package coinDb;

import java.sql.PreparedStatement;



import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import operation.ChooseDb;
import operation.CustomException;


public class UserDb 
{
	

	public void createTable(ChooseDb store)throws CustomException
	{
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.createUserTable())) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e);
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to create User Table");
		}
	}
	
	
	public String getRole(ChooseDb store,int id)throws CustomException
	{
		
		String role="";
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.getRole(),
						PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					role=result.getString("role");
				}
				
				return role;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e);
		}
		catch (Exception e) {
			throw new CustomException("Unable to get Role");
		}
	}
	
	public String getPassword(ChooseDb store,int id)throws CustomException
	{
		
		String password="";
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.getPassword(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					password=result.getString("password");
				}
				
				return password;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e);
		}
		catch (Exception e) {
			throw new CustomException("Unable to get password");
		}
	}
	
	public int addUser(ChooseDb store,user.ZCoin.User.Builder user)throws CustomException
	{
			
		int id=0;
		 
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.addUser(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			
			String name = user.getName();
			long mobile = user.getMobile();
			String human_id = user.getHumanId();
			String password =  user.getPassword();
			double amount = user.getRcAmount();
			
			statement.setString(1, name);
			statement.setLong(2, mobile);
			statement.setString(3, human_id);
			statement.setString(4, password);
			statement.setDouble(5, amount);
			
			statement.executeUpdate();
			
			try (ResultSet result = statement.getGeneratedKeys()) 
			{
				if (result.next()) 
				{
					id=result.getInt(1);
			
				}
				
				return id;
			}
			
		}
		catch(CustomException e)
		{
			throw new CustomException(e);
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to add user");
		}
		
	}
	
	public List<user.ZCoin.User.Builder> showWaitingList(ChooseDb store,MailDb mailObj)throws CustomException
	{
		
		List<user.ZCoin.User.Builder> list = new ArrayList<>();
		
		
		try(PreparedStatement statement =store.getConnection()
				.prepareStatement(store.showWaitingList()))
		{
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					
					user.ZCoin.User.Builder userObj = user.ZCoin.User.newBuilder();
					
					int id=result.getInt("user_id");
					userObj.setUserId(id);
				
					String mail=mailObj.getMailById(store,id);
					
					
					userObj.setMail(mail);
					userObj.setName(result.getString("name"));
					userObj.setHumanId(result.getString("human_id"));
					userObj.setMobile(result.getLong("mobile"));
					userObj.setRcAmount(result.getDouble("rc_amount"));
					userObj.setApproved(result.getBoolean("approved"));
					userObj.setRole(result.getString("role"));
					
					
					list.add(userObj);
				}
				
				return list;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to fetch data");
		}
	}
	
	public user.ZCoin.User.Builder approveAsUser(ChooseDb store,user.ZCoin.User.Builder user)throws CustomException
	{
		int id = user.getUserId();
		
		
		try(PreparedStatement statement =store.getConnection()
				.prepareStatement(store.approveAsUser()))
		{
			statement.setInt(1, id);
		
			statement.executeUpdate();
			
			return user;
			
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to update data");
		}
	}
	
	public user.ZCoin.User.Builder approveAsAdmin(ChooseDb store,user.ZCoin.User.Builder user)throws CustomException
	{
       int id = user.getUserId();
       
       String role="admin";
		
		
		try(PreparedStatement statement =store.getConnection()
				.prepareStatement(store.approveAsAdmin()))
		{
			statement.setString(1, role);
			statement.setInt(2, id);
		
			statement.executeUpdate();
			
			return user;
			
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to update data");
		}
	}
	
	public user.ZCoin.User.Builder getUser(ChooseDb store,int id,MailDb mailObj)throws CustomException
	{
		
		user.ZCoin.User.Builder userObj = user.ZCoin.User.newBuilder();
		
		try(PreparedStatement statement =store.getConnection()
				.prepareStatement(store.getUser()))
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					userObj.setName(result.getString("name"));
					userObj.setHumanId(result.getString("human_id"));
					userObj.setMobile(result.getLong("mobile"));
					userObj.setRcAmount(result.getDouble("rc_amount"));
					String mail=mailObj.getMailById(store,id);
					userObj.setMail(mail);
				}
				
				return userObj;
			}
			
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to get user details");
		}
		
	}
	
	public void changePassword(ChooseDb store,String pass,int id)throws CustomException
	{
		
		try(PreparedStatement statement =store.getConnection()
				.prepareStatement(store.changePassword()))
		{
			statement.setString(1, pass);
			statement.setInt(2, id);
			
			statement.executeUpdate();
			
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to update password"); // No I18N
		}
		
	}
	
	public boolean updateName(ChooseDb store,String name,int id)throws CustomException
	{
		
		try(PreparedStatement statement =store.getConnection()
				.prepareStatement(store.updateName()))
		{
			statement.setString(1, name);
			statement.setInt(2, id);
			
			statement.executeUpdate();
			
			return true;
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to update name"); // No I18N
		}
		
	}

}
