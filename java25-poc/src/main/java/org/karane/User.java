package org.karane;

public class User {
    private final String name;

    public User(String name) {
        name = name == null ? "Anonymous" : name.trim();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println(new User("  Alice  ").getName()); // "Alice"
        System.out.println(new User(null).getName());        // "Anonymous"
    }
}
