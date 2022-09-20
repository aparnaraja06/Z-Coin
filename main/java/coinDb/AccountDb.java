package coinDb;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import cache.CoinCache;
import operation.ChooseDb;
import operation.CreateInstance;
import operation.CustomException;
import redis.clients.jedis.Jedis;

public class AccountDb {
	
	public void createTable(ChooseDb store) throws CustomException
	{
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.createAccountTable())) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			System.out.println("acc : "+e.getMessage());
			throw new CustomException("Unable to create Account Table");
		}
	}
	
	public int addAccount(ChooseDb store,user.ZCoin.User.Builder user)throws CustomException
	{
		
		int acc_num=0;
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.addAccount(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			int user_id = user.getUserId();
			
			double amount = user.getRcAmount();
			
			statement.setInt(1, user_id);
			statement.setDouble(2, amount);
			
             statement.executeUpdate();
			
			try (ResultSet result = statement.getGeneratedKeys()) 
			{
				if (result.next()) 
				{
					acc_num=result.getInt(1);
					
				}
				
				return acc_num;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			System.out.println(e.getMessage());
			throw new CustomException("Unable to add Account");
		}
			
	}
	
	public account.ZCoin.Account.Builder accountDetails(ChooseDb store,int id)throws CustomException
	{
		
		//Account account = new Account();
		
		account.ZCoin.Account.Builder accountObj  = account.ZCoin.Account.newBuilder();
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.accountDetails(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					
							accountObj.setAccountNum(result.getInt("account_num"))
					        .setRcAmount(result.getDouble("rc_amount"))
					        .setZcAmount(result.getDouble("zc_amount"))
					        .build();
				}
				
				return accountObj;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to Fetch Account details");
		}
		
	}
	
	public int getAccountNumById(ChooseDb store,int id)throws CustomException
	{
		
		int account_num=0;
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.getAccountNumById(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					account_num=result.getInt("account_num");
				}
				
				return account_num;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to get account number");
		}
	}
	
	public double getRcBalance(ChooseDb store,int acc_num)throws CustomException
	{
		
		double amount=0.0;
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.getRcBalance(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, acc_num);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					amount =  result.getDouble("rc_amount");
				}
				
				return amount;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to get balance");
		}


					
	}
	
	public boolean withdrawRc(ChooseDb store,int acc_num, double amount)throws CustomException
	{
		double balance = getRcBalance(store,acc_num);
		
		if(balance < amount)
		{
			throw new CustomException("WITHDRAW");
		}
		
		double total = balance-amount;
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.updateRcAmount(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setDouble(1, total);
			statement.setInt(2, acc_num);
			
			statement.executeUpdate();
			
			return true;
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to withdraw");
		}
	
	}
	
	public boolean depositRc(ChooseDb store,int acc_num, double amount)throws CustomException
	{
		double balance = getRcBalance(store,acc_num);
		
		double total = balance+amount;
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.updateRcAmount(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setDouble(1, total);
			statement.setInt(2, acc_num);
			
			statement.executeUpdate();
			
			return true;
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to Deposit");
		}
	
	}
	
	public double getZcBalance(ChooseDb store,int acc_num)throws CustomException
	{
		
		double amount = 0.0;
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.getZcBalance(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
            statement.setInt(1, acc_num);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					amount =  result.getDouble("zc_amount");
				}
				
				return amount;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			System.out.println("err : "+e.getMessage());
			throw new CustomException("Unable to get balance");
		}

	}
	
	public boolean withdrawZc(ChooseDb store,int acc_num, double amount)throws CustomException
	{
		double balance = getZcBalance(store,acc_num);
		
		if(balance < amount)
		{
			throw new CustomException("WITHDRAW");
		}
		
		double total = balance-amount;
		
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.updateZcAmount(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setDouble(1, total);
			statement.setInt(2, acc_num);
			
			statement.executeUpdate();
			
			return true;
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to withdraw");
		}
	
	}
	
	public boolean depositZc(ChooseDb store,int acc_num, double amount)throws CustomException
	{
		double balance = getZcBalance(store,acc_num);
		
		double total = balance+amount;
		
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.updateZcAmount(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setDouble(1, total);
			statement.setInt(2, acc_num);
			
			statement.executeUpdate();
			
			return true;
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to Deposit");
		}
	
	}
	
	public boolean buyZCoin(ChooseDb store,int acc_num, double amount)throws CustomException
	{
		try
		{
		boolean withdraw = withdrawRc(store,acc_num,amount);
		
		boolean deposit = depositZc(store,acc_num,amount);
		
		if(withdraw && deposit)
		{
			return true;
		}
		
		return false;
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to Buy Z coin");
		}
		
	}
	
	public boolean transferZCoin(ChooseDb store,int from_account, int to_account, double amount)throws CustomException
	{
		try
		{
			boolean withdraw = withdrawZc(store,from_account, amount);
			
			boolean deposit = depositZc(store,to_account, amount);
			
			if(withdraw && deposit)
			{
				return true;
			}
			
			return false;
			}
			catch(CustomException e)
			{
				throw new CustomException(e.getMessage());
			}
			catch (Exception e) {
				throw new CustomException("Unable to Transfer");
			}
	}
	
	public void changeZcAmount(ChooseDb store,double times)throws CustomException
	{
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.changeZcAmount(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setDouble(1, times);
			
			statement.executeUpdate();
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to Update amount");
		}
	}
	
	public Jedis allAccounts(ChooseDb store)throws CustomException
	{
		
		CoinCache cache = CreateInstance.COINOPERATION.getCoinCache();
		
		Jedis accountDetails = cache.setJedis();
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.allAccounts())) 
		{
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					account.ZCoin.Account.Builder accountObj = account.ZCoin.Account.newBuilder();
					
					int id = result.getInt("user_id");
					
					accountObj.setAccountNum(result.getInt("account_num"));
					accountObj.setRcAmount(result.getDouble("rc_amount"));
					accountObj.setZcAmount(result.getDouble("zc_amount"));
					
					accountDetails.set(Integer.toString(id), accountObj.toString());
				}
				
				return accountDetails;
					
				}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to fetch accounts");
		}
	}
	

		


}
