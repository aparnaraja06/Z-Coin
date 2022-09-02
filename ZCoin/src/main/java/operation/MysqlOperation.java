package operation;

import java.sql.Connection;

import coinDb.MysqlConnection;
import coinDb.MysqlQuery;

public class MysqlOperation implements ChooseDb{

	@Override
	public String createUserTable() {
		
		String query = MysqlQuery.CREATE_TABLE_USER.getQuery();
		
		return query;
	}

	@Override
	public String getRole() {
		
		String query = MysqlQuery.GET_ROLE_BY_ID.getQuery();
		
		return query;
	}

	@Override
	public String getPassword() {
		
		String query = MysqlQuery.GET_PASSWORD.getQuery();
		
		return query;
	}

	@Override
	public String addUser() {
		
		String query = MysqlQuery.INSERT_USER.getQuery();
		
		return query;
	}

	@Override
	public String showWaitingList() {
		
		String query = MysqlQuery.SELECT_WAITING.getQuery();
		
		return query;
	}

	@Override
	public String approveAsUser() {
		
		String query = MysqlQuery.APPROVE_USER.getQuery();
		
		return query;
	}

	@Override
	public String approveAsAdmin() {
		
		String query = MysqlQuery.APPROVE_ADMIN.getQuery();
		
		return query;
	}

	@Override
	public String getUser() {
		
		String query = MysqlQuery.GET_USER_BY_ID.getQuery();
		
		return query;
	}

	@Override
	public String changePassword() {
		
		String query = MysqlQuery.UPDATE_PASSWORD.getQuery();
		
		return query;
	}

	@Override
	public String updateName() {
		
		String query = MysqlQuery.UPDATE_NAME.getQuery();
		
		return query;
	}

	@Override
	public String createMailTable() {
		
		String query = MysqlQuery.CREATE_TABLE_MAIL.getQuery();
		
		return query;
	}

	@Override
	public String getId() {
		
		String query = MysqlQuery.GET_ID_BY_MAIL.getQuery();
		
		return query;
	}

	@Override
	public String addMail() {
		
		String query = MysqlQuery.INSERT_MAIL.getQuery();
		
		return query;
	}

	@Override
	public String getMailById() {
		
		String query = MysqlQuery.GET_MAIL_BY_ID.getQuery();
		
		return query;
	}

	@Override
	public String checkMailExists() {
		
		String query = MysqlQuery.GET_MAIL.getQuery();
		
		return query;
	}

	@Override
	public String createAccountTable() {
		
		String query = MysqlQuery.CREATE_TABLE_ACCOUNT.getQuery();
		
		return query;
	}

	@Override
	public String addAccount() {
		
		String query = MysqlQuery.INSERT_ACCOUNT.getQuery();
		
		return query;
	}

	@Override
	public String accountDetails() {
		
		String query = MysqlQuery.GET_ACC_BY_ID.getQuery();
		
		return query;
	}

	@Override
	public String getAccountNumById() {
		
		String query = MysqlQuery.SELECT_ACCOUNT.getQuery();
		
		return query;
	}

	@Override
	public String getRcBalance() {
		
		String query = MysqlQuery.GET_RC_AMOUNT.getQuery();
		
		return query;
	}


	@Override
	public String updateRcAmount() {
		
      String query = MysqlQuery.UPDATE_RC_AMOUNT.getQuery();
		
		return query;
		
	}

	@Override
	public String getZcBalance() {
		
		String query = MysqlQuery.UPDATE_ZC_AMOUNT.getQuery();
		
		return query;
	}


	@Override
	public String updateZcAmount() {
		
      String query = MysqlQuery.UPDATE_ZC_AMOUNT.getQuery();
		
		return query;
	}

	@Override
	public String changeZcAmount() {
		
		String query = MysqlQuery.CHANGE_ZC_AMOUNT.getQuery();
		
		return query;
	}

	@Override
	public String createTransactionTable() {
		
		String query = MysqlQuery.CREATE_TABLE_TRANSACTION.getQuery();
		
		return query;
	}

	@Override
	public String addTransaction() {
		
		String query = MysqlQuery.INSERT_TRANSACTION.getQuery();
		
		return query;
	}

	@Override
	public String getAllHistory() {
		
		String query = MysqlQuery.SELECT_TRANSACTION.getQuery();
		
		return query;
	}

	@Override
	public String getHistoryByUserId() {
		
		String query = MysqlQuery.HISTORY_BY_ID.getQuery();
		
		return query;
	}

	@Override
	public String createDatabase() {
		
		String query = MysqlQuery.CREATE_DATABASE.getQuery();
		
		return query;
	}

	@Override
	public Connection getConnection()throws CustomException {
		
		Connection connect = MysqlConnection.CONNECTION.getConnection();
		
		return connect;
	}

	@Override
	public String createDomainInteger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createDomainMail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createSequenceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createSequenceAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkDomainInteger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkDomainMail() {
		// TODO Auto-generated method stub
		return null;
	}

}
