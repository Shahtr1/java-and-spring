## What is the Producer-Consumer Problem?

The producer-consumer problem is a synchronization problem between different processes. There are three entities in this problem:
a producer, a consumer, and a memory buffer. Both the producer and consumer share the same memory buffer.

The producer produces some items and pushes them into the memory buffer. A consumer then consumes these items by popping them out of the buffer. If the buffer is empty, then the consumer waits for the producer to push an item, which it consumes after the producer pushes it.

The memory buffer is of fixed size. If it is full, the producer waits for the consumer to consume an item before pushing a new one. The producer and consumer cannot access the buffer at the same time – that is, it's mutually exclusive. Each process should wait for the other to finish its work on the buffer before it can access the buffer.

**Multithreading** is used to solve this problem
