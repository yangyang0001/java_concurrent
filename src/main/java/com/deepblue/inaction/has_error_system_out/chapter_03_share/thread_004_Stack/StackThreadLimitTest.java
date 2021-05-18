package com.deepblue.inaction.has_error_system_out.chapter_03_share.thread_004_Stack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Java虚拟机栈 进行线程的隔离来确保安全性
 */
public class StackThreadLimitTest {

    public void loadTheArk(Collection<Animal> animalParams) {
        SortedSet<Animal> animals = new TreeSet<>();
        int numPairs = 0;
        Animal animal;

        animal = new Animal("雄狮");
        numPairs = 1;
        animals.add(animal);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Animal {
        private String name;
    }
}
