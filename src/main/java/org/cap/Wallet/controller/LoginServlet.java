package org.cap.Wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cap.Wallet.model.User;
import org.cap.Wallet.service.IUserService;
import org.cap.Wallet.service.UserServiceImpl;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static User userTemp;
	String email=null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context=config.getServletContext();
		email=context.getInitParameter("email");
	}

	protected String getRequestParameter(HttpServletRequest request, String name) {
		String param = request.getParameter(name);
		return !param.isEmpty() ? param : getInitParameter(name);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("userName", getRequestParameter(request, "userName"));
		request.setAttribute("passWord", getRequestParameter(request, "passWord"));
		
		System.out.println("Emails" + email);
		
		response.setContentType("text/html");
		
		IUserService loginService=new UserServiceImpl();
		PrintWriter out=response.getWriter();
		
		String userName=request.getParameter("userName");
		String userPwd=request.getParameter("passWord");
		
		String email=request.getParameter("email");
		System.out.println("email got: "+email);
		
		User login=new User();
		login.setEmailId(userName);
		login.setPassword(userPwd);
		System.out.println("id: "+login.getEmailId());
		
		response.addCookie(new Cookie("username", userName));
		response.addCookie(new Cookie("userpassword", userPwd));
		//TODO: get this working and to stop deleting the database
		User validlogin=loginService.logInUser(login.getEmailId(), login.getPassword());
		if(validlogin!=null) {
			HttpSession session=request.getSession();
			//session.setMaxInactiveInterval(1000);
			session.setAttribute("user", validlogin);
			//userTemp = validlogin;
				System.out.println("login success");
				response.sendRedirect("pages/main.html");
			//request.getRequestDispatcher("main").forward(request, response);
			}else {
				out.println("<b>Sorry!Invalid Login</b>");
				System.out.println("invalid login ");
				//request.getRequestDispatcher("index.html").include(request, response);
				response.sendRedirect("pages/index.html");
			}
		
	}

}
