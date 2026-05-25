package org.example.factory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FactoryThreadPool {
    private final WorkerThread[] threads;
    private final BlockingQueue<Runnable> taskQueue;
    private volatile boolean isRunning = true;

    public FactoryThreadPool(int poolSize) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new WorkerThread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
    }

    public void submit(Runnable task) {
        if (isRunning) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getQueueSize() {
        return taskQueue.size();
    }

    public void shutdown() {
        isRunning = false;
        for (WorkerThread t : threads) {
            t.interrupt();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (isRunning || !taskQueue.isEmpty()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    if (!isRunning) break;
                }
            }
        }
    }
}