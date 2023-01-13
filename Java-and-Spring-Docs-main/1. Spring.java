Q0 -> Introduce Spring framework?

The Spring Framework core is, simply put, an IoC container used to manage beans.
There are two basic types of containers in Spring – the Bean Factory and the Application Context. 
The former provides basic functionalities, which are introduced here; the latter is a superset of the former and is most widely used.
ApplicationContext is an interface in the org.springframework.context package and it has several implementations, 
and the ClassPathXmlApplicationContext is one of these.

///////////////////////////////////////////////////////////////////////////////////////////////////

Bean Factory:
	Let's define a simple bean:
		public class Employee {
		    private String name;
		    private int age;
		    
		    // standard constructors, getters and setters
		}
		
	We can configure the BeanFactory with XML. Let's create a file bean factory-example.xml:
		<bean id="employee" class="com.baeldung.beanfactory.Employee">
		    <constructor-arg name="name" value="Hello! My name is Java"/>
		    <constructor-arg name="age" value="18"/>
		</bean>    
		<alias name="employee" alias="empalias"/>
		
	ClassPathResource belongs to the org.springframework.core.io package. 
	Let's run a quick test and initialize XmlBeanFactory using ClassPathResource as shown below:
		public class BeanFactoryWithClassPathResourceTest {

		    @Test
		    public void createBeanFactoryAndCheckEmployeeBean() {
		        Resource res = new ClassPathResource("beanfactory-example.xml");
		        BeanFactory factory = new XmlBeanFactory(res);
		        Employee emp = (Employee) factory.getBean("employee");
		
		        assertTrue(factory.isSingleton("employee"));
		        assertTrue(factory.getBean("employee") instanceof Employee);
		        assertTrue(factory.isTypeMatch("employee", Employee.class));
		        assertTrue(factory.getAliases("employee").length > 0);
		    }
		}
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////

ApplicationContext:
	1.	Bean instantiation/wiring (Same as BeanFactory)
	2.	Automatic BeanPostProcessor registration (Not in BeanFactory)
	3.	Automatic BeanFactoryPostProcessor registration (Not in BeanFactory)
	4.	Convenient MessageSource access (for i18n) (Not in BeanFactory)
	5.	ApplicationEvent publication (Not in BeanFactory)
	
	The ApplicationContext interface extends an interface called MessageSource, 
	and therefore provides messaging (i18n or internationalization) functionality.
	
/////////////////////////////////////////////////////////////////////////////////////////////////////

Q1 -> What is Spring Bean?
A  -> In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans.
	A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.
	
Q2 -> What is a Spring Container?
A  -> It is generally known as ApplicationContext. 
	ApplicationContext is an interface for providing configuration information to an application.
	Spring has specialized implementations
	1.	ClassPathXmlApplicationContext
	2.	AnnotationConfigApplicationContext
	3.	GenericWebApplicationContext
	others...
	
Q3 -> How to create a Spring Container?
A  -> ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

Q4 -> What is Inversion Of Control?
A  -> IoC is a design pattern that describes inverting the flow of control in a system,
	 so execution flow is not controlled by a central piece of code.
	  This means that components should only depend on abstractions of other components and are not be responsible for handling the creation of dependent objects.
	  The advantages of this architecture are:
	  1.	decoupling the execution of a task from its implementation
	  2.	making it easier to switch between different implementations
	  3.	greater ease in testing a program by isolating a component or mocking its dependencies, and allowing components to communicate through contracts
	  We can achieve Inversion of Control through various mechanisms such as:
	  	Strategy design pattern, Service Locator pattern, Factory pattern, and Dependency Injection (DI).

