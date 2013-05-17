package com.virtualpairprogrammers.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Tutor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(unique=true,nullable=false)
	private String staffId;
	
	private String name;
	private int salary;

	@OneToMany(mappedBy="supervisor")
	private Set<Student> supervisionGroup;
	
	@ManyToMany(mappedBy="tutorGroup")
	private Set<Subject> subjectGroup;
	
	public Tutor(){}

	public Tutor(String staffId, String name, int salary) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
		this.supervisionGroup = new HashSet<Student>();
		this.subjectGroup = new HashSet<Subject>();
	}
	
	public void addTutorToSubjectGroup(Subject subject){
		this.subjectGroup.add(subject);
	}
	public void addStudentToSupervisionGroup(Student studentToAdd){
		this.supervisionGroup.add(studentToAdd);
		studentToAdd.allocateSupervisor(this);
	}
	public Set<Student> getSupervisionGroup(){
		Set<Student> unmodifiable =	Collections.unmodifiableSet(this.supervisionGroup);
		return unmodifiable;
	}
	public Set<Student> getModifiableSupervisionGroup(){
		return this.supervisionGroup;
	}
	public String getName() {
		return this.name;
	}
	public String toString(){
		return "Tutor : "+this.name+"("+ this.staffId + ")";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tutor other = (Tutor) obj;
		if (staffId == null) {
			if (other.staffId != null)
				return false;
		} else if (!staffId.equals(other.staffId))
			return false;
		return true;
	}


}
