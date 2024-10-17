# Hibernate Interview Questions

1. Explain the role of SessionFactory in Hibernate.
    
    1. SessionFactory is a key interface that serves as the factory for creating Session objects.
    2. SessionFactory is heavyweight and designed to be created once per application or per database.
    3. SessionFactory is thread-safe, meaning it can be shared across multiple threads in a web or enterprise application.
    4. During the initialization phase, the SessionFactory reads the Hibernate configuration file (`hibernate.cfg.xml`) and the mapping files (like annotated entity classes) to set up the database connection.
    5. The SessionFactory manages a pool of database connections. When a Session object is created, it pulls a connection from this pool, optimizing performance.
        ```java
        sessionFactory = new Configuration().configure().buildSessionFactory();
        ```
    6. If second-level caching is enabled using Ecache , the SessionFactory manages this cache, which is shared across multiple Session instances.

2. What is a Hibernate Session?

   1. A Session opens a JDBC connection to interact with the database.
   2. Session object is short-lived and should only last for the duration of a single transaction or batch of operations.
   3. A Session is not thread-safe, meaning it should not be shared across multiple threads.
   4. The Session manages the persistence context, which holds the entities involved in a transaction.
   5. The Session object provides first-level caching, meaning all the entities loaded in a session are stored in the persistence context. If the same entity is accessed multiple times, Hibernate retrieves it from the cache instead of querying the database again.
     
   ```java
     Session session = HibernateUtil.getSessionFactory().openSession();
     Employee emp1 = session.get(Employee.class, 1);  // Database hit
     Employee emp2 = session.get(Employee.class, 1);  // Retrieved from first-level cache
     ```

3. What are the Different Strategies for Hibernate Cache?
   
    The CacheConcurrencyStrategy enum defines several strategies that control how cached data is managed and accessed concurrently across multiple transactions.
    1.  `READ_ONLY`
         -   Description:
         This strategy is used for data that never changes (immutable data).
         If the application tries to update an entity cached with READ_ONLY strategy, Hibernate throws an exception.
           
         -   Use Case:
         Ideal for static lookup tables, such as country codes or product categories, where the data does not change.
   
    2.  `NONSTRICT_READ_WRITE`
        -   Description:
        This strategy assumes infrequent updates and relaxes consistency guarantees.
        -   Use Case:
        If data is modified, the cache may contain stale data for a short period
        - How It Works:
        Suitable for entities with low update frequency where exact consistency is not critical, such as historical records or user activity logs.
      
    3.  `READ_WRITE`
        -   Description:
            This strategy uses a soft lock mechanism to ensure that only one transaction can update a cached entity at a time. 
            When an entity is updated, Hibernate places a lock on the cache entry to prevent other transactions from reading or modifying it until the current transaction is complete.
        -   Use Case:
        Suitable for entities that require frequent updates and where data consistency is critical, such as inventory data or financial transactions.
        - How It Works:
          During an update, a soft lock is placed on the cache entry.
          Once the transaction completes, the soft lock is released, and the cache is updated.
          If multiple transactions try to modify the same entry concurrently, one will succeed, and others will wait or fail, ensuring consistency.

    4.  `TRANSACTIONAL`
         - Description:
           The TRANSACTIONAL cache strategy ensures that both database operations and cache operations are treated as a single unit of work, meaning that either both succeed or both fail together.
         - Use Case:
           This is particularly useful when you need distributed transactions across multiple systems or databases, where both the cache and the database must stay in sync.
         - How It Works:
           Atomicity:
           Both the cache update and the database update are included in the same transaction. Either both are committed together, or both are rolled back if an error occurs.
           In some applications (like microservices or enterprise systems), a single transaction might span across multiple systems (e.g., database, cache, and messaging system). 
           `TRANSACTIONAL` cache strategy ensures that the cache also participates in this transaction.
           The TRANSACTIONAL strategy requires a `JTA-compliant cache provider`, like `Infinispan`, which can handle distributed transactions.
         - Example:
           Imagine you are updating an order system where:
           The order total is updated in the database.
           The cache is also updated to store the latest order data.
           If the database operation succeeds but the cache operation fails, your system will have inconsistent data. To prevent this, the TRANSACTIONAL cache strategy ensures that both the database and the cache are updated together, or both are rolled back if anything fails.

