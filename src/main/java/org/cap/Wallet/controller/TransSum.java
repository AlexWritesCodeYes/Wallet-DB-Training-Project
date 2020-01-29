package org.cap.Wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.Wallet.model.Account;
import org.cap.Wallet.model.Transaction;
import org.cap.Wallet.model.User;
import org.cap.Wallet.service.AccountServiceImpl;
import org.cap.Wallet.service.IAccountService;

/**
 * Servlet implementation class TransSum
 */
@WebServlet("/TransSum")
public class TransSum extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = LoginServlet.userTemp;
		PrintWriter out = response.getWriter();
		Account acc = new Account();
		IAccountService accServ = new AccountServiceImpl();
		String accId = request.getParameter("accId");
		
		acc.setAccountID(accId);
		
		List<Transaction> tList = accServ.getTransactionSummary(acc, user);
		if(tList != null) {
			response.sendRedirect("pages/main.html");
			response.getWriter().println("Success");
		}else {
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
