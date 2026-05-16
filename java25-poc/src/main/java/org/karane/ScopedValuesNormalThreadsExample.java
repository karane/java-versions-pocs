package org.karane;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// ScopedValue with a fixed thread pool (normal threads).

// With ThreadLocal on a pooled thread you must call remove() after each task,
// or the next task on the same thread sees stale state.
// ScopedValue.where(...).run(...) binds only for the duration of the lambda --
// no cleanup needed, no bleed-over even when the thread is reused.

public class ScopedValuesNormalThreadsExample {

    static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 4; i++) {
            final String reqId = "req-" + i;
            pool.submit(() ->
                ScopedValue.where(REQUEST_ID, reqId).run(() -> handleRequest())
            );
        }

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("All tasks done. REQUEST_ID bound on main thread: " + REQUEST_ID.isBound());
    }

    static void handleRequest() {
        String thread = Thread.currentThread().getName();
        System.out.println("[" + thread + "] handling " + REQUEST_ID.get());
        processData();
    }

    static void processData() {
        System.out.println("[" + Thread.currentThread().getName() + "] processing data for " + REQUEST_ID.get());
    }
}
