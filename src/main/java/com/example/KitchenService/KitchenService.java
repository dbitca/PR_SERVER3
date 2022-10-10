package com.example.KitchenService;

import com.example.KitchenService.resources.KitchenServiceCooks;

public class KitchenService {
    public static void InitializeCooks() {
        try {
            for (int i = 0; i < 6; i++) {
                new KitchenServiceCooks("Cook " + String.valueOf(i));
                //Thread.sleep(500);
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
