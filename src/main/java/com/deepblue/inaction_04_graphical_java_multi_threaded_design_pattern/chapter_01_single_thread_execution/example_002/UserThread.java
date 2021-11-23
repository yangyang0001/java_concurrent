package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_01_single_thread_execution.example_002;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserThread implements Runnable {

    private Gate gate;

    private String username;

    private String address;

    @Override
    public void run() {

        while(true) {
            gate.pass(username, address);
            gate.check();
        }

    }

}