Q5 -> What is Dependency Injection?
A  -> Dependency injection is a pattern we can use to implement IoC, where the control being inverted is setting an object's dependencies.
	Connecting objects with other objects, or “injecting” objects into other objects, is done by an assembler rather than by the objects themselves.
	Dependency Injection in Spring can be done through constructors, setters or fields.
	1.	Constructor-Based Dependency Injection
		The container will invoke a constructor with arguments each representing a dependency we want to set.
		
		@Configuration
		public class AppConfig {
		
		    @Bean
		    public Item item1() {
		        return new ItemImpl1();
		    }
		
		    @Bean
		    public Store store() {
		        return new Store(item1());
		    }
		}
	2.	Setter-Based Dependency Injection
		For setter-based DI, 
		the container will call setter methods of our class after invoking a no-argument constructor or no-argument static factory method to instantiate the bean.
		@Bean
		public Store store() {
		    Store store = new Store();
		    store.setItem(item1());
		    return store;
		}
	3.	Field-Based Dependency Injection
		In case of Field-Based DI, we can inject the dependencies by marking them with an @Autowired annotation:
		public class Store {
		    @Autowired
		    private Item item; 
		}
		While constructing the Store object, if there's no constructor or setter method to inject the Item bean, 
		the container will use reflection to inject Item into Store.
		This approach might look simpler and cleaner, but we don't recommend using it because it has a few drawbacks such as:
		a.	This method uses reflection to inject the dependencies, which is costlier than constructor-based or setter-based injection.
		
	Wiring allows the Spring container to automatically resolve dependencies between collaborating beans by inspecting the beans that have been defined.
	There are four modes of autowiring a bean using an XML configuration:
	a.	no: the default value – this means no autowiring is used for the bean and we have to explicitly name the dependencies.
	b.	byName: autowiring is done based on the name of the property, therefore Spring will look for a bean with the same name as the property that needs to be set.
	c.	byType: similar to the byName autowiring, only based on the type of the property. 
		This means Spring will look for a bean with the same type of the property to set. If there's more than one bean of that type, the framework throws an exception.
	d.	constructor: autowiring is done based on constructor arguments, meaning Spring will look for beans with the same type as the constructor arguments.
	
	Lazy Initialized Beans:
		By default, the container creates and configures all singleton beans during initialization.
		To avoid this, we can use the lazy-init attribute with value true on the bean configuration:
			<bean id="item1" class="org.baeldung.store.ItemImpl1" lazy-init="true" />
		The advantage of this is faster initialization time, but the trade-off is that we won't discover any configuration errors until after the bean is requested, 
		which could be several hours or even days after the application has already been running.
		


Q6 -> Why do we specify the Coach interface in getBean()?
A  -> Coach theCoach = context.getBean("myCoach", Coach.class);
	When we pass the interface to the method, behind the scenes Spring will cast the object for you.
	Behaves the same as getBean(String), but provides a measure of type safety by throwing a BeanNotOfRequiredTypeException if the bean is not of the required type.
	This means that ClassCastException can't be thrown on casting the result correctly, as can happen with getBean(String).
	
Q7 -> What are Bean Scopes?
A  -> Scope refers to the lifecycle of a bean
	How long does the bean live?
	How many instances are created?
	How is the bean shared?
	
	The default scope of a bean is singleton.
	
Q8 -> What is a Singleton?
A  -> Spring Container creates only one instance of the bean, by default
	It is cached in memory
	All requests for the bean will return a SHARED reference to the SAME bean
	
Q9 -> What are Additional Spring Bean Scopes?
A  -> singleton: Create a single shared instance of the bean. Default scope.
	prototype: Creates a new bean instance for each container request.
	request: Scoped to an HTTP web request. only used for web apps.
	session: Scoped to an HTTP web request. only used for web apps.
	global-session: Scoped to a global HTTP web request. only used for web apps.	
	
Q10 -> Define Bean Lifecycle?
A  	-> Container Started --> Bean Instantiated --> Dependencies Injected --> internal Spring processing
																							|
							Bean is Ready For Use, Container iS Shutdown <-- Your Custom Init Method					
											|
									Your Custom Destroy Method
											|
										   STOP
										  
Q11 -> What are Bean Lifecycle Methods/Hooks?
A	-> Custom Init and destroy methods on bean initialization and destruction respectively.
	Clean up handles to resources (db, sockets, files etc)
	Calling custom business logic method
	
Special Note about init and destroy Method Signatures:
		When using XML configuration, 
		I want to provide additional details regarding the method signatures of the init-method  and destroy-method .
			Access modifier
				The method can have any access modifier (public, protected, private)
			
			Return type
				The method can have any return type. However, "void" is most commonly used. 
				If you give a return type just note that you will not be able to capture the return value. 
				As a result, "void" is commonly used.	
				
			Method name
				The method can have any method name.
				
			Arguments
				The method can not accept any arguments. The method should be no-arg.
				

Special Note about Destroy Lifecycle and Prototype Scope
	For "prototype" scoped beans, Spring does not call the destroy method.  Gasp! 
	In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean:
	the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.
	in the case of prototypes, configured destruction lifecycle callbacks are not called.
	The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding
	
Q12 -> How can I create code to call the destroy method on prototype scope beans?
A   -> You can destroy prototype beans but custom coding is required.
	https://drive.google.com/open?id=1262cK04FYe7x3blpp2wVv7gmkvRyA_cX
	
Q13 -> What are Java Annotations?
A   -> Provide meta-data about the class
	Processed at compile time or run-time for special processing.
	Automatically register the beans in the Spring container of the class with annotation.	


	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	