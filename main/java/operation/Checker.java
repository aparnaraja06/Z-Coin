package operation;

import java.util.regex.Matcher;

import java.util.regex.Pattern;


public class Checker {
	
	public void checkString(String name)throws CustomException
	{
		if(name==null || name.isEmpty())
		{
			throw new CustomException("EMPTY"); // No I18N
		}
	}
	
	public void checkInteger(String number)throws CustomException
	{
		boolean result= false;
		
		checkString(number);
		
		result = number.matches("[0-9]+");
		
		if(!result)
		{
			
			throw new CustomException("NUMBER");
		}
		
		long num = Long.valueOf(number);
		
		
		if(num <= 0)
		{
			
			throw new CustomException("NUMBER");
		}
	}
	
	public void validatePassword(String password,String name,String mobile,String mail)throws CustomException
	{
		boolean result=false;
		
		checkString(name);
		checkString(password);
		checkString(mail);
		checkString(mobile);
		checkInteger(mobile);
		
		if(password.length()<8)
		{
			throw new CustomException("ALPHABETS");
		}
		
		
		if(password.contains(name) || password.contains(mail) || password.contains(mobile))
		{
			throw new CustomException("ALPHABETS");
		}
		
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        
        result=matcher.find();
				
		if(!result)
		{
			throw new CustomException("ALPHABETS");
		}
	}
	
	public void validateAmount(String amount)throws CustomException
	{
		boolean result= false;
		
		checkString(amount);
		checkInteger(amount);
		
		Pattern pattern = Pattern.compile("[0-9]+([,.][0-9]{1,2})?");
		Matcher matcher = pattern.matcher(amount);
		
		result =  matcher.find();
		
		if(!result)
		{
			throw new CustomException("AMOUNT");
		}
		
		double price = Double.parseDouble(amount);
	
		
		if(price < 0.0)
		{
			throw new CustomException("AMOUNT");
		}
		
		
	}
	
	
	public void validateMail(String mail)throws CustomException
	{
          boolean result= false;
          
         checkString(mail);
          
		
		Pattern pattern = Pattern.compile("^[A-Za-z0-9+-._]+@(.+)$");
		Matcher matcher = pattern.matcher(mail);
		
         result = matcher.find();
         
		
		if(!result)
		{
			throw new CustomException("MAIL_ID");
		}
		
	}
	
	public void validateMobile(String mobile)throws CustomException
	{
		boolean result= false;
        
        checkString(mobile);
        
        if(mobile.length()!=10)
        {
        	throw new CustomException("MOBILE");
        }
        
       checkInteger(mobile);
        
		Pattern pattern = Pattern.compile("[7-9][0-9]{9}");
		Matcher matcher = pattern.matcher(mobile);
		
       result = matcher.find();
       
       if(!result)
		{
			throw new CustomException("MOBILE");
		}
	}
	
	public void validateHumanId(String human_id)throws CustomException
	{
         boolean result= true;
        
       checkString(human_id);
        
        if(human_id.length()!=12)
        {
        	result= false;
        }
        
       checkInteger(human_id);
       
       if(!result)
		{
			throw new CustomException("HUMAN_ID");
		}

	}

}
