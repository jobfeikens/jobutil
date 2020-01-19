package nl.vv32.jobutil.bytestuff;

import java.util.Arrays;

//not thread safe
public class ByteQueue {

    @FunctionalInterface
    public interface ByteBufferConsumer {

        void accept(byte[] buffer, int offset, int length);
    }

    private byte[] array = new byte[0];
    private int available = 0;

    public void add(int length, ByteBufferConsumer consumer) {
        if(available + length > array.length) {
            array = Arrays.copyOf(array, array.length + length);
        }
        consumer.accept(array, available, length);
        available += length;
    }

    public void remove(int amount) {
        assert amount <= available;

        for (int i = 0; i < array.length; i++) {
            if (i + amount < array.length) {
                array[i] = array[i + amount];
            } else {
                array[i] = 0;
            }
        }
        available -= amount;
    }

    public int getAvailable() {
        return available;
    }

    public int getInt() {
        return (array[0] << 24) | (array[1] << 16) | (array[2] << 8) | (array[3] & 0x000000FF);
    }

    public byte[] getBytes(int offset, int length) {
        byte[] subArray = new byte[length];
        System.arraycopy(array, offset, subArray, 0, length);
        return subArray;
    }
}
