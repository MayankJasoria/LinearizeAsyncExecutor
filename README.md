# Problem Statement

Imagine we have an AsyncExecutor class that performs some useful task asynchronously via the method `execute()`.

In addition, the method accepts a function object that acts as a callback and gets invoked after the asynchronous
execution is done.

The asynchronous work is simulated using `sleep`. A passed-in call is invoked to let the invoker take any desired action
after the asynchronous processing is complete.

Your task is to make the execution synchronous without changing the original classes (imagine that you are given the
binaries and not the source code) so that the main thread waits till the asynchronous execution is complete.

## Solution Approach

1. Create a new class (`SyncExecutor`) that extends `AsyncExecutor`.
2. Override the `execute()` method.
3. Acquire a `lock()`
4. Make a call to `super(callback)`, but pass a callback that acquires the lock, signals the condition, then runs the
   original callback.
5. Await the condition, which will be broken only once the callback sends the signal to the condition.
6. Finally, release the lock.

## Sample Run
```
Initiating asynchronous task
Continuing normal execution while async execution is in progress
Performing important asynchronous task
Execution Completed! Initiating callback
Completed Async Execution!

Initiating synchronous task
Perform execution synchronously
Performing important asynchronous task
Execution Completed! Initiating callback
Completed Synchronous execution
Resumed normal work only AFTER sync task was completed
```