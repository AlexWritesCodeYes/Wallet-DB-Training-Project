package org.cap.Wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GreetServlet")
public class GreetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		PrintWriter out=response.getWriter();
		out.println("<h1>Welcome!"+ session.getAttribute("userName")+" You are authorized USer!");
	

		String user=request.getParameter("userName");
		String userPwd=request.getParameter("userPwd");
		//PrintWriter out=response.getWriter();
		out.println("<h1>Good Afternoon! "+user+"</h1>");
		out.println("<h3>Password! "+userPwd+"</h3>");
		
	}

}
