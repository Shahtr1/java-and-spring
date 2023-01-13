Q1 -> What is @SpringBootApplication?
A  -> This annotation enables
	Auto configuration	@EnableAutoConfiguration
	Component scanning	@ComponentScan
	Additional configuration	@Configuration
	
Q2 -> What is SpringApplication class?
A  -> It is used to bootstrap our application, 
	Behind the scenes, it creates application context and registers all beans,
	starts the embedded server Tomcat etc...
	
Q3 -> What is Spring Boot Actuator?
A  -> Exposes endpoints to monitor and manage your application
	you easily get DevOps functionality (reloading) out-of-the-box
	simply add the dependency to your POM file
	REST endpoints are automatically added to your application
	
	Endpoints are prefixed with: /actuator
		/health			health information about your application
		/info			information about your project
		
Q4 -> What is Health Endpoint?
A  -> /health checks the status of your application

Q5 -> What is Info Endpoint?
A  -> It is empty by default.
	update application.properties with your app info
		info.app.name=My super app
		(start with info.)
		like info.app.name = ?
			info.app.version = ?

Q6 -> What happens at startup?
A  ->  Spring boot framework will trigger the autoconfiguration jar, and will look through classes in the class path

Q7 -> What is the difference between SOAP and Rest API?
A  -> in SOAP we use XML as request exchange format.
	SOAP defines a specific request and response structure.
	SOAP ENVELOPE  and inside it are 
		HEADER Meta-information like authentication, authorization and , signatures etc
		BODY real data
	you can use either HTTP or MQ,
	the service definition is done using WSDL(Web service definition language)


	Rest Web Services:
	Representation state transfer
	the service definition is done using WASl(not popular) and SWAGGER(awesome)

	
			

	
	

	

	
	
	
	
	
	
	