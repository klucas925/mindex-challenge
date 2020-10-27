package com.mindex.challenge.service;

import java.util.List;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    
    ReportingStructure report(String id);
    List<Employee> list();

    Compensation createCompensation(Compensation compensation);
	Compensation readCompensation(String id);

}
