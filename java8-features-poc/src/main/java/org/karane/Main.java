package org.karane;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> tasks = Arrays.asList("Write code", "Review PR", "Deploy");

        // Lambda + Stream
        List<String> filtered = tasks.stream()
            .filter(task -> task.startsWith("W"))
            .collect(Collectors.toList());

        filtered.forEach(System.out::println);

        // Optional
        Optional<String> maybeTask = tasks.stream().findFirst();
        maybeTask.ifPresent(System.out::println);

        // New Date/Time API
        LocalDate today = LocalDate.now();
        System.out.println("Today is: " + today);
    }
}
