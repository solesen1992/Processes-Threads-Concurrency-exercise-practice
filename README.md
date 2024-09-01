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

## Documentation
### Processes 💻
The processes package provides examples of how to start and manage operating system processes from a Java application. The package contains multiple classes demonstrating different approaches for launching external applications, such as web browsers and text editors, using the ProcessBuilder class and the Runtime.exec() method.

- ProcessDemoProcessBuilder demonstrates how to start an external process (e.g., Google Chrome) using the ProcessBuilder class in Java. It encapsulates the process management functionality and provides feedback on whether the process started successfully.
- ProcessDemoRuntimeExec demonstrates how to start an external process (e.g., Google Chrome) using the Runtime.exec() method in Java. Like the ProcessDemoProcessBuilder, this class manages the process and provides feedback on its execution status.
- StartProcess provides a basic example of starting a process using ProcessBuilder to open the Notepad application on the user's system.


### Threads 💿
This code covers the implementation of multi-threading in Java using two different approaches: implementing the Runnable interface and extending the Thread class. The code examples demonstrate how to create and run multiple threads, each printing a greeting, sleeping for a random amount of time, and then printing a goodbye message.

- MyRunnable: This class represents a task that can be executed by a thread. The task includes printing a greeting message, sleeping for a random duration (0 to 1000 milliseconds), and then printing a goodbye message.
- MyThread: This class extends the Thread class and overrides the run() method to define the thread's behavior. Similar to MyRunnable, it prints a greeting, sleeps for a random duration, and then prints a goodbye message.
- ThreadsDemoExtendsThread: This class demonstrates the creation and execution of threads using the MyThread class, which extends the Thread class.
- ThreadsDemoRunnable: This class demonstrates the creation and execution of threads using the MyRunnable class, which implements the Runnable interface.


### Semaphores 🎧
This package demonstrates the use of semaphores to solve the classic Producer/Consumer problem, where two threads (a producer and a consumer) coordinate access to a shared resource. A semaphore is a synchronization mechanism used in programming to control access to shared resources by multiple threads. It works like a signaling system, allowing threads to perform operations based on the availability of the resource.

Semaphores maintain a counter, which represents the number of permits available. When a thread wants to access the resource, it tries to "acquire" a permit:
- If a permit is available (the counter is greater than 0), the counter is decremented, and the thread gains access to the resource.
- If no permits are available (the counter is 0), the thread is blocked until another thread "releases" a permit by incrementing the counter.
- Semaphores are useful in preventing race conditions, ensuring that only a specific number of threads can access the shared resource simultaneously, thus maintaining data integrity and proper synchronization between threads.

- MyThread extends the Thread class and represents a thread that can act as either a producer or a consumer, depending on its name. The synchronization between the producer and consumer is managed by two semaphores: semProducer and semConsumer. SemProducer controls access for the producer thread and SemConsumer controls access for the consumer thread.
- SemaphoreDemo is the driver class that sets up the environment for the producer and consumer threads: SemProducer: Initialized with 1 permit, allowing the producer to start immediately. SemConsumer: Initialized with 0 permits, blocking the consumer until the producer releases a permit.
- The main method creates two MyThread objects, starts them, and waits for their completion. After both threads finish execution, it prints the final value of the shared resource count, which should be 5.
- SharedResource contains a static integer count that is shared between the producer and consumer threads. It is initially set to 0 and is incremented by the producer during the execution of the program. The consumer reads this value.


### Circular buffer 📀
A buffer is a general term that refers to a temporary storage area used to hold data while it is being transferred from one place to another. A circular buffer is a special type of buffer that treats the buffer memory as if it were circular—meaning that when it reaches the end of the buffer, it wraps around to the beginning. A circular buffer is a fixed-size data structure that uses a single, contiguous block of memory. The write and read positions (pointers) wrap around to the beginning once they reach the end of the buffer, forming a continuous loop. 

