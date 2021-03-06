package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
import org.junit.After;
import org.junit.Before;
*/
import com.revature.dao.EmployeeDao;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTest {

	private EmployeeService eserv;
	private EmployeeDao mockdao;
	
	@Before
	public void startup() {
		
		mockdao = mock(EmployeeDao.class);
		eserv=new EmployeeService(mockdao);
	}
	
	
	@After
	public void teardown() {
		
		mockdao = null;
		eserv= null;
	}
	
	@Test
	public void testConfirmLogin_success() {
		Employee e1 = new Employee(20, "Bruce", "Banner", "TheHulk", "Green");
		Employee e2 = new Employee(21, "Clint", "Barton", "Hawkeye", "arrows");
		List<Employee> employees = new ArrayList();
		employees.add(e2);
		employees.add(e1);
		
		when(mockdao.findAll()).thenReturn(employees);
		
		Employee actual = eserv.confirmLogin("Hawkeye", "arrows");
		Employee expected =e2;
		
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void testConfirmLogin_fail() {
		Employee e1 = new Employee(20, "Bruce", "Banner", "TheHulk", "Green");
		Employee e2 = new Employee(21, "Clint", "Barton", "Hawkeye", "arrows");
		List<Employee> employees = new ArrayList();
		employees.add(e2);
		employees.add(e1);
		
		when(mockdao.findAll()).thenReturn(employees);
		
		Employee actual = eserv.confirmLogin("Hawkeye", "bob");
		Employee expected =e2;
		
		assertNull(actual);
		
		
	}
	
	
	
}
