package coinDb;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import cache.CoinCache;
import operation.Checker;
import operation.ChooseDb;
import operation.CreateInstance;
import operation.CustomException;
import operation.PsqlOperation;
import redis.clients.jedis.Jedis;


public class MailDb {
	
	
	public boolean checkDomainInteger(PsqlOperation psql)throws CustomException
	{
		boolean domain=false;
		
		try (PreparedStatement statement = psql.getConnection().
				prepareStatement(psql.checkDomainInteger()))
		{
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					 domain = result.getBoolean(1);
				}
				
				return domain;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to get Domain for number");
		}
	}
	
	public boolean checkDomainMail(PsqlOperation psql)throws CustomException
	{
		boolean domain=false;
		
		try (PreparedStatement statement = psql.getConnection().
				prepareStatement(psql.checkDomainMail()))
		{
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					 domain = result.getBoolean(1);
				}
				
				return domain;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to get Domain for mail");
		}
	}
	
	public void createDomainInteger(PsqlOperation psql)throws CustomException
	{
		try (PreparedStatement statement = psql.getConnection()
				.prepareStatement(psql.createDomainInteger())) {
			statement.execute();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to create Domain for number");
		}
	}
	
	public void createDomainMail(PsqlOperation psql)throws CustomException
	{
		try (PreparedStatement statement = psql.getConnection()
				.prepareStatement(psql.createDomainMail())) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to create Domain for mail");
		}
	}
	
	public void createSequenceId(PsqlOperation psql)throws CustomException
	{
		try (PreparedStatement statement = psql.getConnection()
				.prepareStatement(psql.createSequenceId())) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to create Sequence for Id");
		}
	}
	
	public void createSequenceAccount(PsqlOperation psql)throws CustomException
	{
		try (PreparedStatement statement = psql.getConnection()
				.prepareStatement(psql.createSequenceAccount())) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to create Sequence for account number");
		}
	}
	
	public void createDatabase(ChooseDb store)throws CustomException
	{
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.createDatabase())) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
		
			throw new CustomException("Unable to create Database");
		}
	}
	
	public void createDatabasePsql(ChooseDb store)throws CustomException
	{
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.createDatabase())) {
			statement.execute();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to create Database");
		}
	}
	
	public void createTable(ChooseDb store)throws CustomException
	{
		
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.createMailTable())) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to create Mail Table");
		}
	}
	
	public int getId(ChooseDb store,String mail)throws CustomException
	{
		Checker check = new Checker();
		
		check.checkString(mail);
		
		
		int id=0;
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.getId(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setString(1, mail);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					id=result.getInt("user_id");
				}
				
				return id;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException("USERNAME");
		}
		catch (Exception e) {
			throw new CustomException("Id not available");
		}
    }
	
	public void addMail(ChooseDb store,String mail,int id)throws CustomException
	{
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.addMail(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			statement.setString(2, mail);
			
			statement.executeUpdate();
			
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("MAIL"); // No I18N
		}
	}
	
	public String getMailById(ChooseDb store,int id)throws CustomException
	{
		
		
		String mail="";
		
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.getMailById(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					mail=result.getString("mail_id");
				}
				
				return mail;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			throw new CustomException("Mail id not available");
		}

		
	}
	
	public boolean checkMailExists(ChooseDb store,String mail)throws CustomException
	{
		
		String mail_id=null;
		boolean check=true;
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.checkMailExists(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setString(1, mail);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					mail_id = result.getString("mail_id");
					
					if(!mail_id.equals(mail))
					{
						throw new CustomException("MAIL");
					}
				}
				return check;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("MAIL");
		}
	}
	
	public Jedis getAllMail(ChooseDb store)throws CustomException
	{
		CoinCache cache = CreateInstance.COINOPERATION.getCoinCache();
		
		Jedis mailDetails = cache.setJedis();
	
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.allMail())) 
		{
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					String mail = result.getString("mail_id");
					int id = result.getInt("user_id");
					
					mailDetails.set(mail, Integer.toString(id));
				}
				
				return mailDetails;
			}
			
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("unable to get all mail");
		}
	}
	

	

}
