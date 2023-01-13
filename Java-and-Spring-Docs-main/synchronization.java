Synchronization in Java is the capability to control the access of multiple threads to any shared resource.

Java Synchronization is better option where we want to allow only one thread to access the shared resource.


Q1 -> Why use Synchronization?
A  -> To prevent thread interference.
	To prevent consistency problem.

Q2 -> Thread Synchronization?
A  -> There are two types of thread synchronization 
		mutual exclusive and inter-thread communication.

	1.	Mutual Exclusive
		-	Synchronized method.

		Concept of Lock in Java:
		Synchronization is built around an internal entity known as the lock or monitor. Every object has a lock associated with it.
		By convention, a thread that needs consistent access to an object's fields has to acquire the object's lock before accessing them, 
		and then release the lock when it's done with them.

		TestSynchronization1.java (The PROBLEM??????)
		class Table{  
		void printTable(int n){//method not synchronized  
		   for(int i=1;i<=5;i++){  
		     System.out.println(n*i);  
		     try{  
		      Thread.sleep(400);  
		     }catch(Exception e){System.out.println(e);}  
		   }  
		  
		 }  
		}  
		  
		class MyThread1 extends Thread{  
			Table t;  
			MyThread1(Table t){  
				this.t=t;  
			}  
			public void run(){  
				t.printTable(5);  
			}  
		  
		} 

		class MyThread2 extends Thread{  
			Table t;  
			MyThread2(Table t){  
				this.t=t;  
			}  
			public void run(){  
				t.printTable(100);  
			}  
		}  
		  
		class TestSynchronization1{  
			public static void main(String args[]){  
				Table obj = new Table();//only one object  
				MyThread1 t1=new MyThread1(obj);  
				MyThread2 t2=new MyThread2(obj);  
				t1.start();  
				t2.start();  
			}  
		}  

		If you declare any method as synchronized, it is known as synchronized method.

			class Table{  
			 synchronized void printTable(int n){//synchronized method  
			   for(int i=1;i<=5;i++){  
			     System.out.println(n*i);  
			     try{  
			      Thread.sleep(400);  
			     }catch(Exception e){System.out.println(e);}  
			   }  
			  
			 }  
			}  

		-	Synchronized block.

			Synchronized block can be used to perform synchronization on any specific resource of the method.
			Suppose we have 50 lines of code in our method, but we want to synchronize only 5 lines, in such cases, we can use synchronized block.
			Synchronized block is used to lock an object for any shared resource.
			Scope of synchronized block is smaller than the method.
			Java synchronized block is more efficient than Java synchronized method.

			class Table  
			{      
			 void printTable(int n){    
			   synchronized(this){//synchronized block    
			     for(int i=1;i<=5;i++){    
			      System.out.println(n*i);    
			      try{    
			       Thread.sleep(400);    
			      }catch(Exception e){System.out.println(e);}    
			     }    
			   }    
			 }//end of the method    
			}    


		-	Static synchronization.
			If you make any static method as synchronized, the lock will be on the class not on object.

Q3 -> What is Deadlock?
A  -> Deadlock in Java is a part of multithreading. Deadlock can occur in a situation when a thread is waiting for an object lock, that is acquired by another thread and second thread is waiting for an object lock that is acquired by first thread. 
	Since, both threads are waiting for each other to release the lock, the condition is called deadlock.

	