4. Explain lifecycle of an entity in hibernate.
    1.  Transient:
        An entity is in the Transient state when it is created using the new keyword, but it is not associated with any Hibernate session and not saved to the database.
        The transient object only exists in memory (JVM heap), and no database record corresponds to it.
    2.  Persistent:
        An entity is in the Persistent state when it is associated with a Hibernate session and its state is synchronized with the database.
        Any changes made to the entity while it is in the persistent state are automatically tracked and saved to the database when the session is flushed or committed.
        Exists both in memory (JVM) and in the database.
    3.  Detached:
        An entity is in the Detached state when it was previously persistent (associated with a session), but the session has been closed or cleared.

   -    State Transitions in Hibernate:
        1. Transient → Persistent:
           Happens when an object is saved using `session.save()` or `session.persist()`
           ```java
           session.save(emp);
           ```
        2. Persistent → Detached:
           Happens when the session is closed or cleared.
           ```java
           session.close(); 
           ```
        3. Detached → Persistent:
           Happens when the object is re-attached to a session using `session.update()` or `session.merge()`
           ```java
           session.update(emp); 
           session.merge(emp); 
           ```
        4. Persistent → Transient:
           Happens when the object is deleted from the database.
           ```java
           session.delete(emp); 
           ```

5. What is the difference between `get()` and `load()` methods?
   -    The get() method immediately retrieves the entity from the database and returns it. If the entity is not found, it returns null.
   -    The load() method returns a proxy object without hitting the database immediately. The actual database query is triggered only when the entity’s properties are accessed (this is called lazy loading).

6. What is cascading in Hibernate?

   1. `CascadeType.PERSIST`	Propagates the save() or persist() operation from the parent to its child entities.
   2. `CascadeType.MERGE`	Propagates the merge() operation from the parent to the child entities.
   3. `CascadeType.REMOVE`	Propagates the delete() operation to the child entities.
   4. `CascadeType.REFRESH`	Propagates the refresh() operation to reload entities from the database.
   5. `CascadeType.DETACH`	Propagates the detach() operation, removing the entities from the persistence context.
   6. `CascadeType.ALL`	    Applies all the above operations.

   -   Example: Cascading in One-to-Many Relationship:
   ```java
    import jakarta.persistence.*;
    import java.util.ArrayList;
    import java.util.List;
    
    @Entity
    public class Department {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
    
        @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)  // Cascade applied here
        private List<Employee> employees = new ArrayList<>();
   
        public void addEmployee(Employee employee) {
           employees.add(employee);
           employee.setDepartment(this);  // Set the reverse relationship
        }
    
        // Getters and Setters
   }
    ```
   ```java
    import jakarta.persistence.*;
    
    @Entity
    public class Employee {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
    
        @ManyToOne
        @JoinColumn(name = "department_id")
        private Department department;
    
        // Getters and Setters
    }
    
    ```
   ```java
   // Saving Parent and Child Together:
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    
    Department dept = new Department();
    dept.setName("IT");
    
    Employee emp1 = new Employee();
    emp1.setName("Alice");
    
    Employee emp2 = new Employee();
    emp2.setName("Bob");
    
    // Add employees to the department
    dept.addEmployee(emp1);
    dept.addEmployee(emp2);
    
    // Save the department (employees will also be saved due to cascading)
    session.save(dept);
    
    tx.commit();
    session.close();
    
    ```
   ```java
   // Deleting Parent and Child Together:
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    
    // Retrieve the department with ID 1
    Department dept = session.get(Department.class, 1);
    
    // Delete the department (employees will also be deleted due to cascading)
    session.delete(dept);
    
    tx.commit();
    session.close();
    
    ```
   
7. Difference Between merge() and update() in Hibernate
    - Both merge() and update() are used to synchronize the state of an entity with the database. 
    - However, they behave differently in how they handle detached entities and session conflicts.
    - The merge() method copies the state of a detached entity into a new or existing persistent entity within the current session. It returns a new managed instance of the entity.
      This method ensures no session conflicts, as it does not require the original detached object to be re-attached.
    - The update() method re-attaches the same instance of a detached entity to the current session, meaning it makes the detached object persistent again.
      If the same entity instance is already associated with another session or the same session, Hibernate will throw a `NonUniqueObjectException`.

