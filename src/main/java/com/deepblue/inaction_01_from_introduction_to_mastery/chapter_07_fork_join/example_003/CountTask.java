package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join.example_003;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.RecursiveTask;

/**
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountTask extends RecursiveTask<Integer> {

    public static int split = 3;

    private int start;
    private int end;

    @SneakyThrows
    @Override
    protected Integer compute() {

        int sum = 0;

        if(end - start <= split) {
            for(int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;

            CountTask first = new CountTask(start, middle);
            CountTask secod = new CountTask(middle + 1, end);

            first.fork();
            secod.fork();

            Integer firstResult = first.get();
            Integer secodResult = secod.get();

            sum = firstResult + secodResult;
        }
        
        return sum;
    }
}
