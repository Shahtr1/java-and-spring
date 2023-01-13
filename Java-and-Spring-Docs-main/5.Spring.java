Q1 -> What are Java Servlets?
A  -> Servlets are the Java programs that runs on the Java-enabled web server or application server.
	They are used to handle the request obtained from the web server, process the request, produce the response, then send response back to the web server.
	
	Properties of Servlets :
		Servlets work on the server-side.
		Servlets are capable of handling complex requests obtained from web server.
	
	Execution of Servlets :
		Execution of Servlets involves six basic steps:
		1.	The clients send the request to the web server.
		2.	The web server receives the request.
		3.	The web server passes the request to the corresponding servlet.
		4.	The servlet processes the request and generates the response in the form of output.
		5.	The servlet sends the response back to the web server.
		6.	The web server sends the response back to the client and the client browser displays it on the screen
		
	The server-side extensions are nothing but the technologies that are used to create dynamic Web pages. 
	Actually, to provide the facility of dynamic Web pages, Web pages need a container or Web server.
	To meet this requirement, independent Web server providers offer some proprietary solutions in the form of APIs(Application Programming Interface).
	These APIs allow us to build programs that can run with a Web server.
	In this case , Java Servlet is also one of the component APIs of Java Platform Enterprise Edition which sets standards for creating dynamic Web applications in Java.
	
	The Servlet technology is similar to other Web server extensions such as Common Gateway Interface(CGI) scripts and Hypertext Preprocessor (PHP).
	However, Java Servlets are more acceptable since they solve the limitations of CGI such as low performance and low degree scalability
	
Q2 -> What is a CGI?
A  -> CGI is actually an external application which is written by using any of the programming languages like C or C++ and this is responsible for processing client requests and generating dynamic content.
	In CGI application, when a client makes a request to access dynamic Web pages, 
	the Web server performs the following operations:
	1.	It first locates the requested web page i.e the required CGI application using URL.
	2.	It then creates a new process to service the client’s request.
	3.	Invokes the CGI application within the process and passes the request information to the server.
	4.	Collects the response from CGI application.
	5.	Destroys the process, prepares the HTTP response and sends it to the client.
	
	So, in CGI server has to create and destroy the process for every request.
	It’s easy to understand that this approach is applicable for handling few clients but as the number of clients increases,
	the workload on the server increases and so the time taken to process requests increases.
	
Q3 -> What are the differences between Servlet and CGI?
A  -> Servlets are portable and efficient.	CGI is not portable
	In Servlets, sharing of data is possible.	In CGI, sharing of data is not possible.
	Servlets can directly communicate with the web server.	CGI cannot directly communicate with the web server
	Servlets are less expensive than CGI.	CGI are more expensive than Servlets.
	Servlets can handle the cookies.	CGI cannot handle the cookies.
	
Q4 -> What are Servlets API's?
A  -> Servlets are build from two packages:
	1.	javax.servlet(Basic)
	2.	javax.servlet.http(Advance)
	
	Various classes and interfaces present in these packages are:
	Component			Type			Package
-----------------------------------------------------------
	Servlet				Interface		javax.servlet.*
	ServletRequest		Interface		javax.servlet.*
	ServletResponse		Interface		javax.servlet.*
	GenericServlet		Class			javax.servlet.*
	HttpServlet			Class			javax.servlet.http.*
	HttpServletRequest	Interface		javax.servlet.http.*
	HttpServletResponse	Interface		javax.servlet.http.*
	Filter				Interface		javax.servlet.*
	ServletConfig		Interface		javax.servlet.*
	
Q5 -> What are advantages of Java Servlet?
A  -> Servlet is faster than CGI as it doesn’t involve the creation of a new process for every new request received.
	Servlets as written in Java are platform independent.
	There is only a single instance which handles all requests concurrently. This also saves the memory and allows a Servlet to easily manage client state.
	It is a server-side component, so Servlet inherits the security provided by the Web server.
	
Q6 -> What is The Servlet Container?
A  -> Servlet container, also known as Servlet engine is an integrated set of objects that provide run time environment for Java Servlet components.
	In simple words, it is a system that manages Java Servlet components on top of the Web server to handle the Web client requests.
	Services provided by the Servlet container :
		1.	Network Services :
			Loads a Servlet class.
			The loading may be from a local file system, a remote file system or other network services.
			The Servlet container provides the network services over which the request and response are sent.
		2.	Decode and Encode MIME based messages :
			Provides the service of decoding and encoding MIME-based messages.
		3.	Manage Servlet container :
			Manages the lifecycle of a Servlet.
		4.	Resource management :
			Manages the static and dynamic resources, such as HTML files, Servlets and JSP pages.
		5.	Security Service : 
			Handles authorization and authentication of resource access.
		6.	Session Management :
			Maintains a session by appending a session ID to the URL path.
	