8. What is JDBC batch processing, and how does Hibernate support it?
    - JDBC Batch Processing is a technique used to execute multiple SQL statements as a batch in a single database round trip.
    - Example: Using JDBC
    ```java
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    PreparedStatement ps = conn.prepareStatement("INSERT INTO Employee (name, department) VALUES (?, ?)");
    
    // Add multiple records to the batch
    ps.setString(1, "Alice");
    ps.setString(2, "IT");
    ps.addBatch();
    
    ps.setString(1, "Bob");
    ps.setString(2, "Finance");
    ps.addBatch();
    
    // Execute the batch
    int[] result = ps.executeBatch();
    
    // Close the resources
    ps.close();
    conn.close();
    ```
    - Example: Using Hibernate
    ```java 
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.setFlushMode(FlushMode.MANUAL);
    
    for (int i = 1; i <= 100; i++) {
    Employee emp = new Employee();
    emp.setName("Employee " + i);
    emp.setDepartment("IT");
    
        // Save the employee entity (will be batched)
        session.save(emp);
    
        // Flush and clear the session after every 20 inserts to avoid memory overflow
        if (i % 20 == 0) {
            session.flush();
            session.clear();
        }
    }
    
    tx.commit();
    session.close();
    
    ```
   - If using `GenerationType.IDENTITY`, batch processing might not work efficiently because each insert requires an immediate database hit to retrieve the generated ID.
   - Use `GenerationType.SEQUENCE` for better batch processing support.

9. Difference Between flush() and clear() in Hibernate
   - The flush() method forces Hibernate to synchronize the in-memory state of the session with the database. 
   - This means that any pending SQL operations (like INSERT, UPDATE, or DELETE) are executed immediately on the database.
   - Ensures that all pending changes (dirty entities) are applied to the database.
   - Does not close the session or remove entities from the persistence context.
   - The clear() method removes all entities from the session’s persistence context. This means that all persistent entities become detached, and any unsaved changes in the session are discarded.

10. How do you fetch all records from a table using HQL?
    -   Example:
      ```java
     String hql = "FROM Employee";
     Query<Employee> query = session.createQuery(hql, Employee.class);

     // Execute the query and get the result list
     List<Employee> employees = query.getResultList();
     ``` 

11. What are callback interfaces in Hibernate?
    -   onSave():
        This method is called before the entity is saved to the database. You can add custom logic like logging or validating data.

    -   onUpdate():
        This method is called before an entity is updated. You can use it to track changes or perform additional operations.

    -   onDelete():
        This method is executed before an entity is deleted. You can use it to perform cleanup actions or log the deletion.
    
    -   onLoad():
        This method is called after the entity is loaded from the database. You can use it to initialize transient fields or perform post-load operations

    -   Interceptor Interface for Global Event Handling:
        If you want to handle events globally (for all entities) instead of implementing lifecycle methods in each entity, you can use the Interceptor interface. 
        This allows you to intercept events across the entire application.
        
        ```java
        import org.hibernate.Interceptor;
        import org.hibernate.type.Type;
        import java.io.Serializable;
    
        public class GlobalInterceptor implements Interceptor {
    
            @Override
            public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
                System.out.println("Global Interceptor: Saving entity - " + entity.getClass().getSimpleName());
                return false;
            }
            
            @Override
            public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
                System.out.println("Global Interceptor: Updating entity - " + entity.getClass().getSimpleName());
                return false;
            }
            
            @Override
            public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
                System.out.println("Global Interceptor: Deleting entity - " + entity.getClass().getSimpleName());
            }
        }
        ```
        ```java
        // Registering the Interceptor with the SessionFactory
        SessionFactory sessionFactory = new Configuration()
        .configure()
        .setInterceptor(new GlobalInterceptor())
        .buildSessionFactory();
        ```

12. What is the difference between `save()` and `persist()`?
    - The save() method is used to immediately save the entity to the database and returns the generated primary key (identifier).
    - The persist() method makes the given entity persistent (managed by the session) but does not return the identifier. It ensures that the entity is saved only within a transaction and delays the insert operation until the transaction is committed or the session is flushed.

