package com.deepblue.inaction_02_beauty_of_calculation.chapter_11_action.example_001;

import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat 是线程不安全的 会抛出 NullPointerException 因为有 共享变量 protected Calender calender;
 */
public class SimpleDateFormatDemo {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat defaultSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {

                @SneakyThrows
                @Override
                public void run() {
                    Date parse = defaultSDF.parse("2021-11-16 18:13:01");
                }

            });

            thread.start();
        }
    }

}
