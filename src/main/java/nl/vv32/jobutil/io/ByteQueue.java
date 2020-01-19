package nl.vv32.jobutil.io;

import java.util.Arrays;

public class ByteQueue {

    @FunctionalInterface
    public interface ByteBufferConsumer {

        void accept(byte[] buffer, int offset, int length);
    }

    private byte[] array = new byte[0];
    private int tail = 0;

    public void add(int length, ByteBufferConsumer consumer) {
        if(tail + length > array.length) {
            array = Arrays.copyOf(array, array.length + length);
        }
        consumer.accept(array, tail, length);
        tail += length;
    }

    public int peekInt() {
        assert tail >= Integer.BYTES;
        return ((array[0] & 0xFF) << 24) | ((array[1] & 0xFF) << 16) | ((array[2] & 0xFF) << 8) | (array[3] & 0xFF);
    }

    public byte[] peekBytes(int offset, int length) {
        assert tail >= offset + length;
        byte[] subArray = new byte[length];
        System.arraycopy(array, offset, subArray, 0, length);
        return subArray;
    }

    public byte[] peekBytes(int amount) {
        return peekBytes(0, amount);
    }

    public int getAvailable() {
        return tail;
    }

    public void remove(int amount) {
        assert tail >= amount;
        System.arraycopy(array, amount, array, 0, array.length);
        tail -= amount;
    }
}