13. What is the role of `@JoinTable` and `@JoinColumn`?
    - The @JoinColumn annotation is used to specify the foreign key column in the owning entity's table that maps the relationship to another table. This is useful in one-to-one or many-to-one relationships.
    ```java
    @Entity
    public class Employee {
    
        @Id
        private int id;
        private String name;
    
        @ManyToOne
        @JoinColumn(name = "department_id")  // Foreign key column in Employee table
        private Department department;
    }
    ```
    ```sql
    CREATE TABLE Employee (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES Department(id)
    );
    ```
    - The @JoinTable annotation is used to define a join table that manages many-to-many relationships between two entities. Since many-to-many associations involve multiple records in both tables, a third join table is needed to store the mappings.
    ```java
    @Entity
    public class Student {
    
        @Id
        private int id;
        private String name;
    
        @ManyToMany
        @JoinTable(
            name = "student_course",  // Join table name
            joinColumns = @JoinColumn(name = "student_id"),  // Foreign key to Student
            inverseJoinColumns = @JoinColumn(name = "course_id")  // Foreign key to Course
        )
        private Set<Course> courses;
    }
    ```
    ```java
    @Entity
    public class Course {
    
        @Id
        private int id;
        private String name;
    
        @ManyToMany(mappedBy = "courses")
        private Set<Student> students;
    }
    ```
    ```sql
    CREATE TABLE student_course (
        student_id INT,
        course_id INT,
        PRIMARY KEY (student_id, course_id),
        FOREIGN KEY (student_id) REFERENCES Student(id),
        FOREIGN KEY (course_id) REFERENCES Course(id)
    );
    ```

14. How do you handle composite keys in Hibernate using annotations?
    - A composite key refers to a primary key composed of more than one column. 
    - Handling composite keys requires special mapping because Hibernate typically expects a single primary key field.
    - Hibernate provides two ways to map composite keys using annotations:
      1. `@IdClass`: Define a separate class to represent the composite key.
        ```java
      public class EmployeeId implements Serializable {

       private int empId;
       private int deptId;

       public EmployeeId(int empId, int deptId) {
           this.empId = empId;
           this.deptId = deptId;
       }
       }
      ```
        ```java
        @Entity
        @Table(name = "employees")
        @IdClass(EmployeeId.class) // Reference to the composite key class
       public class Employee {
        @Id
       private int empId;
      
       @Id
       private int deptId;

       public EmployeeId(int empId, int deptId) {
           this.empId = empId;
           this.deptId = deptId;
       }
       }
      ```
      2. `@EmbeddedId`: The @EmbeddedId annotation allows embedding a composite key class directly into the entity. The composite key class is annotated with `@Embeddable`.
        ```java
      @Embeddable
      public class EmployeeId implements Serializable {

       private int empId;
       private int deptId;

       public EmployeeId(int empId, int deptId) {
           this.empId = empId;
           this.deptId = deptId;
       }
       }
      ```
      ```java
        @Entity
        @Table(name = "employees")
        @IdClass(EmployeeId.class) // Reference to the composite key class
       public class Employee {
        
      @EmbeddedId
       private EmployeeId employeeId;

       public EmployeeId(int empId, int deptId) {
           this.empId = empId;
           this.deptId = deptId;
       }
       }
      ```

15. How is pagination achieved in Hibernate?
    
    - Example: Pagination Using HQL Query
    ```java
     public class HibernatePaginationExample {
        public static void main(String[] args) {
        // Get the session factory and open a session
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
        Session session = sessionFactory.openSession();
             try {
                 // Begin transaction
                 session.beginTransaction();
      
                 // HQL query to fetch all employees
                 String hql = "FROM Employee";
                 Query<Employee> query = session.createQuery(hql, Employee.class);
      
                 // Pagination: Fetch records from 11 to 20 (second page)
                 query.setFirstResult(10);  // Starting from the 11th row (index 10)
                 query.setMaxResults(10);   // Maximum 10 records
    
                 // Execute the query and get the result list
                 List<Employee> employees = query.getResultList();
                 // Commit transaction
                 session.getTransaction().commit();
             } catch (Exception e) {
                 if (session.getTransaction() != null) {
                     session.getTransaction().rollback();
                 }
                 e.printStackTrace();
             } finally {
                 // Close the session
                 session.close();
             }
         }
     }
    ```
    -   SQL Query Generated by Hibernate:
    ```sql
      SELECT * FROM Employee LIMIT 10 OFFSET 10;
    ```

