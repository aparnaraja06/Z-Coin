package cache;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import operation.CustomException;
import redis.clients.jedis.Jedis;

public class CoinCache {
	
	double zCoin=50;
	
	
	public Jedis setJedis()
	{
		Jedis jedis = new Jedis();
		
		return jedis;
	}
	
	public String getRole(Jedis userMap,int id)throws CustomException
	{
		
		String user = userMap.get(Integer.toString(id));
		
		String result = "";
		
	
			String[] temp = user.split("\n");
			
			for(String each : temp)
			{

				if(each.contains("role:"))
				{
					
					String[] arr = each.split(" ");
					
					int length = arr.length-1;
					
					String role = arr[length];
					
					result = role.substring(1, role.length()-1);
					
				}
				
		}
		
		return result;
		
	}
	
    public String getPassword(Jedis userMap,int id)throws CustomException
	{
		String user = userMap.get(Integer.toString(id));
		
		String result = "";
		
		
		String[] temp = user.split("\n");
		
		for(String each : temp)
		{

			if(each.contains("password:"))
			{
				
				String[] arr = each.split(" ");
				
				int length = arr.length-1;
				
				String role = arr[length];
				
				result = role.substring(1, role.length()-1);
				
			}
		}
		
		return result;
	}
	
	public int addUser(Jedis userMap,int id,user.ZCoin.User.Builder userDetails)throws CustomException
	{
		userMap.set(Integer.toString(id), userDetails.toString());
		
		return id;
	}
	
	public int getId(Jedis mailMap,String mail)throws CustomException
	{
		String id = mailMap.get(mail);
		
		int result = Integer.parseInt(id);
		
		return result;
	}
	
	public void addMail(Jedis mailMap,String mail,int id)throws CustomException
	{
		mailMap.set(mail, Integer.toString(id));
	}
	
	public List<user.ZCoin.User.Builder> showWaitingList(Jedis waitingList)throws CustomException
	{

		List<user.ZCoin.User.Builder> list = new ArrayList<>();
		
		for(String key : waitingList.keys("*"))
		{
			int id = Integer.valueOf(key);
			
			user.ZCoin.User.Builder user = getUser(waitingList,id);
			
			list.add(user);
		}
		
		return list;
	}
	
	public user.ZCoin.User.Builder approveAsUser(Jedis userMap,user.ZCoin.User.Builder user)throws CustomException
	{
		int id = user.getUserId();
		
		user.setApproved(true);
		
		userMap.set(Integer.toString(id), user.toString());
		
		return user;
	}
	
	public user.ZCoin.User.Builder approveAsAdmin(Jedis userMap,user.ZCoin.User.Builder user)throws CustomException
	{
        int id = user.getUserId();
		
		user.setApproved(true);
		user.setRole("admin");
		
        userMap.set(Integer.toString(id), user.toString());
		
		return user;
	}
	
	public int addAccount(Jedis accountMap,int acc_num,user.ZCoin.User.Builder user)throws CustomException
	{
		account.ZCoin.Account.Builder accountObj = account.ZCoin.Account.newBuilder();
		
		accountObj.setAccountNum(acc_num);
		accountObj.setRcAmount(user.getRcAmount());
		accountObj.setUserId(user.getUserId());
		
		accountMap.set(Integer.toString(acc_num), accountObj.toString());
		
		return acc_num;
	}
	
	public String removeQuotes(String word)
	{
		String result = word.substring(1, word.length() - 1);
		
		return result;
	}
	
	public account.ZCoin.Account.Builder accountDetails(Jedis accountMap,int id)throws CustomException
	{
		String accountObj = accountMap.get(Integer.toString(id));
		
		account.ZCoin.Account.Builder accountt = account.ZCoin.Account.newBuilder();
		
          
		String[] temp = accountObj.split("\n");
		
		int count=0;
		
		
		for(String each : temp)
		{

			if(each.contains(":"))
			{
				String[] arr = each.split(" ");
				
				for(int i=0;i<arr.length;i++)
				{
					if(i%2!=0)
					{
						count++;
						
						if(count==1)
						{
						accountt.setAccountNum(Integer.valueOf(arr[i]));
						}
						else if(count==2)
						{
							accountt.setRcAmount(Double.valueOf(arr[i]));
						}
						else if(count==3)
						{
							accountt.setZcAmount(Double.valueOf(arr[i]));
						}
					}
				}
			}
		}
		
		
		return accountt;
	}
	
