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
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User user = new User();
		Account acc = new Account();
		PrintWriter out = response.getWriter();
		IAccountService accServ = new AccountServiceImpl();
		//user = LoginServlet.userTemp;
		String accId = request.getParameter("accId");
		String amount = request.getParameter("amount");
		String descrip = request.getParameter("descrip");
		
		
		acc.setAccountID(accId);
		acc.setDescription(descrip);
		
		Account acc2 = accServ.deposit(acc,Double.parseDouble(amount));
		
		if( acc2 != null) {
			response.sendRedirect("pages/main.html");
			//response.getWriter().println("Success");
		
		}
		
	}

}
