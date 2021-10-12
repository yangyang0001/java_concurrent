package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_006;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ThreadA implements Runnable{

    private ThreadLocalHolder holder;

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            System.out.println(thread.getName() + " sequence is " + holder.getNextNum());
            try {
                thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        holder.remove();
    }
}
