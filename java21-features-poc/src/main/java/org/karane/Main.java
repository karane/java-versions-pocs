package org.karane;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.SequencedSet;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        // Virtual threads - Project Loom
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> System.out.println("Running in virtual thread: " + Thread.currentThread()));
        }

        // Pattern matching with switch
        Object value = 42;

        String message = switch (value) {
            case Integer i when i > 0 -> "Positive integer";
            case Integer i -> "Some integer";
            case String s -> "A string: " + s;
            case null -> "It's null";
            default -> "Something else";
        };

        System.out.println("Switch result: " + message);

        // Record pattern matching (preview)
        Point point = new Point(3, 5);
        printPoint(point);


        // Sequenced collections
        SequencedSet<String> ordered = new LinkedHashSet<>();
        ordered.add("First");
        ordered.add("Middle");
        ordered.add("Last");

        System.out.println("First: " + ordered.getFirst());
        System.out.println("Last: " + ordered.getLast());
    }

    // Uses record pattern to destructure
    static void printPoint(Object obj) {
        if (obj instanceof Point(int x, int y)) {
            System.out.println("Point coordinates -> x: " + x + ", y: " + y);
        }
    }

    record Point(int x, int y) {}
}
