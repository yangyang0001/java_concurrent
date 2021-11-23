package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_00_base.example_004;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrintThread implements Runnable{

    private String message;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(message);
        }
    }
}
