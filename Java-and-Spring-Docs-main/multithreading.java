Multithreading in Java is a process of executing multiple threads simultaneously.

Multitasking can be achieved in two ways:

1.	Process-based Multitasking (Multiprocessing)
	Each process has an address in memory. In other words, each process allocates a separate memory area.
	Switching from one process to another requires some time for saving and loading registers, memory maps, updating lists, etc.


2.	Thread-based Multitasking (Multithreading)
	Threads share the same address space.
	A thread is lightweight.


Q1 -> What is a Thread?
A  -> A thread is a lightweight subprocess, the smallest unit of processing. It is a separate path of execution.

Threads are independent. If there occurs exception in one thread, it doesn't affect other threads. It uses a shared memory area.

Thread class extends Object class and implements Runnable interface.

Q2 -> Life cycle of a Thread (Thread States)?
A  -> In Java, a thread always exists in any one of the following states. These states are:

	1.	New
		Whenever a new thread is created, it is always in the new state. For a thread in the new state, the code has not been run yet and thus has not begun its execution.

	2.  Active
		When a thread invokes the start() method, it moves from the new state to the active state. 
		The active state contains two states within it: one is runnable, and the other is running.
		-	Runnable:
			A thread, that is ready to run is then moved to the runnable state. 
			In the runnable state, the thread may be running or may be ready to run at any given instant of time. 
			It is the duty of the thread scheduler to provide the thread time to run, i.e., moving the thread to running state.
			A program implementing multithreading acquires a fixed slice of time to each individual thread. 
			Each and every thread runs for a short span of time and when that allocated time slice is over, the thread voluntarily gives up the CPU to the other thread, 
			so that the other threads can also run for their slice of time. 
			Whenever such a scenario occurs, all those threads that are willing to run, waiting for their turn to run, lie in the runnable state. 
			In the runnable state, there is a queue where the threads lie.

		-	Running:
			When the thread gets the CPU, it moves from the runnable to the running state. 
			Generally, the most common change in the state of a thread is from runnable to running and again back to runnable.


	3.	Blocked / Waiting
			Whenever a thread is inactive for a span of time (not permanently) then, either the thread is in the blocked state or is in the waiting state.
			we can say that thread A remains idle until the thread scheduler reactivates thread A, which is in the waiting or blocked state.

			When the main thread invokes the join() method then, it is said that the main thread is in the waiting state. 
			The main thread then waits for the child threads to complete their tasks. 
			When the child threads complete their job, a notification is sent to the main thread, which again moves the thread from waiting to the active state.

			If there are a lot of threads in the waiting or blocked state, 
			then it is the duty of the thread scheduler to determine which thread to choose and which one to reject, and the chosen thread is then given the opportunity to run.


	4.	Timed Waiting
			Thus, thread lies in the waiting state for a specific span of time, and not forever. 
			A real example of timed waiting is when we invoke the sleep() method on a specific thread. 
			The sleep() method puts the thread in the timed wait state. After the time runs out, the thread wakes up and start its execution from when it has left earlier.


	5.	Terminated
			A thread reaches the termination state because of the following reasons:
			-	When a thread has finished its job, then it exists or terminates normally.
			-	Abnormal termination: It occurs when some unusual events such as an unhandled exception or segmentation fault.


Q3 -> How to create a thread?
A  -> There are two ways to create a thread:

	1.	By extending Thread class
		class Multi extends Thread{  
			public void run(){  
				System.out.println("thread is running...");  
			}  
			public static void main(String args[]){  
				Multi t1=new Multi();  
				t1.start();  
			}  
		}  


	2.	By implementing Runnable interface. 
		class Multi3 implements Runnable{  
			public void run(){  
				System.out.println("thread is running...");  
			}  
			  
			public static void main(String args[]){  
				Multi3 m1=new Multi3();  
				Thread t1 =new Thread(m1);   // Using the constructor Thread(Runnable r)  
				t1.start();  
			}  
		}

Q4 -> What is Thread Scheduler in Java?
A  -> A component of Java that decides which thread to run or execute and which thread to wait is called a thread scheduler in Java.
	There are two factors for scheduling a thread i.e. Priority and Time of arrival.

Q5 -> What is Thread.sleep() in Java?
A  -> class TestSleepMethod1 extends Thread{    
		 public void run(){    
		  for(int i=1;i<5;i++){   
		  // the thread will sleep for the 500 milli seconds   
		    try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}    
		    System.out.println(i);    
		  }    
		 }    
		 public static void main(String args[]){    
		  TestSleepMethod1 t1=new TestSleepMethod1();    
		  TestSleepMethod1 t2=new TestSleepMethod1();    
		     
		  t1.start();    
		  t2.start();    
		 }    
		}    

Q6 -> Can we start a thread twice?
A  -> No. After starting a thread, it can never be started again. 
	If you does so, an IllegalThreadStateException is thrown. In such case, thread will run once but for second time, it will throw exception.


Q7 -> What if we call Java run() method directly instead start() method?
A  -> Each thread starts in a separate call stack.
	Invoking the run() method from the main thread, the run() method goes onto the current call stack rather than at the beginning of a new call stack.

Q8 -> Java join() method?
A  -> The join() method in Java is provided by the java.lang.Thread class that permits one thread to wait until the other thread to finish its execution
	When the join() method is invoked, the current thread stops its execution and the thread goes into the wait state. 
	The current thread remains in the wait state until the thread on which the join() method is invoked called is dead 
	or the wait for the specified time frame(in milliseconds) is over.

Q9 -> Priority of a Thread (Thread Priority)?
A  -> Each thread has a priority. Priorities are represented by a number between 1 and 10.
3 constants defined in Thread class:
	public static int MIN_PRIORITY
	public static int NORM_PRIORITY
	public static int MAX_PRIORITY


Q10 -> WHat is Java Shutdown Hook?
A 	-> A special construct that facilitates the developers to add some code that has to be run when the Java Virtual Machine (JVM) is shutting down is known as the Java shutdown hook.


Q12 -> Java Garbage Collection?
A  	-> In java, garbage means unreferenced objects.
	Garbage Collection is process of reclaiming the runtime unused memory automatically. In other words, it is a way to destroy the unused objects.

	How can an object be unreferenced?
	-	By nulling the reference
		Employee e=new Employee();  
		e=null;  


	-	By assigning a reference to another
		Employee e1=new Employee();  
		Employee e2=new Employee();  
		e1=e2;//now the first object referred by e1 is available for garbage collection  


	-	By anonymous object etc.
		new Employee();  


	finalize() method:
	The finalize() method is invoked each time before the object is garbage collected. 
	This method can be used to perform cleanup processing. This method is defined in Object class as

		protected void finalize(){}  


	gc() method	
	The gc() method is used to invoke the garbage collector to perform cleanup processing. The gc() is found in System and Runtime classes.


	Note: The Garbage collector of JVM collects only those objects that are created by new keyword. 
	So if you have created any object without new, you can use finalize method to perform cleanup processing (destroying remaining objects).