package org.cap.Wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CookieDemo
 */
@WebServlet("/CookieDemo")
public class CookieDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		PrintWriter out=response.getWriter();
		out.println("<h1>Welcome!"+ session.getAttribute("userName")+" You are authorized USer!");
	
		Cookie cookie=new Cookie("color", "green");
		
		cookie.setMaxAge(1000);
	
			response.addCookie(cookie);
		
		
		response.getWriter().println("Cookies Added!");
	}

}