	public user.ZCoin.User.Builder getUser(Jedis userMap,int id)throws CustomException
	{
		String userObj = userMap.get(Integer.toString(id));
		
		user.ZCoin.User.Builder userr = user.ZCoin.User.newBuilder();
		
        
		String[] temp = userObj.split("\n");
		
		int count=0;
		
		
		for(String each : temp)
		{

			if(each.contains(":"))
			{
				
				String[] arr = each.split(" ");
				
				for(int i=0;i<arr.length;i++)
				{
					if(i%2!=0)
					{
						count++;
												
						if(count==1)
						{
							
						String name = removeQuotes(arr[i]);
						
						userr.setName(name);
						
						}
						else if(count==2)
						{
							userr.setMobile(Long.valueOf(arr[i]));
						}
						else if(count==3)
						{
							String humanId = removeQuotes(arr[i]);
							
							userr.setHumanId(humanId);
							
						}
						else if(count==4)
						{
							String password = removeQuotes(arr[i]);
							
							userr.setPassword(password);
						}
						else if(count==5)
						{
							userr.setRcAmount(Double.valueOf(arr[i]));
						}
						else if(count==6)
						{
							String role = removeQuotes(arr[i]);
							
							userr.setRole(role);
						}
						else if(count==7)
						{
							userr.setApproved(Boolean.valueOf(arr[i]));
						}
						else if(count==8)
						{
							String mail = removeQuotes(arr[i]);
							
							userr.setMail(mail);
						}
					}
				}
			}
		}
		
		return userr;
	}
	
	public double zCoinRate()
	{
		return zCoin;
	}
	
	public void changeZcAmount(Jedis accountMap,double times)throws CustomException
	{
		for(String key : accountMap.keys("*"))
		{
			int id = Integer.valueOf(key);
			
			account.ZCoin.Account.Builder accountObj = accountDetails(accountMap,id);
			
			double amount = accountObj.getZcAmount();
			
			double total = amount * times;
			
			accountObj.setZcAmount(total);
			
			accountMap.set(key, accountObj.toString());
		}
	}
	
	public double changeZCoinRate(Jedis accountMap,double amount)throws CustomException
	{
		double old = zCoin;
		
		zCoin=amount;
		
		double times = getDifference(old,zCoin);
		
		changeZcAmount(accountMap,times);
		
		return zCoin;
	}
	
	public double getDifference(double old_value,double new_value)
	{
		double change = new_value/old_value;
		
		return change;
	}
	
	public String getDate()
	{
		String result="";
		
		long millis=System.currentTimeMillis();
		
		SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yyyy-HH:mm:ss");
		
		Date date= new Date(millis);
		
		result=format.format(date);
		
		return result;
	}
	
	public void changePassword(Jedis userMap,String pass,int id)throws CustomException
	{
		user.ZCoin.User.Builder user = getUser(userMap,id);
		
		user.setPassword(pass);
		
		userMap.set(Integer.toString(id), user.toString());
		
	}
	
	public int getAccountNumById(Jedis accountMap,int id)throws CustomException
	{
		account.ZCoin.Account.Builder account = accountDetails(accountMap,id);
		
		int acc_num = account.getAccountNum();
		
		return acc_num;
	}
	
	public double getRcBalance(Jedis accountMap,int id)throws CustomException
	{
		account.ZCoin.Account.Builder account = accountDetails(accountMap,id);
		
		double balance = account.getRcAmount();
		
		return balance;
	}
	
	public boolean withdrawRc(Jedis accountMap,int id,int acc_num,double amount)throws CustomException
	{
		account.ZCoin.Account.Builder account = accountDetails(accountMap,id);
		
		double rc_amount = getRcBalance(accountMap,id);
		
		if(rc_amount < amount)
		{
			throw new CustomException("WITHDRAW");
		}
		
		double total = rc_amount-amount;
		
		account.setRcAmount(total);
		
		accountMap.set(Integer.toString(id), account.toString());
		
		return true;
	}
	
	public boolean depositRc(Jedis accountMap,int id,int acc_num, double amount)throws CustomException
	{
         account.ZCoin.Account.Builder account = accountDetails(accountMap,id);
		
		double rc_amount = getRcBalance(accountMap,id);
		
        double total = rc_amount+amount;
		
		account.setRcAmount(total);
		
		accountMap.set(Integer.toString(id), account.toString());
		
		return true;
	}
	
	public double getZcBalance(Jedis accountMap,int id)throws CustomException
	{
		 account.ZCoin.Account.Builder account = accountDetails(accountMap,id);
		 
		 double balance = account.getZcAmount();
		 
		 return balance;
	}
	
	public boolean withdrawZc(Jedis accountMap,int id, int acc_num, double amount)throws CustomException
	{
		double balance = getZcBalance(accountMap,id);
		
		 account.ZCoin.Account.Builder account = accountDetails(accountMap,id);
		 
		 if(balance < amount)
			{
				throw new CustomException("WITHDRAW");
			}
			
			double total = balance-amount;
			
			account.setZcAmount(total);
			
			accountMap.set(Integer.toString(id), account.toString());
			
			return true;
			
	}
	