Q7 -> What are the steps to create a servlet?
A  -> There are given 6 steps to create a servlet example. These steps are required for all the servers.
	The servlet example can be created by three ways:
	1.	By implementing Servlet interface,
	2.	By inheriting GenericServlet class, (or)
	3.	By inheriting HttpServlet class
	
	The mostly used approach is by extending HttpServlet because it provides http request specific method such as doGet(), doPost(), doHead() etc.
	
Q8 -> What does Class.forName("com.mysql.jdbc.Driver") do while establising a JDBC connection?
A  -> Quoting from the JDBC Specification, Chapter 9, Section 2:
		`
			JDBC drivers must implement the Driver interface, 
			and the implementation must contain a static initializer that will be called 
			when the driver is loaded. 
			This initializer registers a new instance of itself with the DriverManager.
		`
		
		And an example code is provided for AcmeJdbcDriver as follows:
			public class AcmeJdbcDriver implements java.sql.Driver {
			    static {
			        java.sql.DriverManager.registerDriver(newAcmeJdbcDriver());
			    }
			}
			
		And when you call Class.forName(String className), 
		according to the API Documentation, the following happens:
			`
				A call to forName("X") causes the class named X to be initialized.
			`
		where initialization involves code in static block to be executed.
		
		Class.forName loads the class in JVM, when a class is loaded in memory static block executes
		
		So basically, you initialize the Driver class, and in turn the class registers itself with the java.sql.DriverManager per the JDBC specification.
		
		Please note, this is not needed anymore
		`
			The DriverManager methods getConnection and getDrivers have been enhanced 
			to support the Java Standard Edition Service Provider mechanism.
			JDBC 4.0 Drivers must include the file META-INF/services/java.sql.Driver
			This file contains the name of the JDBC drivers implementation of java.sql.Driver.
			Applications no longer need to explictly load JDBC drivers using Class.forName(). 
		`

Q9 -> What is a DAO?
A  -> Responsible for inerfacing with the database
	This is common design pattern: Data Access Object(DAO).
	
	
NOTE: @Controller and @Repository both inherits @Component

Q10 -> What are Checked and Uncheched Exceptions?
A	-> In Java, there are two types of exceptions:
	1.	Checked:
		are the exceptions that are checked at compile time.
		If some code within a method throws a checked exception,
		then the method must either handle the exception or it must specify the exception using throws keyword.
		
		For example, 
		consider the following Java program that opens file at location “C:\test\a.txt” and prints the first three lines of it. 
		The program doesn’t compile, because the function main() uses FileReader() and FileReader() throws a checked exception FileNotFoundException.
		
			import java.io.*; 
  
			class Main { 
			    public static void main(String[] args) { 
			        FileReader file = new FileReader("C:\\test\\a.txt"); 
			        BufferedReader fileInput = new BufferedReader(file); 
			          
			        // Print first 3 lines of file "C:\test\a.txt" 
			        for (int counter = 0; counter < 3; counter++)  
			            System.out.println(fileInput.readLine()); 
			          
			        fileInput.close(); 
			    } 
			} 
			
		Output:
			Exception in thread "main" java.lang.RuntimeException: Uncompilable source code - 
			unreported exception java.io.FileNotFoundException; must be caught or declared to be 
			thrown
			    at Main.main(Main.java:5)
			    
		To fix the above program, we either need to specify list of exceptions using throws,
		or we need to use try-catch block.
			
	2.	Unchecked:
		are the exceptions that are not checked at compiled time.
		It is up to the programmers to be civilized, and specify or catch the exceptions.
		In Java exceptions under Error and RuntimeException classes are unchecked exceptions, everything else under throwable is checked.
		
		Consider the following Java program. It compiles fine, but it throws ArithmeticException when run.
		The compiler allows it to compile, because ArithmeticException is an unchecked exception.

			class Main { 
			   public static void main(String args[]) { 
			      int x = 0; 
			      int y = 10; 
			      int z = y/x; 
			  } 
			} 
			
		Output:
			Exception in thread "main" java.lang.ArithmeticException: / by zero
			    at Main.main(Main.java:5)
			Java Result: 1
			
Q11 -> What is so special about DAOs?
A	-> We use @Repository annotations to DAO implementations, so
	Spring will automatically register the DAO implementation
		thanks to component-scanning
	Spring also provides translation of any JDBC related exceptions
		changes checked to unchecked exceptions
		
Q12 -> What is the purpose of Service Layer?
A	-> Service Facade design pattern
	Intermediate layer for custom business logic
	Integrate data from multiple sources(DAO/repositories)
	


		


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 