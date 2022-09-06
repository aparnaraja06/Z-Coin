package operation;

import java.sql.Connection;

import coinDb.PsqlConnection;
import coinDb.PsqlQuery;

public class PsqlOperation implements ChooseDb{

	@Override
	public String createUserTable() {
		
      String query = PsqlQuery.CREATE_TABLE_USER.getQuery();
		
		return query;
	}

	@Override
	public String getRole() {
		
      String query = PsqlQuery.GET_ROLE_BY_ID.getQuery();
		
		return query;
	}

	@Override
	public String getPassword() {
		
       String query = PsqlQuery.GET_PASSWORD.getQuery();
		
		return query;
	}

	@Override
	public String addUser() {
		
      String query = PsqlQuery.INSERT_USER.getQuery();
		
		return query;
	}

	@Override
	public String showWaitingList() {
	
     String query = PsqlQuery.SELECT_WAITING.getQuery();
		
		return query;
	}

	@Override
	public String approveAsUser() {
		
      String query = PsqlQuery.APPROVE_USER.getQuery();
		
		return query;
	}

	@Override
	public String approveAsAdmin() {
		
      String query = PsqlQuery.APPROVE_ADMIN.getQuery();
		
		return query;

	}

	@Override
	public String getUser() {
		
      String query = PsqlQuery.GET_USER_BY_ID.getQuery();
		
		return query;
	}

	@Override
	public String changePassword() {
		
       String query = PsqlQuery.UPDATE_PASSWORD.getQuery();
		
		return query;
	}

	@Override
	public String updateName() {
		
      String query = PsqlQuery.UPDATE_NAME.getQuery();
		
		return query;
	}

	@Override
	public String createMailTable() {
		
       String query = PsqlQuery.CREATE_TABLE_MAIL.getQuery();
		
		return query;
	}

	@Override
	public String getId() {
		
      String query = PsqlQuery.GET_ID_BY_MAIL.getQuery();
		
		return query;
	}

	@Override
	public String addMail() {
		
       String query = PsqlQuery.INSERT_MAIL.getQuery();
		
		return query;
	}

	@Override
	public String getMailById() {
		
      String query = PsqlQuery.GET_MAIL_BY_ID.getQuery();
		
		return query;

	}

	@Override
	public String checkMailExists() {
		
      String query = PsqlQuery.GET_MAIL.getQuery();
		
		return query;
	}

	@Override
	public String createAccountTable() {
		
      String query = PsqlQuery.CREATE_TABLE_ACCOUNT.getQuery();
		
		return query;
	}

	@Override
	public String addAccount() {
		
       String query = PsqlQuery.INSERT_ACCOUNT.getQuery();
		
		return query;
	}

	@Override
	public String accountDetails() {
		
       String query = PsqlQuery.GET_ACC_BY_ID.getQuery();
		
		return query;
	}

	@Override
	public String getAccountNumById() {
		
       String query = PsqlQuery.SELECT_ACCOUNT.getQuery();
		
		return query;
	}

	@Override
	public String getRcBalance() {
		
       String query = PsqlQuery.GET_RC_AMOUNT.getQuery();
		
		return query;
	}

	@Override
	public String updateRcAmount() {
		
      String query = PsqlQuery.UPDATE_RC_AMOUNT.getQuery();
		
		return query;
	}

	@Override
	public String getZcBalance() {
		
       String query = PsqlQuery.GET_ZC_AMOUNT.getQuery();
		
		return query;
	}

	@Override
	public String updateZcAmount() {
		

       String query = PsqlQuery.UPDATE_ZC_AMOUNT.getQuery();
		
		return query;
		
	}

	@Override
	public String changeZcAmount() {
		
       String query = PsqlQuery.CHANGE_ZC_AMOUNT.getQuery();
		
		return query;
	}

	@Override
	public String createTransactionTable() {
		
       String query = PsqlQuery.CREATE_TABLE_TRANSACTION.getQuery();
		
		return query;
	}

	@Override
	public String addTransaction() {
		
       String query = PsqlQuery.INSERT_TRANSACTION.getQuery();
		
		return query;
	}

	@Override
	public String getAllHistory() {
		
       String query = PsqlQuery.SELECT_TRANSACTION.getQuery();
		
		return query;
	}

	@Override
	public String getHistoryByUserId() {
		
     String query = PsqlQuery.HISTORY_BY_ID.getQuery();
		
		return query;

	}

	@Override
	public String createDatabase() {
		
		String query = PsqlQuery.CREATE_DATABASE.getQuery();
		
		return query;
	}

	@Override
	public Connection getConnection() throws CustomException {
		
		Connection connect = PsqlConnection.CONNECTION.getConnection();
		
		return connect;
	}
	
	public String createDomainInteger()
	{
		String query = PsqlQuery.DOMAIN_INTEGER.getQuery();
		
		return query;
	}
	
	public String createDomainMail()
	{
		String query = PsqlQuery.DOMAIN_MAIL.getQuery();
		
		return query;
	}
	
	public String createSequenceId()
	{
		String query = PsqlQuery.SEQUENCE_ID.getQuery();
		
		return query;
	}
	
	public String createSequenceAccount()
	{
		String query = PsqlQuery.SEQUENCE_ACC_NUM.getQuery();
		
		return query;
	}

	@Override
	public String checkDomainInteger() {
		
		String query = PsqlQuery.CHECK_DOMAIN_INTEGER.getQuery();
		
		return query;
	}

	@Override
	public String checkDomainMail() {
		
		String query = PsqlQuery.CHECK_DOMAIN_MAIL.getQuery();
		
		return query;
		
	}

}
