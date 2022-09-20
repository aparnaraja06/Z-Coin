package operation;



import java.util.List;



import java.util.Map;

import coinDb.CoinDb;
import redis.clients.jedis.Jedis;



public class CoinOperation 
{

	CoinDb coin = new CoinDb();
	
	
	public void createTable()throws CustomException
	{
		coin.createTable();
	}
	
	public void createDatabase()throws CustomException
	{
		coin.createDatabase();
	}
	
	
	  public void createDatabasePsql()throws CustomException 
	  {
	    coin.createDatabasePsql(); 
	   }
	 
	
	public void setMysql()throws CustomException
	{
		coin.setMysql();
	}
	
	public void setPsql()throws CustomException
	{
		coin.setPsql();
	}
	
	public Jedis setUserMap()throws CustomException
	{
		Jedis userMap = coin.getAllUsers();
		
		return userMap;
		
	}
	
	public Jedis setAccountMap()throws CustomException
	{
		Jedis accountMap = coin.allAccounts();
		
		return accountMap;
	}
	
	public Jedis setMailMap()throws CustomException
	{
		Jedis mailMap = coin.getAllMail();
		
		return mailMap;
	}
	
	public Jedis getWaitingList()throws CustomException
	{
		Jedis waitingList = coin.showWaitingList();
		
		return waitingList;
	}
	
	public void createDomainInteger()throws CustomException
	{
		coin.createDomainInteger();
	}
	
	public void createDomainMail()throws CustomException
	{
		coin.createDomainMail();
	}
	
	public void createSequence()throws CustomException
	{
		coin.createSequence();
	}
	
	public String getRole(int id)throws CustomException
	{
		Jedis userMap = setUserMap();
			
		return CreateInstance.COINOPERATION.getCoinCache().getRole(userMap, id);
	}
	
	public String getPassword(int id)throws CustomException
	{
		Jedis userMap = setUserMap();
		
		return CreateInstance.COINOPERATION.getCoinCache().getPassword(userMap, id);
	}
		
	public int addUser(user.ZCoin.User.Builder user)throws CustomException
	{
		
		int id = coin.addUser(user);
		
		Jedis userMap = setUserMap();
		
		CreateInstance.COINOPERATION.getCoinCache().addUser(userMap, id, user);
		
		return id;
	}
	
	public int getId(String mail)throws CustomException
	{
		 Jedis mailMap = setMailMap();
		 
		 return CreateInstance.COINOPERATION.getCoinCache().getId(mailMap, mail);
	}
	
	public void addMail(String mail,int id)throws CustomException
	{
		Jedis mailMap = setMailMap();
		
		coin.addMail(mail,id);
		
		CreateInstance.COINOPERATION.getCoinCache().addMail(mailMap, mail, id);
		
	}
	public Jedis showWaitingList()throws CustomException
	{
		Jedis waitingList = getWaitingList();
		
		return CreateInstance.COINOPERATION.getCoinCache().showWaitingList(waitingList);
	}
	public user.ZCoin.User.Builder approveAsUser(user.ZCoin.User.Builder user)throws CustomException
	{
		Jedis userMap = setUserMap();
		
		user.ZCoin.User.Builder userObj = coin.approveAsUser(user);
		
		return CreateInstance.COINOPERATION.getCoinCache().approveAsUser(userMap, userObj);
	}
	public user.ZCoin.User.Builder approveAsAdmin(user.ZCoin.User.Builder user)throws CustomException
	{
         Jedis userMap = setUserMap();
		
		user.ZCoin.User.Builder userObj = coin.approveAsUser(user);
		
		coin.approveAsAdmin(user);
		
		return CreateInstance.COINOPERATION.getCoinCache().approveAsAdmin(userMap, userObj);
	}
	public int addAccount(user.ZCoin.User.Builder user)throws CustomException
	{
		Jedis accountMap = setAccountMap();
		
		int acc_num = coin.addAccount(user);
		
		CreateInstance.COINOPERATION.getCoinCache().addAccount(accountMap, acc_num, user);
		
		return acc_num;
	}
	
	public account.ZCoin.Account.Builder accountDetails(int id)throws CustomException
	{
		 coin.accountDetails(id);
		 
		 Jedis accountMap = setAccountMap();
			
		return CreateInstance.COINOPERATION.getCoinCache().accountDetails(accountMap, id);
	}
	
