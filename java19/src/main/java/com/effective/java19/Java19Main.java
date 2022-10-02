package com.effective.java19;

import com.effective.java19.vertualthreads.VirtualTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Java19Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //ExecutorService executor = Executors.newFixedThreadPool(100);
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<VirtualTask> tasks = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            tasks.add(new VirtualTask(i));
        }

        long time = System.currentTimeMillis();

        List<Future<Integer>> futures = executor.invokeAll(tasks);

        long sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();
        }

        time = System.currentTimeMillis() - time;

        System.out.println("sum = " + sum + "; time = " + time + " ms");

        executor.shutdown();
    }
}