16. What are EntityListeners in Hibernate?
    - EntityListeners are callback classes that allow you to hook into the lifecycle events of entities. 
    - These listeners provide a mechanism to execute custom logic when specific lifecycle events—such as persist, update, delete, or load operations—occur on an entity.
    - EntityListener class: Contains the methods that handle the entity events contains @Pre(Persist or Update or Remove),@Post(same),@PostLoad
    - Entity class: Annotated to link the listener to the entity and linking like below
    ```java
    @EntityListeners(EmployeeListener.class)
    ```

17. How do you implement optimistic locking?
    - Optimistic locking is a concurrency control mechanism used to prevent data conflicts when multiple transactions attempt to update the same record in a database. 
    - It works by versioning an entity, ensuring that the data hasn’t been modified by another transaction before the current transaction commits. 
    - If a conflict is detected, the application throws an `OptimisticLockException`.
    - In Hibernate, optimistic locking is implemented using a version field (usually annotated with `@Version`).
    - Hibernate checks the version value of the entity when updating it. If the version value in the database has changed since it was last fetched, an `OptimisticLockException` is thrown, and the update fails.

18. What is dirty checking, and how does it work in Hibernate?
    - Dirty checking is a mechanism in Hibernate where it automatically detects changes made to persistent entities during a session and synchronizes these changes with the database when the transaction is committed. 
    - With dirty checking, developers don’t need to manually call update() for every modification—they just modify the fields of the persistent entity, and Hibernate will detect and update the database accordingly.
    - When an entity is retrieved using session.get() or session.load(), it becomes a persistent object—which means it is being managed by the Hibernate session.
    - When the transaction commits or the session is flushed, Hibernate performs dirty checking to detect if the entity’s fields have been modified. If changes are found, it automatically issues the necessary SQL update statements to synchronize the changes with the database.
    - If changes are accidentally made to an entity, Hibernate will detect these and apply them to the database.
    ```java
    session.setDefaultReadOnly(true);
    // or
    @Transactional(readOnly = true)
    // or for specific entities
    session.setReadOnly(entity, true)
    ```
    - For large datasets, dirty checking can result in performance overhead, as Hibernate compares the current state with the original snapshot.
      Using FlushMode.MANUAL for Batch Processing:
    ```java
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.setFlushMode(FlushMode.MANUAL);  // Disable automatic flush
    
    for (int i = 1; i <= 100; i++) {
    Employee emp = new Employee();
    emp.setName("Employee " + i);
    emp.setDepartment("IT");
    
        session.save(emp);
    
        if (i % 20 == 0) {  // Flush every 20 records to reduce memory usage
            session.flush();
            session.clear();  // Clear the session to free up memory
        }
    }
    
    tx.commit();
    session.close();
    ```
    - If you modify an entity outside the transaction or session, you might encounter a `LazyInitializationException`.

19. What are Flush Modes in Hibernate?
    - In Hibernate, a flush operation synchronizes the state of the persistence context (in-memory objects) with the database. 
    - Specifically, it ensures that all changes made to persistent entities (like inserts, updates, and deletes) are written to the database.
    - Hibernate provides several flush modes to determine when changes will be flushed to the database:
        1. `FlushMode.AUTO`: Hibernate automatically flushes changes before executing a query or when the transaction is committed.
        2. `FlushMode.COMMIT`: Flush occurs only at the end of the transaction when commit() is called.
        3. `FlushMode.ALWAYS`: Flush occurs before every query execution, ensuring the latest state is used.
        4.  `FlushMode.MANUAL`: Hibernate never flushes automatically. You need to manually call flush() when required.

20. What are named queries, and how are they defined?
    - Named queries are predefined HQL or SQL queries that are defined at the entity level and can be reused throughout the application.
    ```java
    @Entity
    @NamedQuery(
    name = "Employee.findByDepartment",
    query = "FROM Employee e WHERE e.department = :dept"
    )
    @Table(name = "employees")
    public class Employee {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
    }
    ```
    ```java
    List<Employee> employees = session.createNamedQuery("Employee.findByDepartment", Employee.class)
    .setParameter("dept", "HR")
    .getResultList();
    ```
    ```java
    @NamedNativeQuery(
    name = "Employee.findAllEmployees",
    query = "SELECT * FROM employees",
    resultClass = Employee.class
    )
    @Entity
    @Table(name = "employees")
    public class Employee {
    // Fields, getters, and setters as before
    }
    ```

