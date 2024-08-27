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
The processes package provides examples of how to start and manage operating system processes from a Java application. The package contains multiple classes demonstrating different approaches for launching external applications, such as web browsers and text editors, using the ProcessBuilder class and the Runtime.exec() method.

The processes package demonstrates various methods to start and manage operating system processes using Java. It covers using ProcessBuilder and Runtime.exec(), showing how to launch applications and handle process execution results.

#### ProcessDemoProcessBuilder
This class demonstrates how to start an external process (e.g., Google Chrome) using the ProcessBuilder class in Java. It encapsulates the process management functionality and provides feedback on whether the process started successfully.

Key Points:
- ProcessBuilder: A flexible class that can configure and start a process.
- start(): Initiates the process.
- waitFor(): Blocks the current thread until the process terminates.
- Error Handling: Catches and prints exceptions such as IOException and InterruptedException.

#### ProcessDemoRuntimeExec
This class demonstrates how to start an external process (e.g., Google Chrome) using the Runtime.exec() method in Java. Like the ProcessDemoProcessBuilder, this class manages the process and provides feedback on its execution status.

Key Points:
- Runtime.exec(): An alternative method to ProcessBuilder for starting external processes.
- Process Management: Similar process handling approach as ProcessDemoProcessBuilder.
- Runtime.getRuntime(): Accesses the runtime environment, which can be used to execute a command in a separate process.

#### StartProcess
This class provides a basic example of starting a process using ProcessBuilder to open the Notepad application on the user's system.

Key Points:
- Simple Process Launch: Demonstrates the simplest use of ProcessBuilder to launch a native application (Notepad).
- No Process Management: Unlike the previous examples, this class does not handle process termination or exit codes.

### Threads
This documentation covers the implementation of multi-threading in Java using two different approaches: implementing the Runnable interface and extending the Thread class. The code examples demonstrate how to create and run multiple threads, each printing a greeting, sleeping for a random amount of time, and then printing a goodbye message.

In general, implementing Runnable is the preferred approach when a class needs to perform a task in a thread while retaining the flexibility to extend other classes. Extending Thread can be useful for simple cases where a quick, direct thread implementation is needed without concern for extensibility or reusability.

#### MyRunnable
Implements: Runnable
This class represents a task that can be executed by a thread. The task includes printing a greeting message, sleeping for a random duration (0 to 1000 milliseconds), and then printing a goodbye message.

Methods:
- public void run(): This method contains the logic that the thread will execute. It prints a greeting, sleeps for a random time, and then prints a goodbye message.

#### MyThread
Extends: Thread
This class extends the Thread class and overrides the run() method to define the thread's behavior. Similar to MyRunnable, it prints a greeting, sleeps for a random duration, and then prints a goodbye message.

Methods:
- public void run(): Overrides the run() method of Thread to define the task's behavior.

#### ThreadsDemoExtendsThread
Package: threads
This class demonstrates the creation and execution of threads using the MyThread class, which extends the Thread class.

Methods:
- public static void main(String[] args): The main method creates four instances of MyThread, starts them, and waits for them to complete using join().

#### ThreadsDemoRunnable
This class demonstrates the creation and execution of threads using the MyRunnable class, which implements the Runnable interface.

Methods:
- public static void main(String[] args): The main method creates four instances of MyRunnable, wraps them in Thread objects, starts them, and waits for them to complete using join().

### Semaphores
This package demonstrates the use of semaphores to solve the classic Producer/Consumer problem, where two threads (a producer and a consumer) coordinate access to a shared resource.

A semaphore is a synchronization mechanism used in programming to control access to shared resources by multiple threads. It works like a signaling system, allowing threads to perform operations based on the availability of the resource.

Semaphores maintain a counter, which represents the number of permits available. When a thread wants to access the resource, it tries to "acquire" a permit:
- If a permit is available (the counter is greater than 0), the counter is decremented, and the thread gains access to the resource.
- If no permits are available (the counter is 0), the thread is blocked until another thread "releases" a permit by incrementing the counter.

Semaphores are useful in preventing race conditions, ensuring that only a specific number of threads can access the shared resource simultaneously, thus maintaining data integrity and proper synchronization between threads.


#### MyThread
MyThread extends the Thread class and represents a thread that can act as either a producer or a consumer, depending on its name. The synchronization between the producer and consumer is managed by two semaphores: semProducer and semConsumer.
- semProducer: Controls access for the producer thread.
- semConsumer: Controls access for the consumer thread.

Method: run()
- This method defines the execution logic for the thread:
- Producer Thread: Increments the shared resource count, signals the consumer to proceed by releasing semConsumer, and pauses briefly to allow a context switch.
- Consumer Thread: Reads the value of the shared resource count, signals the producer to proceed by releasing semProducer, and continues.

#### SemaphoreDemo
SemaphoreDemo is the driver class that sets up the environment for the producer and consumer threads:
- semProducer: Initialized with 1 permit, allowing the producer to start immediately.
- semConsumer: Initialized with 0 permits, blocking the consumer until the producer releases a permit.
The main method creates two MyThread objects, starts them, and waits for their completion. After both threads finish execution, it prints the final value of the shared resource count, which should be 5.

