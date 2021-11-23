package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_00_base.example_007;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bank {

    private long money;

    private String name;

    public synchronized void deposit(long m) {
        money += m;
    }

    public synchronized boolean withdraw(long m) {
        if(this.money >= m) {
            money -= m;
            return true;
        } else {
            return false;
        }
    }
}
