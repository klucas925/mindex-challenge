package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        // TODO Add some custom error handling

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
    
    @Override
    public ReportingStructure report(String id) {
    	
    	// Retrieve employee from mongodb using employee ID
    	Employee employee = employeeRepository.findByEmployeeId(id);
    	
    	// TODO Add custom error handling
    	
    	// Verify employee exists
    	if (employee == null) {
    		throw new RuntimeException ("Invalid employeeId: " + id);
    	}
    	
    	// Retrieve number of reports
    	int numberOfReports = numOfReports(employee);
    	
    	return new ReportingStructure(employee, numberOfReports);
    }
    
    private int numOfReports(Employee employee) {
    	
    	int numOfDirectReports = 0;
    	
    	// We only need to do work if there are actual direct reports
    	if (employee.getDirectReports().size() > 0 && employee.getDirectReports() != null ) {
    		
    		// Iterate through direct reports
    		for (Employee currentEmployee : employee.getDirectReports()) {
    			
    			// Retrieve info for the direct report employee
    			Employee directReportEmployee = employeeRepository.findByEmployeeId(currentEmployee.getEmployeeId());
    			
    			if (directReportEmployee.getDirectReports() != null && directReportEmployee.getDirectReports().size() > 0) {
    				
    				// Start appending direct report to current employee
    				currentEmployee.setDirectReports(directReportEmployee.getDirectReports());
    				
    				numOfDirectReports += numOfReports(directReportEmployee);
    			}
    			
    			numOfDirectReports++;
    		}
    	}
    	
    	return numOfDirectReports;
    }

	@Override
	public Compensation readCompensation(String id) {

		LOG.debug("Retreiving employee compensation info with employeeId [{}]", id);

		// if the employee id is not valid
		if (!employeeRepository.existsById(id)) {
			throw new RuntimeException("Invalid employeeId: " + id);
		}

		Compensation compensation = compensationRepository.findByEmployeeEmployeeId(id);

		if (compensation == null) {
			throw new RuntimeException("No compensation information found for employeeId: " + id);
		}

		return compensation;
	}

	@Override
	public Compensation createCompensation(Compensation compensation) {

		String employeeId = compensation.getEmployee().getEmployeeId();

		LOG.debug("Creating employee compensation for employeeId [{}]", employeeId);

		// if the employee id is not valid
		if (!employeeRepository.existsById(employeeId)) {
			throw new RuntimeException("Invalid employeeId: " + employeeId);
		}

		return compensationRepository.save(compensation);
	}

	@Override
	public List<Employee> list() {
		return employeeRepository.findAll();
	}
}

