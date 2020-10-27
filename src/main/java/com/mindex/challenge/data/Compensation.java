package com.mindex.challenge.data;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Keith D Lucas
 *
 */
@Document
public class Compensation {

	@DBRef
	private Employee employee;
	
	// Salary format not specified in requirements, will go with Integer
	private Integer salary; 

	// Date format not specified in requirements, will go with MM/dd/yyyy
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date effectiveDate;

    public Compensation() {
    }
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Integer getSalary() {
		return salary;
	}
	
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	@Override
	public String toString() {
		return "Compensation [employee=" + employee + ", salary=" + salary + ", effectiveDate=" + effectiveDate + "]";
	}
}
