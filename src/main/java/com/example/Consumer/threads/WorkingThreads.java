package com.example.Consumer.threads;

import com.example.Consumer.models.Object;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class WorkingThreads implements Runnable {

    Thread t;
    String threadname;
    int id;
    public static final ReentrantLock mutex = new ReentrantLock();
    public static BlockingQueue<Object> objects = new LinkedBlockingQueue<>();
    static HttpHeaders headers = new HttpHeaders();
    private static int minQueue = 3;
    private static int maxQueue = 10;
    private static boolean SignalSent = false;

    private static RestTemplate restTemplate = new RestTemplate();

    public WorkingThreads(String thread) {
        threadname = thread;
        t = new Thread(this, threadname);
        System.out.println("New thread :" + t);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            mutex.lock();
            try {
                if (objects.size() > 0) {
                    var object = objects.take();
                    System.out.println("Object queue size :" + objects.size());
                    if (objects.size() <= minQueue && SignalSent){
                        SignalSent = false;
                        SendSignal(false);
                    }
                    System.out.println("Value of signal :" + SignalSent);
                    sendObject(object);
                    Thread.sleep(3000);
                    //System.out.println("Capacity of queue " + objects.size());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException();
            } finally {
                mutex.unlock();
            }
        }
    }

    public static void sendObject(Object object) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // create an entity which encapsulated the body (order) and the header of http request
        HttpEntity<Object> entity = new HttpEntity<>(object, headers);
        // send current order to kitchen
        restTemplate.postForEntity("http://localhost:8083/agregator/objectConsumer", entity, Object.class);
        //System.out.println("Object sent to agregator");
    }

    public static void addObject(Object object) {
        // add incoming order to queue
        objects.add(object);
        if (objects.size() >= maxQueue && !SignalSent){
            SignalSent = true;
            System.out.println("S-a setat valoarea true la semnal");
            SendSignal(true);
        }
    }

    public static void SendSignal (boolean signal){
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Boolean> entity = new HttpEntity<>(signal, headers);
        restTemplate.postForEntity("http://localhost:8080/producer/signal", entity, Boolean.class);
        System.out.println("S-a trimis semnal de tip " + signal);
    }
}
