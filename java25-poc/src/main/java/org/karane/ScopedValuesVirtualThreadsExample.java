package org.karane;

import java.util.ArrayList;
import java.util.List;

public class ScopedValuesVirtualThreadsExample {

    static final ScopedValue<String> USER_ID = ScopedValue.newInstance();

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            final String userId = "user-" + i;
            Thread t = Thread.ofVirtual().start(() ->
                ScopedValue.where(USER_ID, userId).run(() -> handleRequest())
            );
            threads.add(t);
        }

        for (Thread t : threads) t.join();

        System.out.println("All 1000 virtual threads done.");
        System.out.println("USER_ID bound on main thread: " + USER_ID.isBound());
    }

    static void handleRequest() {
        String result = computeResponse();
        if (USER_ID.get().endsWith("1") || USER_ID.get().equals("user-1000")) {
            System.out.println("[" + Thread.currentThread().getName() + "] " + USER_ID.get() + " → " + result);
        }
    }

    static String computeResponse() {
        return "response-for-" + USER_ID.get();
    }
}
