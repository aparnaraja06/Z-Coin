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
import operation.ErrorMsg;


public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html");
		 
		 HttpSession session = request.getSession();
			
			String name =  request.getParameter("name");
			
			String msg = "error";
			
			try
			{
				
				
				CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();
				
				Checker check = CreateInstance.COINOPERATION.getCheckInstance();
				
				String type = request.getParameter("type");
				
				check.validateAmount(name);
				
				double amount = Double.parseDouble(name);
				
				if(type.equals("set"))
				{
				
				double amountt=coin.changeZCoinRate(amount);
				
				
				if(amount!=amountt)
				{
                   session.setAttribute("Error", msg);
					

				}
				}
				else
				{
					int id = (int)session.getAttribute("user_id");
					
					int acc_num = coin.getAccountNumById(id);
					
					boolean result = coin.buyZCoin(id,acc_num, amount);
					
					if(!result)
					{
						 session.setAttribute("Error", msg);
					}
				}
			}
			catch(CustomException e)
			{
				
				String err_msg=e.getMessage();
				
				ErrorMsg err = ErrorMsg.valueOf(err_msg);
				
				int code = err.getCode();
				
				response.sendError(code);
				
			}
			
				
	}

}
