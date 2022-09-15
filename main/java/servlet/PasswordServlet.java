package servlet;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import operation.Checker;
import operation.CustomException;
import operation.CreateInstance;
import operation.CoinOperation;
import operation.ErrorMsg;

/**
 * Servlet implementation class PasswordServlet
 */

public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
response.setContentType("text/html");
		
		//CoinOperation coin=(CoinOperation)request.getServletContext().getAttribute("Instance");


		
		String pass1=request.getParameter("pass");
				
		HttpSession session=request.getSession();
		int user_id=(int)session.getAttribute("user_id");
		
		
		PrintWriter out=response.getWriter();
		
		
		
		try
		{
			CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();
			
			Checker check = CreateInstance.COINOPERATION.getCheckInstance();
			
			try
			{
			check.checkString(pass1);
			}
			catch(CustomException e)
			{
				throw new CustomException("PASSWORD");
			}
			
			String pass2=coin.getPassword(user_id);
			
			boolean result=pass1.equals(pass2);
			
			
			if(!result)
			{
				String error="Incorrect password";
				request.setAttribute("Error",error);
				
				
				out.print(error);
				
				
			}
			else
			{
				String role = coin.getRole(user_id);
				
				request.setAttribute("Role",role);
				
				session.setAttribute("Role", role);
				
				out.print(role);
				
			}
		}
		catch(CustomException e)
		{
            String error=e.getMessage();
			
            ErrorMsg err = ErrorMsg.valueOf(error);
			
			int code = err.getCode();
			
			response.sendError(code);
		}
		
		
	}

}
