package org.karane;

public class Main {
    public static void main(String[] args) {
        Task task = new CodingTask("Refactor module");

        if (task instanceof CodingTask codingTask) {
            System.out.println("Task: " + codingTask.description());
        }

        String result = switch (task.name()) {
            case "CodingTask" -> "It's a coding task";
            default -> "Unknown task";
        };

        System.out.println(result);
    }
}

