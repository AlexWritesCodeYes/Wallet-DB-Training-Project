package org.cap.Wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/FirstServlet", "/hello" },
		initParams = {@WebInitParam(name = "color",value = "red"),
				@WebInitParam(name = "shape",value = "circle")}
		)
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String email=null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context=config.getServletContext();
		email=context.getInitParameter("email");
		
		Enumeration<String> enumeration= config.getInitParameterNames();
		while(enumeration.hasMoreElements()) {
			String param=enumeration.nextElement();
			System.out.println(param + "-->" + config.getInitParameter(param));
		}
	}





	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("...............Service Method Called..............");
		String msg="Hello! Greeting from Capgemini!";
		PrintWriter out=response.getWriter();
		out.println("<h1>"+msg+"</h1>");
		out.println("<h3>Email : "+ email+" </h3>");
		
		out.println("<a href='GreetServlet?userName=tom&userPwd=tom123'>Greet User!</a");
		
	}





	@Override
	public void destroy() {
		System.out.println("-------------Servlet Destroyed----------------.");
	}
	
	

}
