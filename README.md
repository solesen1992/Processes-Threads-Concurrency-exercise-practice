# Processes, Threads, Concurrency exercise practice
Practice exercises from my education in Computer Science. Exercises in Processes, Threads, and Concurrency.

The exercises helped me gain a better understanding of the topics.

## Credit
The original code can be seen here: https://github.com/goldfingyr/DMA-CSD-S233

I've only made small changes to some of the code. My primary goal was to get an understanding of the topic.

## Topics
- Processes - How to start a process
- Threads - How to make a thread using 'extends Thread' and 'implement Runnable'.
- Semaphores - Synchronization to prevent deadlock
- Circular buffer - Data structure
- FIFO (First in, First out)
- Buffer

## Documentation
### Processes

### Threads

### Semaphores

### Circular buffer

### Fifo queue
This Java code is an implementation of a Producer-Consumer problem using a shared FIFO (First In, First Out) queue. The Producer-Consumer is a common problem that deals with synchronizing and scheduling tasks in concurrent programming. This code demonstrates the use of synchronization primitives to manage access to a shared resource in a multithreaded environment.

The SharedFiFoQueue is a FIFO queue because it retrieves elements in the same order they were added. The PosW and PosR pointers ensure that the first element added is the first one removed, maintaining the FIFO discipline.

- The ConditionDemo class sets up the environment by creating and starting the producer and consumer threads.
- The Producer thread reads words from a file and adds them to the SharedFiFoQueue. If the queue is full, it waits until space becomes available.
- The Consumer thread removes words from the SharedFiFoQueue and processes them. If the queue is empty, it waits until the producer adds more elements.
- The SharedFiFoQueue uses a circular buffer to manage elements efficiently, with ReentrantLock and Condition ensuring proper synchronization between threads.
- The program terminates when both the producer and consumer threads finish their work, which is ensured by the join() calls in the ConditionDemo class.

