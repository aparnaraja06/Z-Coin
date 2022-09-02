package servlet;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import operation.Checker;
import operation.CustomException;
import operation.CreateInstance;
import operation.CoinOperation;
import user.User;
import operation.ErrorMsg;


/**
 * Servlet implementation class ChangePassword
 */

public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
         response.setContentType("text/html"); // No I18N
		
         PrintWriter out=response.getWriter();
		
		String old=request.getParameter("old");
				
		HttpSession session=request.getSession();
		int user_id=(int)session.getAttribute("user_id");
		
		try
		{
			CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();
			
			Checker check = CreateInstance.COINOPERATION.getCheckInstance();
			
			String pass = coin.getPassword(user_id);
			
			User user = coin.getUser(user_id);
			
			String name = user.getName();
			long mobile = user.getMobile();
			String number = String.valueOf(mobile);
			String mail = user.getMail();
			
			try
			{
			check.checkString(old);
			}
			catch(CustomException e)
			{
				throw new CustomException("PASSWORD");
			}
			String invalid = "Incorrect password";
			
			if(pass.equals(old))
			{
				String new1 = request.getParameter("new1");
				
				String new2 = request.getParameter("new2");
				
				try
				{
				check.checkString(new1);
				check.checkString(new2);
				}
				catch(CustomException e)
				{
					throw new CustomException("PASSWORD");
				}
				
				check.validatePassword(new1,name,number,mail);
				check.validatePassword(new2,name,number,mail);
				
				String msg = "Password doesn't match";
				
				
				if(new1.equals(new2))
				{
					coin.changePassword(new2, user_id);
					
				}
				else
				{
					session.setAttribute("Error",msg);
					
					out.print(msg);
				}
			}
			else
			{
				session.setAttribute("Error", invalid);
				
				out.print(invalid);
			}
		}
		catch(CustomException e)
		{
			
			String msg=e.getMessage();
			
			
			ErrorMsg err = ErrorMsg.valueOf(msg);
			
			int code = err.getCode();
			
			response.sendError(code);
			
		}
	}

}
