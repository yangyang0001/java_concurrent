package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_005;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockList {

    private static ArrayList<String> list = Lists.newArrayList();

    private static ReentrantLock lock = new ReentrantLock();

    public void add(String element) {
        try {
            lock.lock();
            list.add(element);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void remove(String element) {
        try {
            lock.lock();
            list.remove(element);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get(int index) {
        try {
            lock.lock();
            return list.get(index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }

}
