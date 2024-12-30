package com.vicc.net;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InByteBufferConcurrentPool {

    /**
     * A thread-safe queue that pools the {@link ByteBuffer} instances available for
     * use. Ensures buffers can be safely acquired and released by multiple threads
     * concurrently.
     */
    private final BlockingQueue<ByteBuffer> pool;

    /**
     * Creates a new instance of this class file and populates the pool.
     * 
     * @param capacity   The capacity of this pool.
     * @param allocation The maximum memory allocation for each individual buffer in
     *                   this pool. Measured in bytes and allocated on the heap.
     */
    public InByteBufferConcurrentPool(int capacity, int allocation) {
        this.pool = new ArrayBlockingQueue<>(capacity);

        for (int it = 0; it < capacity; it++) {
            pool.add(ByteBuffer.allocate(allocation));
        }
    }

    /**
     * Acquires a {@link ByteBuffer} from the pool. This method blocks if no buffers
     * are currently available, until a buffer becomes available.
     * 
     * @return The {@link ByteBuffer} that was acquired from the pool.
     * @throws InterruptedException The exception thrown if the current thread is
     *                              interrupted while waiting.
     */
    public ByteBuffer acquire() throws InterruptedException {
        ByteBuffer buffer = pool.take();
        buffer.clear();
        return buffer;
    }

    /**
     * Releases a {@link ByteBuffer} back to the pool, making it available for
     * reuse.
     * 
     * @param buffer The {@link ByteBuffer} to be released.
     */
    public void release(ByteBuffer buffer) {
        Objects.requireNonNull(buffer, "Required parameter must not be null.");
        buffer.clear();
        pool.offer(buffer);
    }
}