The code consists of two classes: Buffer and CircularBuffer. The Buffer class implements a thread-safe circular buffer, while CircularBuffer demonstrates how to use this buffer with producer and consumer threads.
- The Buffer class simulates a circular buffer, which allows data to be written and read in a first-in, first-out (FIFO) manner. This implementation is designed to be thread-safe, ensuring that multiple threads can interact with the buffer concurrently without conflicts. The Buffer class uses synchronized methods and Java’s wait() and notifyAll() mechanisms to manage concurrent access by producer and consumer threads.
- The read method in the Buffer class retrieves and removes an item from the buffer. If the buffer is empty (i.e., PositionRead == PositionWrite), the thread waits until data is available. Once data is read, the PositionRead pointer is incremented (with wrap-around using modulo operation). It notifies other waiting threads that the buffer state has changed.
- The write method in the Buffer class adds an item to the buffer. If the buffer is full (i.e., the next write position equals the read position), the thread waits until space is available. It writes the value to the buffer and increments the PositionWrite pointer (with wrap-around using modulo operation). It notifies other waiting threads that the buffer state has changed.
- The buffer size is fixed at 4, meaning it can hold up to 4 elements before it needs to start overwriting the oldest data or block new writes until space is available. Both the write and read positions (PositionWrite and PositionRead) wrap around when they reach the end of the buffer, effectively creating a loop. This wrap-around behavior is what makes it a circular buffer.
- The CircularBuffer class demonstrates the practical use of the Buffer class. It creates and starts two threads: one for the producer (which writes data to the buffer) and one for the consumer (which reads data from the buffer). The producer and consumer threads run concurrently, interacting with the shared Buffer instance. The producer starts by writing data to the buffer. If the buffer becomes full, it waits until the consumer reads some data. The consumer reads data from the buffer as it becomes available. If the buffer is empty, it waits until the producer writes more data. This back-and-forth interaction between the producer and consumer, managed by the circular buffer, ensures that data is processed in the correct order and without loss.


### Fifo queue 🔓
This Java code is an implementation of a Producer-Consumer problem using a shared FIFO (First In, First Out) queue. The Producer-Consumer is a common problem that deals with synchronizing and scheduling tasks in concurrent programming. This code demonstrates the use of synchronization primitives to manage access to a shared resource in a multithreaded environment.

The SharedFiFoQueue is a FIFO queue because it retrieves elements in the same order they were added. The PosW and PosR pointers ensure that the first element added is the first one removed, maintaining the FIFO discipline.

- The ConditionDemo class sets up the environment by creating and starting the producer and consumer threads.
- The Producer thread reads words from a file and adds them to the SharedFiFoQueue. If the queue is full, it waits until space becomes available.
- The Consumer thread removes words from the SharedFiFoQueue and processes them. If the queue is empty, it waits until the producer adds more elements.
- The SharedFiFoQueue uses a circular buffer to manage elements efficiently, with ReentrantLock and Condition ensuring proper synchronization between threads.
- The program terminates when both the producer and consumer threads finish their work, which is ensured by the join() calls in the ConditionDemo class.

### Monitor 🌟
A monitor in concurrent programming is a synchronization construct that allows threads to have both mutual exclusion (i.e., only one thread can execute a critical section of code at a time) and the ability to wait (block) and be notified (awaken) when certain conditions are met. In Java, a monitor is implemented by using the synchronized keyword, along with the wait(), notify(), and notifyAll() methods provided by the Object class. Each object in Java has an associated monitor that a thread can lock or unlock.

How the Code Uses a Monitor
- The methods Put() and Get() in the Buffer class are declared as synchronized. This means that when a thread calls one of these methods on an instance of Buffer, it automatically acquires the lock (monitor) associated with that Buffer instance. No other thread can execute any other synchronized method on that instance until the lock is released.
- wait() Method: Inside Put(), if the buffer is full (count == buffer.length), the producer thread calls wait(). This causes the producer thread to release the monitor (lock) and suspend its execution until it is notified.
Similarly, in Get(), if the buffer is empty (count == 0), the consumer thread calls wait() and waits to be notified when an item is available in the buffer.
- notify() Method: After producing an item and adding it to the buffer in Put(), the producer calls notify() to wake up a waiting consumer thread. After consuming an item and removing it from the buffer in Get(), the consumer calls notify() to wake up a waiting producer thread.
- Mutual Exclusion: The synchronized keyword ensures that only one thread can execute the Put() or Get() method on the Buffer object at any given time.
- Thread Coordination: The wait() and notify() methods are used to coordinate the actions of the producer and consumer. For instance, the producer must wait if the buffer is full, and the consumer must wait if the buffer is empty.
- This use of monitors ensures that the producer and consumer threads work together correctly, avoiding race conditions and ensuring that the buffer is used properly.
