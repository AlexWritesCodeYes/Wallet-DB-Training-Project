package org.cap.Wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import org.cap.Wallet.model.Account;
import org.cap.Wallet.model.User;
import org.cap.Wallet.service.AccountServiceImpl;
import org.cap.Wallet.service.IAccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreatAccServlet
 */
@WebServlet("/CreatAccServlet")
public class CreatAccServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		HttpSession session = request.getSession();
		IAccountService accServ = new AccountServiceImpl();
		
		User user =(User) session.getAttribute("user");
		
		System.out.println(user);
		
		Account acc = new Account();
		
		PrintWriter out = response.getWriter();
		
		String accType = request.getParameter("accType");
		String balance = request.getParameter("balance");
		String descrip = request.getParameter("descrip");
		
		if(accType.equals(Account.AccountType.CHECKINGS.toString())) {
			acc.setAccountType(Account.AccountType.CHECKINGS.getValue());
		}
		else {
			acc.setAccountType(Account.AccountType.SAVINGS.getValue());
		}
		acc.setBalance(Double.parseDouble(balance));
		acc.setDescription(descrip);
		acc.setOpeningDate(LocalDate.now());
		
		
		System.out.println(acc);
		
		Account acc2 = accServ.createAccount(acc, user);
		
		if( acc2 != null) {
			response.getWriter().println("Account create Successfully");
			response.sendRedirect("pages/main.html");
		}
		else {
			if(user == null) {
				out.println("<b>No user</b>");	
			}
			else {
				out.println("<b>No acc</b>");
			}
		}
	}

}
