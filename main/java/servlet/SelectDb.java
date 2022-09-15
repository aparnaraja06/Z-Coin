package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import operation.CoinOperation;
import operation.CreateInstance;
import operation.CustomException;
import operation.ErrorMsg;


public class SelectDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		 
		
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession();
		
		String name =  request.getParameter("name");
		
		try
		{
			CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();
			
			String msg = "error";
			
			if(name.equals("mysql"))
			{
				coin.setMysql();
				coin.createDatabase();
				coin.createTable();
				
			}
			else if(name.equals("psql"))
			{
				
				coin.setPsql();
				
				boolean check_int = coin.checkDomainInteger();
				boolean check_mail = coin.checkDomainMail();
				
				if(!check_int)
				{
					coin.createDomainInteger();
				}
				else if(!check_mail)
				{
					coin.createDomainMail();
				}
				
				coin.createDatabasePsql();
				coin.createTable();
				coin.createSequence();
				

			}
			else
			{
			session.setAttribute("Error", msg);
			
			out.print(msg);
			
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
