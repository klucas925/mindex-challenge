package com.mindex.challenge.data;

/**
 * @author Keith D Lucas
 *
 */
public class ReportingStructure {
	
	Employee employee;
	Integer numberOfReports; 

	public ReportingStructure (Employee employee, Integer numberOfReports) {
		this.employee = employee;
		this.numberOfReports = numberOfReports;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Integer getNumberOfReports() {
		return numberOfReports;
	}
	
	public void setNumberOfReports(Integer numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
	
}
