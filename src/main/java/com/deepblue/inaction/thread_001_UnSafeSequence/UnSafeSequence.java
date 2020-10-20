package com.deepblue.inaction.thread_001_UnSafeSequence;

import lombok.*;

import javax.annotation.concurrent.NotThreadSafe;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UnSafeSequence {
    private int value;

    public int getNext() {
        return value ++;
    }
}
