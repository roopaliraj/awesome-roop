package com.virtualpairprogrammers.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Subject {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(unique=true,nullable=false)
	private String subjectName;
	
	private int numberOfSemesters;
	
	@ManyToMany
	private Set<Tutor> tutorGroup;
	
	public Subject(){}
	
	public Subject( String subjectName, int numberOfSemesters) {
		super();
		this.subjectName = subjectName;
		this.tutorGroup = new HashSet<Tutor>();
		this.numberOfSemesters = numberOfSemesters;

	}
	public void addTutorToSubject(Tutor tutor){
		this.tutorGroup.add(tutor);
	}
	public String toString(){
		return this.subjectName + "last for "+this.numberOfSemesters + "semesters";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subjectName == null) ? 0 : subjectName.hashCode());
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
		Subject other = (Subject) obj;
		if (subjectName == null) {
			if (other.subjectName != null)
				return false;
		} else if (!subjectName.equals(other.subjectName))
			return false;
		return true;
	}
	
}
