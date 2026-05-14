package org.karane;

public class ScopedValuesExample {

    static final ScopedValue<String> CURRENT_USER = ScopedValue.newInstance();
    static final ScopedValue<String> REQUEST_ID   = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(CURRENT_USER, "alice")
                   .where(REQUEST_ID, "req-42")
                   .run(ScopedValuesExample::handleRequest);

        System.out.println("After scope — CURRENT_USER bound: " + CURRENT_USER.isBound());
    }

    static void handleRequest() {
        System.out.println("Handling request " + REQUEST_ID.get()
                + " for user: " + CURRENT_USER.get());
        fetchData();
    }

    static void fetchData() {
        System.out.println("  fetchData — user: " + CURRENT_USER.get()
                + ", request: " + REQUEST_ID.get());
        auditLog();
    }

    static void auditLog() {
        System.out.println("  auditLog  — logging action for " + CURRENT_USER.get());
    }
}
