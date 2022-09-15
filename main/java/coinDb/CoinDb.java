package coinDb;

import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.List;
import java.util.Map;


import operation.ChooseDb;
import operation.CreateInstance;
import operation.CustomException;
import operation.MysqlOperation;
import operation.PsqlOperation;


public class CoinDb 
{
	
	double zCoin=50;

	UserDb userObj = new UserDb();
	AccountDb accountObj = new AccountDb();
	MailDb mailObj = new MailDb();
	TransactionDb transactionObj = new TransactionDb();
	ChooseDb store = null;
	
	public void createDatabase()throws CustomException
	{
		mailObj.createDatabase(store);
	}
	
	
	 public void createDatabasePsql()throws CustomException 
	 {
	  mailObj.createDatabasePsql(store); 
	 }
	 
	
	public void setMysql()throws CustomException
	{
		store = CreateInstance.COINOPERATION.getMysqlInstance();
		
	}
	
	public void setPsql()throws CustomException
	{
		store = CreateInstance.COINOPERATION.getPsqlInstance();
	}
	
	public void createDomainInteger()throws CustomException
	{
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		mailObj.createDomainInteger(psql);
		
	}
	
	public void createDomainMail()throws CustomException
	{
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
	     mailObj.createDomainMail(psql);
	}
	
	public void createSequence()throws CustomException
	{
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		mailObj.createSequenceId(psql);
		mailObj.createSequenceAccount(psql);
	}
	
	public void createTable()throws CustomException
	{
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		userObj.createTable(mysql);
		accountObj.createTable(mysql);
		mailObj.createTable(mysql);
		transactionObj.createTable(mysql);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		userObj.createTable(psql);
		mailObj.createTable(psql);
		accountObj.createTable(psql);
		transactionObj.createTable(psql);
	}
	
	public String getRole(int id)throws CustomException
	{
		return userObj.getRole(store,id);
	}
	
	public String getPassword(int id)throws CustomException
	{
		return userObj.getPassword(store,id);
	}
		
	public int addUser(user.ZCoin.User.Builder user)throws CustomException
	{
		int user_id=0;
		
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		 user_id = userObj.addUser(mysql,user);
		 
	    PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
	    
	    user_id = userObj.addUser(psql, user);
	    
	    return user_id;
	}
	
	public int getId(String mail)throws CustomException
	{
		
		return mailObj.getId(store,mail);
	}
	
	public void addMail(String mail,int id)throws CustomException
	{
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		mailObj.addMail(mysql,mail,id);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		mailObj.addMail(psql, mail, id);
		
	}
	public List<user.ZCoin.User.Builder> showWaitingList()throws CustomException
	{
		return userObj.showWaitingList(store,mailObj);
	}
	public user.ZCoin.User.Builder approveAsUser(user.ZCoin.User.Builder user)throws CustomException
	{
		user.ZCoin.User.Builder user_obj = null;
		
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		user_obj=userObj.approveAsUser(mysql,user);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		user_obj=userObj.approveAsUser(psql, user);
		
		return user_obj;
	}
	public user.ZCoin.User.Builder approveAsAdmin(user.ZCoin.User.Builder user)throws CustomException
	{
		user.ZCoin.User.Builder user_obj = null;
		
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		user_obj = userObj.approveAsAdmin(mysql,user);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		user_obj = userObj.approveAsAdmin(psql, user);
		
		return user_obj;
	}
	public int addAccount(user.ZCoin.User.Builder user)throws CustomException
	{
		int acc_num=0;
		
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		acc_num = accountObj.addAccount(mysql,user);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		acc_num = accountObj.addAccount(psql, user);
		
		return acc_num;
	}
	public account.ZCoin.Account.Builder accountDetails(int id)throws CustomException
	{
		return accountObj.accountDetails(store,id);
	}
	public user.ZCoin.User.Builder getUser(int id)throws CustomException
	{
      
		return userObj.getUser(store,id,mailObj);
		
	}
	public void changePassword(String pass,int id)throws CustomException
	{
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		userObj.changePassword(mysql,pass, id);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		userObj.changePassword(psql, pass, id);
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
		
		changeZcAmount(times);
		
		return zCoin;
	}
	
	public double getDifference(double old_value,double new_value)
	{
		double change = new_value/old_value;
		
		return change;
	}
	
	public void changeZcAmount(double times)throws CustomException
	{
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		accountObj.changeZcAmount( mysql,times);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		accountObj.changeZcAmount(psql, times);
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
	
	public int getAccountNumById(int id)throws CustomException
	{
		return accountObj.getAccountNumById(store,id);
	}
	
	public double getRcBalance(int acc_num)throws CustomException
	{
		return accountObj.getRcBalance(store,acc_num);
	}
	
	public boolean withdrawRc(int acc_num, double amount)throws CustomException
	{
		boolean withdraw=false;
		
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		withdraw = accountObj.withdrawRc(mysql,acc_num, amount);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		withdraw = accountObj.withdrawRc(psql, acc_num, amount);
		
		return withdraw;
	}
	
	public boolean depositRc(int acc_num, double amount)throws CustomException
	{
         boolean deposit=false;
		
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		deposit =  accountObj.depositRc(mysql,acc_num, amount);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		deposit = accountObj.depositRc(psql, acc_num, amount);
		
		return deposit;
	}
	
	public boolean buyZCoin(int acc_num, double amount)throws CustomException
	{
		 boolean buy=false;
			
	    MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
	    
		buy = accountObj.buyZCoin(mysql,acc_num, amount);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		buy = accountObj.buyZCoin(psql, acc_num, amount);
		
		return buy;
	}
	
	public boolean transferZCoin(int from_account, int to_account, double amount)throws CustomException
	{
		boolean transfer=false;
		
	    MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
	    
		 transfer = accountObj.transferZCoin(mysql,from_account, to_account, amount);
		 
		 PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		 
		 transfer = accountObj.transferZCoin(psql, from_account, to_account, amount);
		 
		 return transfer;
	}
	
	public void addTransaction(transaction.ZCoin.Transaction.Builder transfer)throws CustomException
	{
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		 
		transactionObj.addTransaction(mysql,transfer);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		transactionObj.addTransaction(psql, transfer);
	}
	
	public Map<Integer,List<transaction.ZCoin.Transaction.Builder>> getAllHistory()throws CustomException
	{
		return transactionObj.getAllHistory(store);
	}
	
	public List<transaction.ZCoin.Transaction.Builder> getHistoryByUserId(int user_id)throws CustomException
	{
		return transactionObj.getHistoryByUserId(store,user_id);
	}
	
	public boolean checkMailExists(String mail)throws CustomException
	{
		return mailObj.checkMailExists(store,mail);
	}
	public boolean updateName(String name,int id)throws CustomException
	{
		boolean update= false;
		
		MysqlOperation mysql = CreateInstance.COINOPERATION.getMysqlInstance();
		
		update = userObj.updateName(mysql,name, id);
		
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		update = userObj.updateName(psql, name, id);
		
		return update;
	}
	public boolean checkDomainInteger()throws CustomException
	{
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		return mailObj.checkDomainInteger(psql);
	}
	public boolean checkDomainMail()throws CustomException
	{
		PsqlOperation psql = CreateInstance.COINOPERATION.getPsqlInstance();
		
		return mailObj.checkDomainMail(psql);
	}
	
	
}
