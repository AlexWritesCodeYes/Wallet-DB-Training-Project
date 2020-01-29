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
@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		Account acc1 = new Account();
		Account acc2 = new Account();
		PrintWriter out = response.getWriter();
		IAccountService accServ = new AccountServiceImpl();
		user = LoginServlet.userTemp;
		String acc1Id = request.getParameter("acc1Id");
		String acc2Id = request.getParameter("acc2Id");
		String amount = request.getParameter("amount");
		String descrip = request.getParameter("descrip");
		String type = request.getParameter("transType");
		
		acc1.setAccountID(acc1Id);
		acc2.setAccountID(acc2Id);
		
//		Account acc2 = accServ.withdraw(Integer.parseInt(type), Double.parseDouble(amount), 
//				descrip, acc, user);
		//Account acc2 = accServ.withdraw(acc, Double.parseDouble(amount));
		boolean transferSuccess = accServ.transferFunds(Double.parseDouble(amount), acc1, acc2, user);
		
		if( transferSuccess ) {
			response.getWriter().println("Success");
			response.sendRedirect("pages/main.html");
		}
		else {
			if(user == null) {
				out.println("<b>No user</b>");	
				response.sendRedirect("pages/main.html");
			}
			else {
				out.println("<b>Not enough accounts</b>");
				response.sendRedirect("pages/main.html");
				//out.println(acc1.getAccountID());
			}
		}
	}

}
