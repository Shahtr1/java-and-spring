Q1 -> What are Java Annotations?
A  -> Provide meta-data about the class
	Processed at compile time or run-time for special processing.
	Automatically register the beans in the Spring container of the class with annotation.	

Q2 -> What is Spring AutoWiring?
A  -> For dependency injection, Spring can use auto wiring
	Spring will look for a class that matches the property
		matches by type: class or interface
	Spring will inject it automatially ... hence it is autowired
	
Q3 -> What are AutoWiring Injection Types?
A  -> Constructor Injection
	Setter injection
	Field Injection
	
Q4 -> AutoWiring FAQ: What if there are Multiple Implementations?
A  -> Spring has special support to handle this case. Use the @Qualifier annotation.

NOTE-Constructor Injection: As of Spring Framework 4.3, 
an @Autowired annotation on such a constructor is no longer necessary if the target bean only defines one constructor to begin with. 
However, if several constructors are available, at least one must be annotated to teach the container which one to use.

Q5 -> What is a Field Injection?
A  -> Inject dependencies by setting field values on your class directly (even private fields)
	Accomplished by using Java Reflection
	
NOTE- Annotations, Default Bean Names - The Special Case
	In general, when using Annotations, for the default bean name, Spring uses the following rule.
	If the annotation's value doesn't indicate a bean name, an appropriate name will be built based on the short name of the class (with the first letter lower-cased).
	However, for the special case of when BOTH the first and second characters of the class name are upper case, then the name is NOT converted.
	For the case of RESTFortuneService
	RESTFortuneService --> RESTFortuneService
	No conversion since the first two characters are upper case.
	
Q6 -> How to use @Qualifier with constructor injection
A  -> You have to place the @Qualifier annotation inside of the constructor arguments.
	@Autowired
    public TennisCoach(
    		@Qualifier("randomFortuneService") FortuneService theFortuneService
    	) {

        System.out.println(">> TennisCoach: inside constructor using @autowired and @qualifier");
        
        fortuneService = theFortuneService;
    }
    
Q7 -> How to inject properties file using Java annotations?
A  -> 1. Create a properties file to hold your properties. It will be a name value pair.

	New text file:  src/sport.properties
		foo.email=myeasycoach@luv2code.com
		foo.team=Silly Java Coders
		
	2. Load the properties file in the XML config file.
		<context:property-placeholder location="classpath:sport.properties"/>
		
	3. Inject the properties values into your Swim Coach: SwimCoach.java
		@Value("${foo.email}")
		private String email;
		    
		@Value("${foo.team}")
		private String team;
		
NOTE-HEADS UP - FOR JAVA 9 USERS - @PostConstruct and @PreDestroy
	If you are using Java 9 or higher, then you will encounter an error when using @PostConstruct and @PreDestroy in your code. 
	

Q8 -> What are the ways of Configuring Spring Container?
A  -> 1. Full XML Config
	2. XML Component Scan
	3. Java Configuration Class
	
Q9 -> How @Bean works behind the scenes?
A  ->  	@Bean 
		  public Coach swimCoach() {   
		   SwimCoach mySwimCoach = new SwimCoach();   
		   return mySwimCoach; 
		  }
		At a high-level, Spring creates a bean component manually. 
		By default the scope is singleton. So any request for a "swimCoach" bean, 
		will get the same instance of the bean since singleton is the default scope.
		Behind the scenes, during the @Bean interception, it will check in memory of the Spring container (applicationContext) and see if this given bean has already been created.
		If this is the first time the bean has been created then it will execute the method as normal. It will also register the bean in the application context. 
		So that is knows that the bean has already been created before. Effectively setting a flag.  
		The next time this method is called, the @Bean annotation will check in memory of the Spring container (applicationContext) and see if this given bean has already been created.
		
Q10 -> What is a real-time use case for @Bean?
A 	-> Here is a real-time use case of using @Bean: 
	You can use @Bean to make an existing third-party class available to your Spring framework application context.	  

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	