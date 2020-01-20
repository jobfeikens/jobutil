package nl.vv32.jobutil.io;

import java.util.Arrays;
import java.util.Optional;

public class ByteQueue {

    private byte[] array = new byte[0];
    private int tail = 0;

    public void ensureCapacity(int capacity) {
        if (capacity > array.length) {
            array = Arrays.copyOf(array, capacity);
        }
    }

    public void add(int length, ByteBufferConsumer consumer) {
        ensureCapacity(tail + length);
        consumer.accept(array, tail, length);
        tail += length;
    }

    public Optional<Integer> peekInt() {
        if (tail >= Integer.BYTES) {
            return Optional.of(
                (array[0] & 0xFF) << Byte.SIZE * 3 |
                (array[1] & 0xFF) << Byte.SIZE * 2 |
                (array[2] & 0xFF) << Byte.SIZE     |
                (array[3] & 0xFF)
            );
        } else {
            return Optional.empty();
        }
    }

    public Optional<byte[]> peekBytes(int offset, int amount) {
        if (tail >= offset + amount) {
            byte[] bytes = new byte[amount];
            System.arraycopy(array, offset, bytes, 0, amount);
            return Optional.of(bytes);
        } else {
            return Optional.empty();
        }
    }

    public Optional<byte[]> peekBytes(int amount) {
        return peekBytes(0, amount);
    }

    public int getBytesAvailable() {
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
