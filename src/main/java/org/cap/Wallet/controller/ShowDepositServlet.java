package org.cap.Wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cap.Wallet.model.Account;
import org.cap.Wallet.model.User;
import org.cap.Wallet.service.AccountServiceImpl;
import org.cap.Wallet.service.IAccountService;


@WebServlet("/ShowDepositServlet")
public class ShowDepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		
		IAccountService accServ = new AccountServiceImpl();
		List<Account> accounts= accServ.getAccounts(user);
		
		PrintWriter out=response.getWriter();
		out.println(""
				+ "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Insert title here</title>\r\n" + 
				"\r\n" + 
				"<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n" + 
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/mainPage.css\">\r\n" + 
				" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\r\n" + 
				"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
				"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\r\n" + 
				"</head>\r\n" + 
				"<body style=\"background-color: #62AAF4;\">\r\n" + 
				"\r\n" + 
				"<nav class=\"navbar navbar-inverse navbar-fixed-top\">\r\n" + 
				"  <div class=\"container-fluid\">\r\n" + 
				"    <div class=\"navbar-header\">\r\n" + 
				"      <a class=\"navbar-brand\" href=\"#\">Wallet</a>\r\n" + 
				"    </div>\r\n"
				+ "<ul class=\"nav navbar-nav\">\r\n" + 
				"      <li class=\"active\"><a href=\"main.html\">Home</a></li>\r\n" + 
				"      <li><a href=\"createAcc.html\">Create Account</a></li>\r\n" + 
				"      <li><a href=\"../ShowDepositServlet\">Deposit</a></li>\r\n" + 
				"      <li><a href=\"withd.html\">Withdraw</a></li>\r\n" + 
				"      <li><a href=\"transSumm.html\">Transaction Summary</a>\r\n" + 
				"      <li><a href=\"transFund.html\">Transfer Fund</a>\r\n" + 
				"      <li><a href=\"../LogoutServlet\">Logout</a>\r\n" + 
				"    </ul>"+				
				"  </div>  \r\n" + 
				"</nav>\r\n" + 
				"  <div class=\"container\" style=\"margin-top:150px;\">\r\n" + 
				"    <div class=\"row\">\r\n" + 
				"      <div class=\"col-lg-6\">\r\n" + 
				"        <i class=\"fa fa-bank\" style=\"font-size:360px\"></i>\r\n" + 
				"  	  </div>\r\n" + 
				"  	<div class=\"col-lg-4\" style=\"margin-top:50px\">\r\n" + 
				"	  <form class=\"form-inline\" method=\"post\" action=\"DepositServlet\">\r\n" + 
				"	  	<h2>Account Deposit</h2>\r\n" + 
				"	  	<table>\r\n" + 
				"	  	  <tr>\r\n" +
				"	  	  	<td><label for=\"accId\">Account Id: </label></td>\r\n	<td>");
				
				//+ "<input type=\"text\" class=\"form-control\" id=\"accId\" name=\"accId\" size=\"20\"></td>\r\n" + 
			
				out.println("<select name=\"accId\">");
				for(Account account:accounts) {
					out.println("<option value="+account.getAccountID()+">"+account.getAccountID()+"</option>");
				}
				out.println( "</select>");
		
		
		
		
		
		out.println("	  	  </tr>\r\n" + 
				"	  	  <tr>\r\n" + 
				"	  	  	<td><label for=\"amount\">Deposit amount: </label></td>\r\n" + 
				"	  	    <td><input type=\"number\" class=\"form-control\" id=\"amount\" name=\"amount\" step=\"0.01\" min=\"0\" placeholder=\"input deposit amount\"></td>\r\n" + 
				"	  	  </tr>\r\n" + 
				"	  	   <tr>\r\n" + 
				"  			<td><label for=\"descrip\">Transaction Description</label></td>\r\n" + 
				"  			<td><input type=\"text\" class=\"form-control\" id=\"descrip\" name=\"descrip\" size=\"30\">\r\n" + 
				" 			</td>\r\n" + 
				" 		  </tr>\r\n" + 
			
				" 		  <tr>\r\n" + 
				"  			<td></td>		\r\n" + 
				" 			<td><button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n" + 
				"  			<button type=\"reset\" class=\"btn btn-primary\">Cancel</button>\r\n" + 
				"			</td>\r\n" + 
				"		  </tr>\r\n" + 
				"	  	</table>\r\n" + 
				"  	  </form>\r\n" + 
				"  	</div>\r\n" + 
				"  	</div>\r\n" + 
				"  </div>\r\n" + 
				"</body>\r\n" + 
				"</html>"
				+ ""
				+ "");
		
	}

}
