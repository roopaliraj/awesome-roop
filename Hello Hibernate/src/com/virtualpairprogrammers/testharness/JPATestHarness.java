package com.virtualpairprogrammers.testharness;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.virtualpairprogrammers.domain.Student;
import com.virtualpairprogrammers.domain.Subject;
import com.virtualpairprogrammers.domain.Tutor;

public class JPATestHarness 
{
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");

	public static void main(String[] args)
	{		
		setUpData();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// let's do some queries!
		Query q = em.createQuery("from Student as student where student.name like '%e%'");
		List<Student> allStudents = q.getResultList();
		for(Student next: allStudents){
			System.out.println(next);
		}
		tx.commit();
		em.close();
	}
	
	public static void setUpData()
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// Some subjects
		Subject mathematics = new Subject("Mathematics", 2);
		Subject science = new Subject("Science", 2);
		Subject history = new Subject("History", 3);
		em.persist(mathematics);
		em.persist(science);
		em.persist(history);
		
		Student student1 = new Student("Marco Fortes", "1-FOR-2010", "1 The Street", "Anytown", "484848");
		Student student2 = new Student("Kath Grainer", "2-GRA-2009", "2 Kaths Street", "Georgia", "939393");
		em.persist(student1);
		em.persist(student2);

		// This tutor will be very busy, with lots of students
		Tutor t1 = new Tutor("ABC123", "David Banks", 2939393);
		t1.addTutorToSubjectGroup(mathematics);
		t1.addTutorToSubjectGroup(science);
		t1.addStudentToSupervisionGroup(student1);
		
		// This tutor is new and has no students
		// But he will be able to teach science and mathematics
		Tutor t2 = new Tutor("DEF456", "Alan Bridges", 0);
		t2.addTutorToSubjectGroup(mathematics);
		t2.addTutorToSubjectGroup(science);
		t2.addStudentToSupervisionGroup(student2);
		
		// This tutor is the only tutor who can teach History
		Tutor t3 = new Tutor("GHI678", "Linda Histroia", 0);
		t3.addTutorToSubjectGroup(history);
		
		em.persist(t1);
		em.persist(t2);
		em.persist(t3);

	
		tx.commit();
		em.close();
	}

	
}  
