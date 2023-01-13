Q1 -> Define REST over HTTP?
A  -> Most common use of REST is over HTTP
	Leverage HTTP methods for CRUD operations

		HTTP methods 					CRUD Operation
---------------------------------------------------------------------------------------------------------
		   POST							Create a new entity
		   GET							Read a list of entities or single entity
		   PUT							Update an existing entity
		   DELETE						Delete an existing entity

Q2 -> What is @ExceptionHandler annotation?
A  -> Exceptionhandler will return a ResponseEntity
	ResponseEntity is a wrapper for the HTTP response object
	ResponseEntity provides fine-grained control to specify:
		HTTP status code, HTTP headers and Response body
		
Q3 -> What are the steps to handle exceptions?
A  -> Here are the steps
	1.	Create a custom error response class
	2.	Create a custom exception class
	3.	Update REST service to throw exception if student not found
	4.	Add an exception handler using @ExceptionHandler
	
Q4 -> What is Spring @ControllerAdvice?
A  -> @ControllerAdvice is similar to an interceptor / filter
	Pre-process requests to controllers
	Post-process responses to handle exceptions
	Perfect for global exception handling
	
	
Q5 -> Explain REST API Design?
A  -> here are the steps:
	1.	Review API Requirements
	2.	Indentify main resource / entity
		Convention is to use plural form of resource / entity: customers
	3.	Use HTTP methods to assign action on resource

 
	

	













