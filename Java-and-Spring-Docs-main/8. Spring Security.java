Q1 -> What is a Spring Security Model?
A  -> Spring Security defines a framework for security
	Implemented using Servlet filters in the background
	two methods of securing a Web app: declarative and programmatic

Q2 -> What are Servlet Filters?
A  -> Servlet Filters are used to pre-process / post-process web requests
	Servlet Fileters can route web requests based on security logic
	Spring provuides a bulk of security functionality with servlet filters

Q3 -> Spring Security in Action
A  -> 																					Access Denied
																							    |
																						       No
																								|
		Spring																				    |
	   Security --------->  Is Web Resource --Yes----------------- Is user -------Yes-----> Is user
	    filters  			  Protected?           ^             authenticated?           authorized?
	    						  |			       |			      |						    |
	    						  |			   Authenticate           |						    |
	    						  |          userId & password		  No					    |
	    						  |                ^				  |						   Yes
	    						  No               |                 Send two                   |
	    						  |           Show login form <-----Login Form                  |
	    						  |																|
	    						  |------------------------------------------------------> Show Resource


Q4 -> XML config to Java config
A  -> web.xml 													Spring
															@Configuration


	spring-mvc-demo.servlet.xml								Spring Dispatcher
														   Servlet Initializer


    @EnableWebMvc
    1.	Provides similar support to <mvc:annotation-driven /> in XML.
    2.	Adds conversion, formatting and validation support
    3.	Processing of @Controller classes and @RequestMapping etc ... methods

    Web App Initializer
    1.	Spring MVC provides support for web app initialization
    2.	Makes sure your code is automatically detected
    3.	your code is used to initialize the servlet container

    	We use special class for this:
    		AbstractAnnotationConfigDispatcherServletInitializer
    		Your TO DO list
    			Extend this abstract base class
    			Override required methods
    			Specify servlet mapping and location of your app config

Q5 -> Explain Development process of Spring security?
A  -> Development Process:
	1.	Add Maven dependencies for Spring MVC Web App
	2.	Create Spring App Configuration (@Configuration)
	3.	Create Spring Dispatcher Servlet Initializer
	4.	Develop our Spring controller
	5.	Develop our JSP view page
	
Q6 -> How many dependencies does Spring Security have?
A  -> Two
	spring-security-web
	spring-security-config
	
Q7 -> Jow do you manually add csrf token?
A  -> <input type="hidden"
			name="${_csrf.parameterName }"
			value="${_csrf.token }" />
			

    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			