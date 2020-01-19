package nl.vv32.jobutil.io;

import java.util.Arrays;

public class ByteQueue {

    private byte[] array = new byte[0];
    private int tail = 0;

    public void ensureCapacity(int capacity) {
        if(capacity > array.length) {
            array = Arrays.copyOf(array, capacity);
        }
    }

    public void add(int length, ByteBufferConsumer consumer) {
        ensureCapacity(tail + length);
        consumer.accept(array, tail, length);
        tail += length;
    }

    public int peekInt() {
        assert tail >= Integer.BYTES;
        return ((array[0] & 0xFF) << 24) | ((array[1] & 0xFF) << 16) | ((array[2] & 0xFF) << 8) | (array[3] & 0xFF);
    }

    public void peekBytes(byte[] bytes, int offset) {
        assert tail >= offset + bytes.length;
        System.arraycopy(array, offset, bytes, 0, bytes.length);
    }

    public void peekBytes(byte[] bytes) {
        peekBytes(bytes, 0);
    }

    public byte[] peekBytes(int offset, int amount) {
        byte[] bytes = new byte[amount];
        peekBytes(bytes, offset);
        return bytes;
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

    @FunctionalInterface
    public interface ByteBufferConsumer {

        void accept(byte[] buffer, int offset, int length);
    }
}
