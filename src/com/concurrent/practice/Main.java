package com.concurrent.practice;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // demonstrate AsyncExecutor first
        System.out.println("Initiating asynchronous task");
        AsyncExecutor executor = new AsyncExecutor();
        executor.execute(() -> System.out.println("Completed Async Execution!"));
        System.out.println("Continuing normal execution while async execution is in progress");

        // Ensuring that async task is completed
        Thread.sleep(4000);

        // Initiating sync task
        System.out.println();
        System.out.println("Initiating synchronous task");
        SyncExecutor syncExecutor = new SyncExecutor();
        syncExecutor.execute(() -> System.out.println("Completed Synchronous execution"));
        System.out.println("Resumed normal work only AFTER sync task was completed");
    }
}
