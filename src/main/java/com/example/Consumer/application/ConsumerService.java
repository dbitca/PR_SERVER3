package com.example.Consumer.application;

import com.example.Consumer.threads.WorkingThreads;

public class ConsumerService {
    public static void InitializeWorkingThreads() {
        try {
            for (int i = 0; i < 6; i++) {
                new WorkingThreads("Cook " + String.valueOf(i));
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
