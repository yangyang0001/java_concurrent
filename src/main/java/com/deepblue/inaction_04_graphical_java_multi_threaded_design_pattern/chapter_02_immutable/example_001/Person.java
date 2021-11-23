package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_02_immutable.example_001;

public final class Person {

    private final String username;
    private final String address;

    public Person(String username, String address) {
        this.username = username;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "[ Person : username = " + username + ", address = " + address + " ]";
    }
}
