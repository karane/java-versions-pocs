package org.karane;

public class ScopedValuesRebindingExample {

    static final ScopedValue<String> CURRENT_USER = ScopedValue.newInstance();
    static final ScopedValue<String> ROLE         = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(CURRENT_USER, "alice")
                   .where(ROLE, "user")
                   .run(() -> {
                       printContext("outer scope");
                       performAdminOperation();
                       printContext("back in outer scope");
                   });
    }

    static void performAdminOperation() {
        ScopedValue.where(ROLE, "admin").run(() -> {
            printContext("inner scope (elevated)");
            deleteRecord();
        });
    }

    static void deleteRecord() {
        System.out.println("  deleteRecord -- user=" + CURRENT_USER.get() + ", role=" + ROLE.get());
    }

    static void printContext(String label) {
        System.out.println("[" + label + "] user=" + CURRENT_USER.get() + ", role=" + ROLE.get());
    }
}
