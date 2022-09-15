package coinDb;

import java.sql.Connection;
import java.sql.DriverManager;

import operation.CustomException;

public enum PsqlConnection {
	
	CONNECTION;
	
	private String url="jdbc:postgresql://localhost:5432/z_coin";
	private String username="postgres";
	private String password="Root@123";
	
	
Connection connectt=null;
	
	public Connection getConnection() throws CustomException 
	{
		if(connectt==null)
		{
			try
			{
				Class.forName("org.postgresql.Driver");
				connectt=DriverManager.getConnection(url,username,password);
				
				return connectt;
			}
			catch(Exception e)
			{
				System.out.println("Connect : "+e.getMessage());
				throw new CustomException("CONNECTION"); // No I18N
			}
		}
		else
		{
			return connectt;
		}
	}
	
	public void closeConnection()throws Exception
	{
		if(connectt!=null)
		{
			try
			{
				connectt.close();
			}
			catch(Exception e)
			{
				throw new Exception("CLOSE_CONNECTION"); // No I18N
			}
		}
	}

}
