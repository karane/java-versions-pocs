package org.karane;

// ScopedValue.callWhere — scoped computation that returns a value
public class ScopedValuesCallWhereExample {

    static final ScopedValue<String> CURRENT_USER = ScopedValue.newInstance();
    static final ScopedValue<String> LOCALE       = ScopedValue.newInstance();

    public static void main(String[] args) throws Exception {
        String responseForAlice = ScopedValue.where(CURRENT_USER, "alice")
                                             .where(LOCALE, "en-US")
                                             .call(() -> buildResponse());

        String responseForCarlos = ScopedValue.where(CURRENT_USER, "carlos")
                                              .where(LOCALE, "es-MX")
                                              .call(() -> buildResponse());

        System.out.println("alice  --> " + responseForAlice);
        System.out.println("carlos --> " + responseForCarlos);

        // Values are unbound after each call() returns.
        System.out.println("CURRENT_USER bound after scopes: " + CURRENT_USER.isBound());
    }

    static String buildResponse() {
        String greeting = greet();
        String body     = fetchContent();
        return greeting + " | " + body + " [locale=" + LOCALE.get() + "]";
    }

    static String greet() {
        return switch (LOCALE.get()) {
            case "es-MX" -> "Hola, " + CURRENT_USER.get();
            default      -> "Hello, " + CURRENT_USER.get();
        };
    }

    static String fetchContent() {
        return "content-for-" + CURRENT_USER.get();
    }
}
