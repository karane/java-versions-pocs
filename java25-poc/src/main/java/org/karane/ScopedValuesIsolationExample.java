package org.karane;

// ScopedValue cross-thread isolation.
//
// Two threads share the same ScopedValue key but each binds its own value.
// Each thread only ever sees its own binding 
// A static field would be shared and require locking; ThreadLocal requires remove().
// ScopedValue gives per-thread isolation with no cleanup and no synchronization.

public class ScopedValuesIsolationExample {

    static final ScopedValue<String> SESSION_TOKEN = ScopedValue.newInstance();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = Thread.ofVirtual().name("thread-A").start(() ->
            ScopedValue.where(SESSION_TOKEN, "token-AAAA").run(() -> simulateRequest(5))
        );

        Thread t2 = Thread.ofVirtual().name("thread-B").start(() ->
            ScopedValue.where(SESSION_TOKEN, "token-BBBB").run(() -> simulateRequest(5))
        );

        t1.join();
        t2.join();

        System.out.println("SESSION_TOKEN bound on main thread: " + SESSION_TOKEN.isBound());
    }

    static void simulateRequest(int steps) {
        for (int i = 1; i <= steps; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] step " + i
                    + " — token=" + SESSION_TOKEN.get());
            Thread.yield(); // encourage interleaving
        }
    }
}
