package com.mindex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/compensation")
public class CompensationController {

	private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/")
	public Compensation create(@RequestBody Compensation compensation) {
		
		// Keeping in line with EmployeeController
		LOG.debug("Received compensation create request for [{}]", compensation.getEmployee().getEmployeeId());
		
		return employeeService.createCompensation(compensation);
	}
	
	@GetMapping("/{id}")
	public Compensation read(@PathVariable String id) {
		
		LOG.debug("Received compensation read request for [{}]", id);	    

		return employeeService.readCompensation(id);		
	}
	
}