	public boolean depositZc(Jedis accountMap,int id,int acc_num, double amount)throws CustomException
	{
		double balance = getZcBalance(accountMap,id);
		
		 account.ZCoin.Account.Builder account = accountDetails(accountMap,id);
		 
		 double total = balance+amount;
		 
		 account.setZcAmount(total);
		 
		 accountMap.set(Integer.toString(id), account.toString());
		 
		 return true;
		
	}
	
	public boolean buyZCoin(Jedis accountMap,int id, int acc_num,double amount)throws CustomException
	{
       boolean withdraw = withdrawRc(accountMap,id,acc_num,amount);
		
		boolean deposit = depositZc(accountMap,id,acc_num,amount);
		
		if(withdraw && deposit)
		{
			return true;
		}
		
		return false;
	}
	
	public void addTransaction(Jedis transferMap,transaction.ZCoin.Transaction.Builder transfer)throws CustomException
	{
		int id = transfer.getUserId();
		
		String user_id = Integer.toString(id);
		
		List<String> value = transferMap.mget(user_id);
		
		if(value==null)
		{
			value = new ArrayList<>();
		}
		
		value.add(transfer.toString());
		
		transferMap.set(user_id, value.toString());
	}
	
	
	public  List<transaction.ZCoin.Transaction.Builder> getHistoryByUserId(Jedis transferMap,int id)throws CustomException
	{
		List<String> list = transferMap.mget(Integer.toString(id));
		
		List<transaction.ZCoin.Transaction.Builder> history = new ArrayList<>();
		
		for(int i=0;i<list.size();i++)
		{
			String ans = list.get(i);
			
			String[] temp = ans.split("\n");
			
			int count=0;
			
			transaction.ZCoin.Transaction.Builder transfer = transaction.ZCoin.Transaction.newBuilder();
			
			for(String each : temp)
			{

				if(each.contains(":"))
				{
		
					String[] arr = each.split(" ");
					
					for(int j=0;j<arr.length;j++)
					{
						if(j%2!=0)
						{
		
							count++;
													
							if(count==1)
							{
								transfer.setUserId(Integer.valueOf(arr[j]));
							}
							else if(count==2)
							{
								transfer.setFromAccount(Integer.valueOf(arr[j]));
							}
							else if(count==3)
							{
								transfer.setToAccount(Integer.valueOf(arr[j]));
							}
							else if(count==4)
							{
								String type = removeQuotes(arr[j]);
								
								transfer.setType(type);
							}
							else if(count==5)
							{
								transfer.setAmount(Double.valueOf(arr[j]));
							}
							else if(count==6)
							{
								String date = removeQuotes(arr[j]);

								transfer.setDate(date);
								
							}
						}
						
						
					}
				}
			}
			
			history.add(transfer);
		}
	
		
		return history;
	}
	
	public boolean checkMailExists(Jedis mailMap,String mail)throws CustomException
	{
		boolean result = mailMap.exists(mail);
		
		return result;
	}
	
	public boolean updateName(Jedis userMap,String name,int id)throws CustomException
	{
		user.ZCoin.User.Builder user = getUser(userMap,id);
		
		user.setName(name);
		
		userMap.set(Integer.toString(id), user.toString());
		
		return true;
	}
	
	public int getIdByAccountNum(Jedis accountMap,int acc_num)throws CustomException
	{
		int user = 0;
		
		for(String key : accountMap.keys("*"))
		{
			int id = Integer.valueOf(key);
			
			account.ZCoin.Account.Builder account = accountDetails(accountMap,id);
			
			int accountNumber = account.getAccountNum();
			
			if(accountNumber == acc_num)
			{
				user = account.getUserId();
			}
		}
		
		return user;
	}
		
	public boolean transferZCoin(Jedis accountMap,int from_id, int to_id,int from_account, int to_account, double amount)throws CustomException
	{
		boolean withdraw = withdrawZc(accountMap,from_id,from_account,amount);
		
		boolean deposit = depositZc(accountMap,to_id,to_account,amount);
		
		if(withdraw && deposit)
		{
			return true;
		}
		
		return false;
	}
	
	public Map<Integer,List<transaction.ZCoin.Transaction.Builder>> getAllHistory(Jedis transferMap) throws CustomException 
	{
		
		Map<Integer,List<transaction.ZCoin.Transaction.Builder>> transactionMap = new HashMap<>();
		
		
		for(String key : transferMap.keys("*"))
		{
			
			List<transaction.ZCoin.Transaction.Builder> transfer = 
					getHistoryByUserId(transferMap,Integer.valueOf(key));
			
			transactionMap.put(Integer.valueOf(key), transfer);
			
		}
		
		return transactionMap;
		
	}
	
	

}
