package com.deepblue.inaction.system_out_error.chapter_02_safe.thread_001_UnSafeSequence;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UnSafeSequence {
    private int value;

    public int getNext() {
        try {
            Thread.sleep(500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value ++;
    }
}
