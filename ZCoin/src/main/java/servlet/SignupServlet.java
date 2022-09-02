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
import user.User;
import operation.ErrorMsg;

/**
 * Servlet implementation class SignupServlet
 */

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*public void init(ServletConfig config)throws ServletException
	   {
		  CoinOperation coin = new CoinOperation();
		  Validate validator = new Validate();
		   config.getServletContext().setAttribute("Instance", coin);
		   config.getServletContext().setAttribute("Validate", validator);
			super.init(config);
	   }
	   */

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
		
		String msg = "Error! Update failed";
		
		try
		{
		CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();
		
		Checker check = CreateInstance.COINOPERATION.getCheckInstance();
		
		try
		{
		check.checkString(name);
		}
		catch(CustomException e)
		{
			throw new CustomException("USERNAME");
		}
		
		if(request.getParameter("type").equals("edit"))
		{
			int id = (int)request.getSession().getAttribute("user_id");
			boolean result=coin.updateName(name, id);
			
			if(!result)
			{
               session.setAttribute("Error", msg);
				
				out.print(msg);
			}
		}
		else
		{
		String mail = request.getParameter("mail");
		String mobile = request.getParameter("mobile");
		String human_id = request.getParameter("human_id");
		String password = request.getParameter("password");
		String amount =  request.getParameter("amount");
	
			
			check.validateMail(mail);
			coin.checkMailExists(mail);
			check.validateMobile(mobile);
			check.validateHumanId(human_id);
			check.validateAmount(amount);
			
			
			check.validatePassword(password, name, mobile, mail);
			
			long mobile_num = Long.parseLong(mobile);
			double rc_amount = Double.parseDouble(amount);
			
			User user = new User();
			
			user.setName(name);
			//user.setMail(mail);
			user.setMobile(mobile_num);
			user.setPassword(password);
			user.setHuman_id(human_id);
			user.setRc_amount(rc_amount);
			
			
			int user_id=coin.addUser(user);
			
			String text="User already Exists";
			
			if(user_id!=0)
			{
				coin.addMail(mail, user_id);
				
				session.setAttribute("user_id", user_id);				
				
			    out.print(user_id);
			}
			else
			{
                session.setAttribute("Error", text);				
				
			    out.print(text);

			}
			
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
