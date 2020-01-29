package org.cap.Wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.Wallet.model.Account;
import org.cap.Wallet.model.User;
import org.cap.Wallet.service.AccountServiceImpl;
import org.cap.Wallet.service.IAccountService;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		Account acc = new Account();
		PrintWriter out = response.getWriter();
		IAccountService accServ = new AccountServiceImpl();
		user = LoginServlet.userTemp;
		String accId = request.getParameter("accId");
		String amount = request.getParameter("amount");
		String descrip = request.getParameter("descrip");
		String type = request.getParameter("transType");
		
		acc.setAccountID(accId);
		
//		Account acc2 = accServ.withdraw(Integer.parseInt(type), Double.parseDouble(amount), 
//				descrip, acc, user);
		Account acc2 = accServ.withdraw(acc, Double.parseDouble(amount));
		
		if( acc2 != null) {
			response.sendRedirect("pages/main.html");
			response.getWriter().println("Success");
		}
		else {
			if(user == null) {
				out.println("<b>No user</b>");	
			}
			else {
				out.println("<b>No acc</b>");
				out.println(acc.getAccountID());
			}
		}
	}

}
