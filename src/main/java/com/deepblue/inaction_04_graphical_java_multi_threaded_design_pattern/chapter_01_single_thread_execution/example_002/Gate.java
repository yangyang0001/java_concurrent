package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_01_single_thread_execution.example_002;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gate {

    private long counter;

    private String username;

    private String address;

    public synchronized void pass(String username, String address) {
        this.counter ++;
        this.username = username;
        this.address = address;
    }

    public synchronized void check() {
        if(username.charAt(0) != address.charAt(0)) {
            System.out.println("****************broken****************" + this.toString());
        }
    }

}
