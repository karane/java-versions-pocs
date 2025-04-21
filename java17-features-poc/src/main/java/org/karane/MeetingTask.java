package org.karane;

final class MeetingTask implements Task {
    private final String desc;

    public MeetingTask(String desc) {
        this.desc = desc;
    }

    public String name() {
        return "MeetingTask";
    }

    public String description() {
        return desc;
    }
}
