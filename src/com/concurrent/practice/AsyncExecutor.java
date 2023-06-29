package com.concurrent.practice;

public class AsyncExecutor {

    public void execute(Runnable callback) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Performing important asynchronous task");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Execution Completed! Initiating callback");
                callback.run();
            }
        });
        thread.start();
    }
}
