package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static ObjectMapper om =new ObjectMapper();

	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		
		
		Employee e =eserv.confirmLogin(username, password);
		
		if(e!=null) {
			System.out.println("Logged in");
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			PrintWriter out = response.getWriter();
			
			response.setContentType("text/html");
			
			out.println("<h1>Welcome "+e.getFirstname()+"!</h1>");
			out.println("<h3>You have succesfully logged in </h3>");
			out.println(om.writeValueAsString(e));
			
			
		}
		else {
			System.out.println("No login");
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			
			response.setContentType("text/html");
			
			out.println("<h3>You have succesfully logged in </h3>");
			response.setStatus(204);
		}
		
		
		
	}
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		
		List<Employee> myBoys=eserv.findAll();
		
		String jsonString = om.writeValueAsString(myBoys);
		
		PrintWriter out=response.getWriter();
		out.println(jsonString);
	}
		
	
}
