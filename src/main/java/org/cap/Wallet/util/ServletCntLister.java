package org.cap.Wallet.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletCntLister
 *
 */
@WebListener
public class ServletCntLister implements ServletContextListener {

 
  
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext ctx=sce.getServletContext();
    	ctx.setInitParameter("email", "capgemini@info.com");
		ctx.setInitParameter("devName", "capgDevteam");
    }
	
}
