package cache;


import java.text.SimpleDateFormat;
import java.util.Date;

import operation.ChooseDb;
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
	
	public Jedis showWaitingList(Jedis waitingList)throws CustomException
	{
		return waitingList;
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
	
	public double changeZCoinRate(double amount)throws CustomException
	{
		double old = zCoin;
		
		zCoin=amount;
		
		double times = getDifference(old,zCoin);
		
	//	changeZcAmount(times);
		
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
		
		SimpleDateFormat format=new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		
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
		
		String value = transferMap.get(Integer.toString(id));
		
		//list.add(transfer);
		
		//transferMap.put(id, list);
	}
	
	/*public RMap<Integer,RList<transaction.ZCoin.Transaction.Builder>> getAllHistory()throws CustomException
	{
		return transferMap;
	}
	
	public RList<transaction.ZCoin.Transaction.Builder> getHistoryByUserId(int id)throws CustomException
	{
		RList<transaction.ZCoin.Transaction.Builder> list = transferMap.get(id);
		
		return list;
	}*/
	
	public boolean checkMailExists(Jedis mailMap,String mail)throws CustomException
	{
		String result = mailMap.get(mail);
		
		if(result==null || result.isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
	public boolean updateName(Jedis userMap,String name,int id)throws CustomException
	{
		user.ZCoin.User.Builder user = getUser(userMap,id);
		
		user.setName(name);
		
		userMap.set(Integer.toString(id), user.toString());
		
		return true;
	}
	
	

}
