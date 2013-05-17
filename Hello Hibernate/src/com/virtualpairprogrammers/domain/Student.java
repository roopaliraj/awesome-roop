package com.virtualpairprogrammers.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a Student enrolled in the college management
 * system (CMS)
 */
@Entity
public class Student
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;
	
	@Column(unique=true,nullable=false)
	private String enrollmentID;
	
    private String name;

    @ManyToOne
    @JoinColumn(name="TUTOR_FK")
    private Tutor supervisor;
    @Embedded
    private Address address;

    public Student(){}
    
      
    public Student(String name,String enrollmentID,String street,String city,
    		String postCode)
    {
    	this.name = name;
    	this.enrollmentID=enrollmentID;
    	this.address = new Address(city, street, postCode);
    	this.supervisor = null;
    }

   
	public double calculateGradePointAverage()
    {
    	return 0;
    }
    public void allocateSupervisor(Tutor newSupervisor){
    	this.supervisor = newSupervisor;
    	newSupervisor.getModifiableSupervisionGroup().add(this);
    }
    
    public String toString(){
    	return this.name + " lives at : " + this.address;
    }
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnrollmentID() {
		return enrollmentID;
	}
	public void setEnrollmentID(String enrollmentID) {
		this.enrollmentID = enrollmentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSupervisorName(){
		return this.supervisor.getName();
	}
	 @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((enrollmentID == null) ? 0 : enrollmentID.hashCode());
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
			Student other = (Student) obj;
			if (enrollmentID == null) {
				if (other.enrollmentID != null)
					return false;
			} else if (!enrollmentID.equals(other.enrollmentID))
				return false;
			return true;
		}

}
