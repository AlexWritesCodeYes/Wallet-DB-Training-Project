package org.cap.Wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(urlPatterns = { "/MainServlet", "/main" },
initParams = {@WebInitParam(name = "teamName",value = "DevSupportTeam")})
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	String email=null,name=null,teamName=null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context=config.getServletContext();
		email=context.getInitParameter("email");
		name=context.getInitParameter("devName");
		
		teamName=config.getInitParameter("teamName");
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		PrintWriter out=response.getWriter();
		out.println("<h1>Welcome!"+ session.getAttribute("userName")+" You are authorized USer!");
		out.println("<h3>Email : "+ email+" </h3>");
		out.println("<h3>DevName : "+ name+" </h3>");
		out.println("<h3>TeamName : "+ teamName+" </h3>");
		
		
		Cookie[] cookies= request.getCookies();
		for(Cookie cookie:cookies)
			out.println(cookie.getName() + "-->" + cookie.getValue());
		
		
		out.println("<a href='LogoutServlet'>Logout</a>");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	
	
}