	public user.ZCoin.User.Builder getUser(int id)throws CustomException
	{
		coin.getUser(id);
		
        Jedis userMap = setUserMap();
		
		return CreateInstance.COINOPERATION.getCoinCache().getUser(userMap, id);
	}
	public void changePassword(String pass,int id)throws CustomException
	{
		coin.changePassword(pass, id);
		
		Jedis userMap = setUserMap();
		
		CreateInstance.COINOPERATION.getCoinCache().changePassword(userMap, pass, id);
	}
	public double zCoinRate()
	{
		
		return CreateInstance.COINOPERATION.getCoinCache().zCoinRate();
		
	}
	public double changeZCoinRate(double amount)throws CustomException
	{
	
		return CreateInstance.COINOPERATION.getCoinCache().changeZCoinRate(amount);
		
	}
	public void changeZcAmount(double times)throws CustomException
	{
		//pending
		//coin.changeZcAmount(times);
	}
	public String getDate()
	{
		return CreateInstance.COINOPERATION.getCoinCache().getDate();
		
	}
	public int getAccountNumById(int id)throws CustomException
	{
	        coin.getAccountNumById(id);
	        
	        Jedis accountMap = setAccountMap();
	        
	     return CreateInstance.COINOPERATION.getCoinCache().getAccountNumById(accountMap, id);
	     
	}
	
	public boolean withdrawRc(int id,int acc_num, double amount)throws CustomException
	{
		
	     coin.withdrawRc(acc_num, amount);
	     
	     Jedis accountMap = setAccountMap();
	     
	     return CreateInstance.COINOPERATION.getCoinCache().withdrawRc(accountMap, id, acc_num, amount);
	}
	public boolean depositRc(int id,int acc_num, double amount)throws CustomException
	{

		coin.depositRc(acc_num, amount);
		
		Jedis accountMap = setAccountMap();
		
		return CreateInstance.COINOPERATION.getCoinCache().depositRc(accountMap, id, acc_num, amount);
	}
	
	public boolean buyZCoin(int id,int acc_num, double amount)throws CustomException
	{
		
	     coin.buyZCoin(acc_num, amount);
	     
	     Jedis accountMap = setAccountMap();
	     
	     return CreateInstance.COINOPERATION.getCoinCache().buyZCoin(accountMap, id, acc_num, amount);
	}
	public boolean transferZCoin(int from_account, int to_account, double amount)throws CustomException
	{
		//pending
		return coin.transferZCoin(from_account, to_account, amount);
	}
	public void addTransaction(transaction.ZCoin.Transaction.Builder transfer)throws CustomException
	{
		coin.addTransaction(transfer);
	}
	public Map<Integer,List<transaction.ZCoin.Transaction.Builder>> getAllHistory()throws CustomException
	{
		return coin.getAllHistory();
	}
	public List<transaction.ZCoin.Transaction.Builder> getHistoryByUserId(int user_id)throws CustomException
	{
		return coin.getHistoryByUserId(user_id);
	}
	public boolean checkMailExists(String mail)throws CustomException
	{
		coin.checkMailExists(mail);
		
		Jedis mailMap = setMailMap();
		
		return CreateInstance.COINOPERATION.getCoinCache().checkMailExists(mailMap, mail);
	}
	public boolean updateName(String name,int id)throws CustomException
	{
		coin.updateName(name, id);
		
		Jedis userMap = setUserMap();
		
		return CreateInstance.COINOPERATION.getCoinCache().updateName(userMap, name, id);
	}
	public boolean checkDomainInteger()throws CustomException
	{
		return coin.checkDomainInteger();
	}
	public boolean checkDomainMail()throws CustomException
	{
		return coin.checkDomainMail();
	}
	
	/*
	 * public void checkJedis()throws CustomException { CoinCache cache =
	 * CreateInstance.COINOPERATION.getCoinCache();
	 * 
	 * //Jedis list = cache.setJedis();
	 * 
	 * List<String> list = new ArrayList<>();
	 * 
	 * list.add("Aparna"); list.add("Raja");
	 * 
	 * 
	 * //String value = list.lpop("1");
	 * 
	 * System.out.println(list.toString()); }
	 */
	
}