21. Different Generation Types in Hibernate
    - `AUTO`: Default. Hibernate chooses the strategy based on the underlying database.
    - `IDENTITY`: Relies on auto-increment columns in the database. The database generates the ID.
    - `SEQUENCE`: Uses database sequences to generate unique IDs.
    ```java
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_sequence", allocationSize = 1)
    private int id;
    ```
    - `TABLE`: Uses a separate table to maintain and generate unique IDs.
    ```java
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_table_gen")
    @TableGenerator(
    name = "employee_table_gen",
    table = "id_generator_table",
    pkColumnName = "gen_name",
    valueColumnName = "gen_value",
    pkColumnValue = "employee_id",
    allocationSize = 1
    )
    private int id;
    ```
    ```sql
    CREATE SEQUENCE employee_sequence START WITH 1 INCREMENT BY 1;
    ```
    -  Custom Generators: 
    ```java
    public class UUIDGenerator implements IdentifierGenerator {
    
        @Override
        public Serializable generate(SharedSessionContractImplementor session, Object object) {
            return UUID.randomUUID().toString();
        }
    }
    ```
    ```java
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.example.UUIDGenerator")
    private String id;
    ```

22. How does Hibernate handle inheritance mapping?
    - Inheritance mapping allows developers to map class hierarchies (i.e., inheritance relationships) in Java to relational database tables.
    - `InheritanceType.SINGLE_TABLE` (Default): Maps the entire class hierarchy to one single table, with a discriminator column.
    ```java
    @Entity
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Single table strategy
    @DiscriminatorColumn(name = "employee_type", discriminatorType = DiscriminatorType.STRING)
    public class Employee {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
    }
    ```
    ```java
    @Entity
    @DiscriminatorValue("Manager")
    public class Manager extends Employee {
    private String department;
    }
    ```
    ```java
    @Entity
    @DiscriminatorValue("Developer")
    public class Developer extends Employee {
    private String programmingLanguage;
    }
    ```
    | **id** | **name** | **employee_type** | **department** | **programmingLanguage** |
    |--------|----------|-------------------|----------------|-------------------------|
    | 1      | Alice    | Manager           | HR             | NULL                    |
    | 2      | Bob      | Developer         | NULL           | Java                    |
    
    - `InheritanceType.TABLE_PER_CLASS` : Creates separate tables for each concrete class in the hierarchy.
    
    | **id** | **name** | **department** |
    |--------|----------|----------------|
    | 1      | Alice    | HR             |

    | **id** | **name** | **programmingLanguage** |
    |--------|----------|-------------------------|
    | 1      | Alice    | Java                    |

    - `InheritanceType.JOINED` : Maps the base class and each subclass to separate tables with relationships using foreign keys.

    | **id** | **name** |
    |--------|----------|
    | 1      | Alice    |
    | 2      | Bob      |

    | **employee_id** | **department** |
    |-----------------|----------------|
    | 1               | Alice          |

    | **employee_id** | **programmingLanguage** |
    |-----------------|-------------------------|
    | 2               | Java                    |

23. How do you map enum types in Hibernate?
    - Mapping Enums Using `@Enumerated` Annotation
        1. Mapping Enum Using Ordinal (Default) `@Enumerated(EnumType.ORDINAL)`
        2. Mapping Enum Using String `@Enumerated(EnumType.STRING)`
    - Using `@Convert` with Custom Enum Converter
    ```java
    import jakarta.persistence.AttributeConverter;
    import jakarta.persistence.Converter;
    
    @Converter(autoApply = true)
    public class EmployeeStatusConverter implements AttributeConverter<EmployeeStatus, String> {
    
        @Override
        public String convertToDatabaseColumn(EmployeeStatus status) {
            switch (status) {
                case ACTIVE:
                    return "A";
                case INACTIVE:
                    return "I";
                case TERMINATED:
                    return "T";
                default:
                    throw new IllegalArgumentException("Unknown status: " + status);
            }
        }
    
        @Override
        public EmployeeStatus convertToEntityAttribute(String dbData) {
            switch (dbData) {
                case "A":
                    return EmployeeStatus.ACTIVE;
                case "I":
                    return EmployeeStatus.INACTIVE;
                case "T":
                    return EmployeeStatus.TERMINATED;
                default:
                    throw new IllegalArgumentException("Unknown database value: " + dbData);
            }
        }
    }
    ```

