package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_03_guarded_suspension.example_001;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
public class Request {

    private final String name;

    public Request (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ Request : name = " + name + " ]";
    }
}
