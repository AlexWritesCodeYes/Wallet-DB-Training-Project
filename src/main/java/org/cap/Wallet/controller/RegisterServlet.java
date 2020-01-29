package org.cap.Wallet.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.Wallet.model.Address;
import org.cap.Wallet.model.User;
import org.cap.Wallet.service.IUserService;
import org.cap.Wallet.service.UserServiceImpl;
//import org.omg.CORBA.RepositoryIdHelper;

/**
 * Servlet implementation class RegisterServlet
 */
/*@WebServlet(urlPatterns = { "/FirstServlet", "/hello" },
		initParams = {@WebInitParam(name = "color",value = "red"),
				@WebInitParam(name = "shape",value = "circle")}
		)*/
@WebServlet(urlPatterns = {"/RegisterServlet", "/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserService userService=new UserServiceImpl();
		Address address=new Address();
		User user=new User();
		
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String password=request.getParameter("password");
		String emailId=request.getParameter("emailId");
		String dob=request.getParameter("dob");
		String ssn=request.getParameter("ssn");
		String houseNumber=request.getParameter("houseNumber");
		String streetName=request.getParameter("streetName");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String zipcode=request.getParameter("zipcode");
		String country=request.getParameter("country");
		
		address.setHouseNumber(houseNumber);
		address.setStreetName(streetName);
		address.setCity(city);
		address.setState(state);
		address.setZipcode(zipcode);
		address.setCountry(country);
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setAddress(address);
		user.setEmailId(emailId);
		user.setSSN(ssn);
		user.setDOB(LocalDate.parse(dob));
		
		User user2=userService.registerUser(user);
		if(user2!=null) {
			response.getWriter().println("Registration Completed!");
			response.sendRedirect("pages/main.html");
		}
	}

}
