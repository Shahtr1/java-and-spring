Q1 -> What is Spring MVC Front Controller?
A  -> Front controller known as DispatcherServlet
	Part of the Spring Framework
	Already developed by Spring Dev team

Q2 -> What is Controller?
A  -> When a front controller has  a request, it delegates the request to the Controller.
	Code created by Developer
	Contains your business logic
		1.	Handle the request
		2.	Store/retrieve data (db, web service...)
		3.	Place data in model
	Send to appropriate view template

Q3 -> What is Model?
A  -> Model: contains your data
	Store/retrieve data via backend systems
		1.	database, web service, etc...
		2.	Use a Spring bean if you like
	Place your data in the model
		1.	Data can be any Java object/collection

Q4 -> What is a View Template?
A  -> Spring MVC is flexible
		1.	Supports many view templates
	Most common is JSP + JSTL
	Developer creates a page
		1.	Displays data

Q5 -> What are other View Templates?
A  -> Other view templates supported
		1.	Thymleaf, Groovy
		2.	Velocity, Freemaker, etc...

Q6 -> What is @Controller annotation?
A  -> Annotate class with @Controller
	@Controller inherits from @Component ... supports scanning
	
Q7 -> What is Java EE?
A  -> Java EE is a collection of enterpise api's 
	like Servlets, JSP, JDBC, Enterprise Java Beans, Java Message Service
	
Q8 -> What is Jakarta EE?
A  -> Jakarta EE is the community version of Java EE(rebranded, relicensed)
	Its not managed my oracle now, but by the community.
	
Q9 -> What are the changes with Jakarta EE in picture?
A  -> At the moment, main change with jakarta EE ... package renaming
	javax.* packages are renamed to jakarta.*
	For Example,
		1.	javax.servlet.http.HttpServlet is now jakarta.servlet.http.HttpServlet
		
Q10 -> What is the impact of Jakarta EE 9 on Hibernate Validator?
A   -> Hibernate Validator 7 is based on Jakarta EE 9.
	But Spring 5 is still based on some components of Java EE(javax.*)
	As a result Spring 5 is not compatible with Hibernate Validator 7.
	
Q11 -> What if i need to use latest version?
A   -> Two releases of Hibernate Validator
		1.	Hibernate Validator 7 is for Jakarta EE 9 projects
		2.	Hibernate Validator 6.2 is compatible with Spring 5
	Hibernate Validator 6.2 has the SAME features as Hibernate Validator 7

Q12 -> Explaing MVC using JAVA configuration?
A 	-> To enable Spring MVC support through a Java configuration class, all we have to do is add the @EnableWebMvc annotation:
			@EnableWebMvc
			@Configuration
			public class WebConfig {

			    /// ...
			}

	@Configuration – which marks the class as a source of bean definitions

	This will set up the basic support we need for an MVC project, such as registering controllers and mappings,
	type converters, validation support, message converters and exception handling.

	If we want to customize this configuration, we need to implement the WebMvcConfigurer interface:
			@EnableWebMvc
			@Configuration
			public class WebConfig implements WebMvcConfigurer {

			   @Override
			   public void addViewControllers(ViewControllerRegistry registry) {
			      registry.addViewController("/").setViewName("index");
			   }

			   @Bean
			   public ViewResolver viewResolver() {
			      InternalResourceViewResolver bean = new InternalResourceViewResolver();

			      bean.setViewClass(JstlView.class);
			      bean.setPrefix("/WEB-INF/view/");
			      bean.setSuffix(".jsp");

			      return bean;
			   }
			}
	In this example, we've registered a ViewResolver bean that will return .jsp views from the /WEB-INF/view directory.
	Very important here is that we can register view controllers that create a direct mapping between the URL and the view name using the ViewControllerRegistry. 
	This way, there's no need for any Controller between the two.

	If we want to also define and scan controller classes, we can add the @ComponentScan annotation with the package that contains the controllers:
			@EnableWebMvc
			@Configuration
			@ComponentScan(basePackages = { "com.baeldung.web.controller" })
			public class WebConfig implements WebMvcConfigurer {
			    // ...
			}

	To bootstrap an application that loads this configuration, we also need an initializer class:
			public class MainWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

				@Override
				protected Class<?>[] getRootConfigClasses() {
					return null;
				}

				@Override
				protected Class<?>[] getServletConfigClasses() {
					return new Class[] { ReportConfig.class };
				}

				@Override
				protected String[] getServletMappings() {
					return new String[] { "/" };
				}

			}



	
SPECIAL NOTE about BindingResult Parameter Order:
	When performing Spring MVC validation, the location of the BindingResult parameter is very important. 
	In the method signature, the BindingResult parameter must appear immediately after the model attribute.
	If you place it in any other location, Spring MVC validation will not work as desired. In fact, your validation rules will be ignored.
		@RequestMapping("/processForm")
        public String processForm(
                @Valid @ModelAttribute("customer") Customer theCustomer,
                BindingResult theBindingResult) {
            ...            
        }
        
Q13 -> What is @RestController?
A   -> @RestController is a convenience annotation for creating Restful controllers. 
It is a specialization of @Component and is autodetected through classpath scanning. 
It adds the @Controller and @ResponseBody annotations. It converts the response to JSON or XML. 
It does not work with the view technology, so the methods cannot return ModelAndView. 
It is typically used in combination with annotated handler methods based on the @RequestMapping annotation.

	

 


