#### SharedResource
SharedResource contains a static integer count that is shared between the producer and consumer threads. It is initially set to 0 and is incremented by the producer during the execution of the program. The consumer reads this value.


### Circular buffer
The provided code consists of two classes: Buffer and CircularBuffer. The Buffer class implements a thread-safe circular buffer, while CircularBuffer demonstrates how to use this buffer with producer and consumer threads.

#### Buffer
The Buffer class simulates a circular buffer, which allows data to be written and read in a first-in, first-out (FIFO) manner. This implementation is designed to be thread-safe, ensuring that multiple threads can interact with the buffer concurrently without conflicts.

Key Concepts:
- Circular Buffer: A circular buffer is a fixed-size data structure that uses a single, contiguous block of memory. The write and read positions (pointers) wrap around to the beginning once they reach the end of the buffer, forming a continuous loop.
- Synchronization: The Buffer class uses synchronized methods and Java’s wait() and notifyAll() mechanisms to manage concurrent access by producer and consumer threads.

Instance Variables:
- BufferSize: The size of the buffer, set to 4 in this implementation.
- Container: An integer array that acts as the storage for the buffer.
- PositionRead: Tracks the current position from where data will be read.
- PositionWrite: Tracks the current position where data will be written.

Methods:
synchronized int Read():
- Retrieves and removes an item from the buffer.
- If the buffer is empty (i.e., PositionRead == PositionWrite), the thread waits until data is available.
- Once data is read, the PositionRead pointer is incremented (with wrap-around using modulo operation).
- Notifies other waiting threads that the buffer state has changed.

synchronized void Write(int value):
- Adds an item to the buffer.
- If the buffer is full (i.e., the next write position equals the read position), the thread waits until space is available.
- Writes the value to the buffer and increments the PositionWrite pointer (with wrap-around using modulo operation).
- Notifies other waiting threads that the buffer state has changed.

Circular Buffer Behavior:
The buffer size is fixed at 4, meaning it can hold up to 4 elements before it needs to start overwriting the oldest data or block new writes until space is available.
Both the write and read positions (PositionWrite and PositionRead) wrap around when they reach the end of the buffer, effectively creating a loop. This wrap-around behavior is what makes it a circular buffer.

#### CircularBuffer
The CircularBuffer class demonstrates the practical use of the Buffer class. It creates and starts two threads: one for the producer (which writes data to the buffer) and one for the consumer (which reads data from the buffer).

Key Components:
- static Buffer MyBuffer: A shared instance of the Buffer class that both threads use to read and write data.

Main Method:
Producer Thread:
- Writes 20 integers (from 0 to 19) to the buffer.
- After each write, it prints the value that was added to the buffer.

Consumer Thread:
- Reads 20 integers from the buffer.
- After each read, it prints the value that was retrieved from the buffer.

Execution Flow:
- The producer and consumer threads run concurrently, interacting with the shared Buffer instance.
- The producer starts by writing data to the buffer. If the buffer becomes full, it waits until the consumer reads some data.
- The consumer reads data from the buffer as it becomes available. If the buffer is empty, it waits until the producer writes more data.
- This back-and-forth interaction between the producer and consumer, managed by the circular buffer, ensures that data is processed in the correct order and without loss.

Why It's a Circular Buffer
The Buffer class implements a circular buffer because:
- Fixed Size with Wrap-Around: The buffer has a fixed size (BufferSize = 4), and both the write and read positions wrap around when they reach the end of the array. This ensures continuous usage of the allocated memory without any overflow or out-of-bounds errors.
- FIFO Behavior: The buffer maintains a first-in, first-out (FIFO) order, ensuring that data is read in the same order it was written, a key characteristic of circular buffers.
- Efficient Memory Use: By wrapping around, the buffer continuously reuses its allocated space, making it memory-efficient and suitable for scenarios where data is produced and consumed at different rates.

This setup allows multiple threads to safely and efficiently share data, making the Buffer class a thread-safe circular buffer implementation.

### Fifo queue
This Java code is an implementation of a Producer-Consumer problem using a shared FIFO (First In, First Out) queue. The Producer-Consumer is a common problem that deals with synchronizing and scheduling tasks in concurrent programming. This code demonstrates the use of synchronization primitives to manage access to a shared resource in a multithreaded environment.

The SharedFiFoQueue is a FIFO queue because it retrieves elements in the same order they were added. The PosW and PosR pointers ensure that the first element added is the first one removed, maintaining the FIFO discipline.

- The ConditionDemo class sets up the environment by creating and starting the producer and consumer threads.
- The Producer thread reads words from a file and adds them to the SharedFiFoQueue. If the queue is full, it waits until space becomes available.
- The Consumer thread removes words from the SharedFiFoQueue and processes them. If the queue is empty, it waits until the producer adds more elements.
- The SharedFiFoQueue uses a circular buffer to manage elements efficiently, with ReentrantLock and Condition ensuring proper synchronization between threads.
- The program terminates when both the producer and consumer threads finish their work, which is ensured by the join() calls in the ConditionDemo class.

