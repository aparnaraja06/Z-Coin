package operation;

import java.sql.Connection;

public interface ChooseDb 
{
	public String createDatabase();
	
	public Connection getConnection()throws CustomException;

	public String  createUserTable();
	
	 public String getRole();
	 
	 public String getPassword();
	 
	 public String addUser();
	 
	 public String showWaitingList();
	 
	 public String approveAsUser();
	 
	 public String approveAsAdmin();
	 
	 public String getUser();
	 
	 public String changePassword();
	 
	 public String updateName();
	 
	 public String createMailTable();
	 
	 public String getId();
	 
	 public String addMail();
	 
	 public String getMailById();
	 
	 public String checkMailExists();
	 
	 public String createAccountTable();
	 
	 public String addAccount();
	 
	 public String accountDetails();
	 
	 public String getAccountNumById();
	 
	 public String getRcBalance();
	 
	 public String updateRcAmount();
	 
	 public String getZcBalance();
	 
	 public String updateZcAmount();
	 
	 public String changeZcAmount();
	 
	 public String createTransactionTable();
	 
	 public String addTransaction();
	 
	 public String getAllHistory();
	 
	 public String getHistoryByUserId();
	 
	 public String createDomainInteger();
	 
	 public String createDomainMail();
	 
	 public String createSequenceId();
	 
	 public String createSequenceAccount();
	 
	 public String checkDomainInteger();
	 
	 public String checkDomainMail();

}
