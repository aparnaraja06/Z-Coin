package coinDb;

public enum MysqlQuery {
	
	CREATE_DATABASE("CREATE DATABASE IF NOT EXISTS zCoin"),

	CREATE_TABLE_USER("CREATE TABLE IF NOT EXISTS user(user_id int auto_increment,name varchar(50) not null,"
			+ "mobile long, human_id varchar(12), password varchar(8) not null, rc_amount double not null,"
			+ "approved boolean default false,role varchar(10) default \"user\",unique(human_id),"
			+ " primary key (user_id))Engine=InnoDb auto_increment=100"),

	CREATE_TABLE_MAIL("CREATE TABLE IF NOT EXISTS mail(user_id int,"
			+ " mail_id varchar(100)not null,foreign key(user_id) references user(user_id))"),

	CREATE_TABLE_ACCOUNT(
			"CREATE TABLE IF NOT EXISTS account(user_id int not null, account_num int not null auto_increment,"
					+ " rc_amount double not null, zc_amount double, primary key(account_num), "
					+ "foreign key(user_id) references user(user_id))Engine=InnoDb auto_increment=1000"),

	CREATE_TABLE_TRANSACTION("CREATE TABLE IF NOT EXISTS transaction(user_id int not null, from_account int not null,"
			+ " to_account int not null, type varchar(10) not null, amount double , date varchar(20),"
			+ " foreign key(user_id) references user(user_id), "
			+ "foreign key(from_account) references account(account_num))"),

	INSERT_USER("INSERT INTO user(name,mobile,human_id,password,rc_amount) VALUES(?,?,?,?,?)"),

	INSERT_MAIL("INSERT INTO mail(user_id,mail_id) VALUES(?,?)"),

	INSERT_ACCOUNT("INSERT INTO account(user_id,rc_amount) VALUES(?,?)"),

	INSERT_TRANSACTION(
			"INSERT INTO transaction(user_id,from_account,to_account,type,amount,date) " + "VALUES(?,?,?,?,?,?)"),

	SELECT_WAITING("SELECT * FROM user WHERE approved=false"),

	GET_USER_BY_ID("SELECT * FROM user WHERE user_id=?"),

	GET_ACC_BY_ID("SELECT * FROM account WHERE user_id=?"),

	SELECT_TRANSACTION("SELECT * FROM transaction"),

	APPROVE_USER("UPDATE user SET approved=true WHERE user_id=?"),

	APPROVE_ADMIN("UPDATE user SET approved=true, role =? WHERE user_id=?"),

	UPDATE_PASSWORD("UPDATE user SET password=? WHERE user_id=?"),

	UPDATE_NAME("UPDATE user SET name=? WHERE user_id=?"),

	UPDATE_RC_AMOUNT("UPDATE account SET rc_amount=? WHERE account_num=?"),
	// "UPDATE account SET rc_amount=? WHERE account_num=?",
	UPDATE_ZC_AMOUNT("UPDATE account SET zc_amount=? WHERE account_num=?"),
	// "UPDATE account SET zc_amount=? WHERE account_num=?"),

	SELECT_ACCOUNT("SELECT account_num FROM account WHERE user_id=?"),

	GET_RC_AMOUNT("SELECT rc_amount FROM account WHERE account_num=?"),

	GET_ZC_AMOUNT("SELECT zc_amount FROM account WHERE account_num=?"),

	HISTORY_BY_ID("SELECT * FROM transaction WHERE user_id=?"),

	GET_ID_BY_MAIL("SELECT user_id FROM mail WHERE mail_id=?"),

	GET_MAIL_BY_ID("SELECT mail_id FROM mail WHERE user_id=?"),

	GET_MAIL("SELECT mail_id FROM mail WHERE mail_id=?"),

	CHANGE_ZC_AMOUNT("UPDATE account SET zc_amount=zc_amount * ? WHERE zc_amount > 0"),

	GET_ROLE_BY_ID("SELECT role FROM user WHERE user_id=?"),

	GET_PASSWORD("SELECT password FROM user WHERE user_id=?"),
	
	ALL_USER("SELECT * FROM user"),
	
	ALL_MAIL("SELECT * FROM mail"),
	
	ALL_ACCOUNTS("SELECT * FROM account");

	private final String query;

	MysqlQuery(String query) {

		this.query = query;
	}

	public String getQuery() {

		return query;
	}

}
