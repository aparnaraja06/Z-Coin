package coinDb;

import java.sql.PreparedStatement;



import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cache.CoinCache;
import operation.ChooseDb;
import operation.CreateInstance;
import operation.CustomException;
import redis.clients.jedis.Jedis;


public class TransactionDb 
{
	

	public void createTable(ChooseDb store)throws CustomException
	{
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.createTransactionTable())) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to create Transaction Table");
		}
	}
	
	public void addTransaction(ChooseDb store,transaction.ZCoin.Transaction.Builder transfer)throws CustomException
	{
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.addTransaction())) {
		
			int user_id = transfer.getUserId();
			int from_account = transfer.getFromAccount();
			int to_account = transfer.getToAccount();
			double amount = transfer.getAmount();
			String date = transfer.getDate();
			String type = transfer.getType();
			
			statement.setInt(1, user_id);
			statement.setInt(2, from_account);
			statement.setInt(3, to_account);
			statement.setString(4, type);
			statement.setDouble(5, amount);
			statement.setString(6, date);
			
			statement.executeUpdate();
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			//System.out.println("trans : "+e.getMessage());
			throw new CustomException("Unable to add transaction details");
		}

	}
	
	public Map<Integer,List<transaction.ZCoin.Transaction.Builder>> getAllHistory(ChooseDb store)throws CustomException
	{
		
		CoinCache cache = CreateInstance.COINOPERATION.getCoinCache();
		
		//Jedis transactionMap = cache.setJedis();
		
		Map<Integer,List<transaction.ZCoin.Transaction.Builder>> transactionMap = new HashMap<>();
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.getAllHistory())) 
		{
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					int user_id = result.getInt("user_id");
					
					//String value = transactionMap
							//.get(Integer.toString(user_id));
					
					Jedis list = null;
					
					//if(value==null)
					{
						list = cache.setJedis();
					}

					transaction.ZCoin.Transaction.Builder transfer = transaction.ZCoin.Transaction.newBuilder();
					
					transfer.setUserId(user_id);
				    transfer.setFromAccount(result.getInt("from_account"));
					transfer.setToAccount(result.getInt("to_account"));
					transfer.setAmount(result.getDouble("amount"));
					transfer.setType(result.getString("type"));
					transfer.setDate(result.getString("date"));
					
					list.lpush(Integer.toString(user_id), transfer.toString());
					
					//transactionMap.put(Integer.toString(user_id),);
					
				}
				
				return transactionMap;
		}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to get history");
		}
	}
	
	public List<transaction.ZCoin.Transaction.Builder> getHistoryByUserId(ChooseDb store,int user_id)throws CustomException
	{
		
		List<transaction.ZCoin.Transaction.Builder> list = new ArrayList<>();
		
		try (PreparedStatement statement = store.getConnection()
				.prepareStatement(store.getHistoryByUserId())) 
		{
			statement.setInt(1, user_id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					transaction.ZCoin.Transaction.Builder transfer = transaction.ZCoin.Transaction.newBuilder();
					
					transfer.setToAccount(result.getInt("to_account"));
					transfer.setAmount(result.getDouble("amount"));
					transfer.setType(result.getString("type"));
					transfer.setDate(result.getString("date"));
					
					list.add(transfer);
				}
				
				return list;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to get history");
		}
	}
}
