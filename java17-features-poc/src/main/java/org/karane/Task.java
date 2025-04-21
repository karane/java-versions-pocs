package org.karane;

sealed interface Task permits CodingTask, MeetingTask {
    String name();

    String description();
}
