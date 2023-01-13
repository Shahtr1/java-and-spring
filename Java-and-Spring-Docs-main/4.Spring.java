Q1 -> What is Cascade?
A  -> We can cascade operations
	Apply the same operations to related entities.
	If we save one, it will also apply the same to related one.
	Also if we delete an instructor, we should also delete their instructor detail.
	
Q2 -> What are Fetch Types: Eager vs Lazy Loading?
A  -> When we fetch / retrieve data, should we retrieve everything?
	EAGER will retrieve everything
	LAZY will retrieve on request
	
Q3 -> What is an Entity Lifecycle?
A  -> a. Transient State
		1.	The transient state is the initial state of an object.
		2.	Once we create an instance of POJO class, then the object entered in the transient state.
		3.	Here, an object is not associated with the Session. So, the transient state is not related to any database.
		4.	Hence, modifications in the data don't affect any changes in the database.
		5.	The transient objects exist in the heap memory. They are independent of Hibernate.
		
		e.g.,
			Employee e=new Employee(); //Here, object enters in the transient state.  
			e.setId(101);  
			e.setFirstName("Gaurav");  
			e.setLastName("Chawla");  
		
	b.	Persistent state
		1.	As soon as the object associated with the Session, it entered in the persistent state.
		2.	Hence, we can say that an object is in the persistence state when we save or persist it.
		3.	Here, each object represents the row of the database table.
		4.	So, modifications in the data make changes in the database.
		
		e.g.,
			session.save(e);  
			session.persist(e);  
			session.update(e);  
			session.saveOrUpdate(e);  
			session.lock(e);  
			session.merge(e);  
	c.	Detached State
		1.	Once we either close the session or clear its cache, then the object entered into the detached state.
		2.	As an object is no more associated with the Session, modifications in the data don't affect any changes in the database.
		3.	However, the detached object still has a representation in the database.
		4.	If we want to persist the changes made to a detached object, it is required to reattach the application to a valid Hibernate session.
		5.	To associate the detached object with the new hibernate session, use any of these methods - 
			load(), merge(), refresh(), update() or save() on a new session with the reference of the detached object.
		
		e.g.,
			session.close();  
			session.clear();  
			session.detach(e);  
			session.evict(e); 
		
		
Q4 -> What are @OneToOne - Cascade Types?
A  -> Persist:
		If entity is persisted / saved, related entity will also be persisted
	Remove:
		If entity is removed / deleted, related entity will also be deleted
	Refresh:
		if entity is refreshed, related entity will also be refreshed
	Detach:
		If entity is detached ( not associated w/ session ), then related entity will also be detached
	Merge:
		if entity is merged, then related entity will also be, merged
	All:
		All of above cascade types
	
	By default, no operations are cascaded
	
Q5 -> Define mappedBy?
A  -> mappedBy tells Hibernate
	public class InstructorDetail {
		...	
		@OneToOne(mappedBy = "instructorDetail", cascade=CascadeType.ALL)
		private Instructor instructor;
	}
	
	public class Instructor {
		...
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="instructor_detail_id)
		private InstructorDetail instructorDetail;
	}
	
	1.	Look at the instructorDetail property in the Instructor class
	2.	Use information from the Instructor class @JoinColumn
	3.	To help find associated instructor
	

	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

 






















	
	

	
	
	
	
	
	
	
	
	
	
	
	