24. What is the purpose of the `@Temporal` annotation?
    - The @Temporal annotation is used to specify how Java date and time types (like java.util.Date or java.util.Calendar) should be mapped to database columns.
    - `TemporalType.DATE`: Stores only the date part (no time).
    - `TemporalType.TIME`: Stores only the time part (no date).
    - `TemporalType.TIMESTAMP`: Stores both date and time (full timestamp).
    
    ```java
    @Temporal(TemporalType.DATE)  // Store only the date part
        private Date birthDate;
    ```
    -   Starting with Java 8, it is recommended to use java.time API (e.g., LocalDate, LocalTime, LocalDateTime), which provides better handling of date/time types and doesn’t require @Temporal.
    ```java
    private LocalDate eventDate;  // No @Temporal needed
    private LocalDateTime eventTimestamp;
    ```

25. What is the purpose of `Session.evict()` and `Session.clear()`?
    - Hibernate maintains a cache of persistent objects in the session, known as the first-level cache. 
    - Sometimes, it is necessary to remove entities from the session cache to free memory or avoid stale data.
    - `Session.evict(Object entity)`: Removes a specific entity from the session cache.
    - `Session.clear()`: Removes all entities from the session cache.

26. How do you create an immutable entity?
    - An immutable entity in Hibernate is an entity whose state cannot be changed after it has been persisted. 
    - Once the entity is saved in the database, any attempts to modify its fields will not trigger an UPDATE SQL operation.
    - 1. Use the `@Immutable` annotation on the entity class.
      2. Ensure all the fields are final or private (optional for clarity).
      3. Avoid setter methods for the entity's fields (or make fields final if possible).

27. What is the difference between `flush()` and `commit()`?
    - The flush() method in Hibernate synchronizes the state of the persistence context (session cache) with the database. However, it does not commit the transaction. 
    - After flush(), the changes are only written to the database, but they are not finalized until the transaction commits.
    - The commit() method commits the current transaction and finalizes all changes. Any changes made during the transaction are persisted permanently in the database. 
    - After commit(), the transaction is completed, and rollback is no longer possible.

28. What are Hibernate filters, and how are they used?
29. What is the purpose of `@SecondaryTable`?
30. How do you implement soft deletes in Hibernate?
31. What is the role of `@NaturalId` in Hibernate?
32. How do you troubleshoot lazy initialization exceptions?
33. What is the n+1 select problem, and how do you resolve it?
34. How do you handle orphan entities?
35. What is the role of `StatelessSession` in Hibernate?
36. How do you optimize HQL queries?
37. What is the purpose of `@ElementCollection`?
38. How does Hibernate detect cyclic dependencies?
39. What are the risks of using native SQL queries with Hibernate?
40. How do you monitor Hibernate performance?
41. How does Hibernate handle transactions across multiple sessions?
42. How do you configure multiple datasources with Hibernate?
43. How do you implement auditing with Hibernate Envers?
44. What is multi-tenancy, and how is it configured in Hibernate?
45. How do you implement pessimistic locking?
46. What is Hibernate Search, and how does it work?
47. How do you handle large result sets in Hibernate?
48. What are sharding strategies in Hibernate?
49. What is the role of `Session.lock()`?
50. How do you implement custom `UserType` in Hibernate?
51. What are dynamic models, and how do they differ from static models?
52. How do you configure clustered caching for large applications?
53. How does Hibernate work with NoSQL databases?
54. How does Hibernate support soft deletes and auditing simultaneously?
55. What are FetchModes, and how do they affect performance?
56. How do you resolve deadlocks in Hibernate?
57. How do you design a scalable application using Hibernate?
58. How do you manage multi-threaded transactions in Hibernate?
59. How do you handle schema updates during deployment?
60. What are some pitfalls of using HQL over native SQL?
61. How do you integrate Hibernate with Kafka?
62. How do you implement bulk updates?
63. How does Hibernate support distributed transactions?
64. What is the impact of flush modes on performance?
65. How do you implement version control for entities?
66. How do you tune Hibernate for high-performance applications?
67. How does Hibernate implement soft locking?
68. What are the challenges of migrating legacy systems to Hibernate?
69. How do you implement a multi-level cache?
70. How do you handle concurrency issues with optimistic locking?
71. What are some best practices for using Hibernate in production?

