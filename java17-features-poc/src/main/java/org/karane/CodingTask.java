package org.karane;

final class CodingTask implements Task {
    private final String desc;

    public CodingTask(String desc) {
        this.desc = desc;
    }

    public String name() {
        return "CodingTask";
    }

    public String description() {
        return desc;
    }
}
