package operation;

public enum ErrorMsg 
{
	  CONNECTION(401),
	  CLOSE_CONNECTION(402),
	  USERNAME(403),
	  PASSWORD(404),
	  MAIL(405),
	  ALPHABETS(406),
	  NUMBER(414),
	  AMOUNT(408),
	  WITHDRAW(409),
	  MAIL_ID(410),
	  HUMAN_ID(411),
	  MOBILE(412),
	  EMPTY(413);
	  
	  private final int code;
	  
	  private ErrorMsg(int code)
	  {
		    this.code = code;
	  }

	  public int getCode() {
	     return code;
	  }
	  
	 
	
}
