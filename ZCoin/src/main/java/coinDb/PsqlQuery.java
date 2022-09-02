package coinDb;


public enum PsqlQuery
{
	CREATE_DATABASE("SELECT 'CREATE DATABASE z_coin' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'z_coin')"),
	
	CHECK_DOMAIN_INTEGER("select exists (select 1 from pg_type where typname = 'check_integer')"),
	
	CHECK_DOMAIN_MAIL("select exists (select 1 from pg_type where typname = 'check_mail')"),
	
	DOMAIN_INTEGER("create domain check_integer varchar(12) NOT NULL CHECK(value ~'[0-9]+')"),
	
	DOMAIN_MAIL("CREATE DOMAIN check_mail varchar(100) NOT NULL CHECK(value ~'^[A-Za-z0-9+-._]+@(.+)$')"),
	
	SEQUENCE_ID("CREATE SEQUENCE IF NOT EXISTS u_id start 100 increment 1 owned by customer.user_id"),
	
	SEQUENCE_ACC_NUM("CREATE SEQUENCE IF NOT EXISTS"
			+ " accountNumber start 1000 increment 1 owned by account.account_num"),
	
	CREATE_TABLE_USER("CREATE TABLE IF NOT EXISTS customer(user_id serial primary key,name varchar (50) not null, "
			+ "mobile check_integer, human_id check_integer, password varchar(8) not null, "
			+ "rc_amount numeric(5,2) not null, approved boolean default false,role varchar(10) "
			+ "default 'user',constraint h_id unique(human_id))"),
			
	CREATE_TABLE_MAIL("CREATE TABLE IF NOT EXISTS mail(user_id int not null, mail_id check_mail, "
	   + "constraint email unique(mail_id), "
	   + "constraint fk_user foreign key(user_id) references customer(user_id))"),
	   
	CREATE_TABLE_ACCOUNT("CREATE TABLE IF NOT EXISTS account(user_id int not null, account_num "
			+ "serial not null primary key,"
        + "rc_amount numeric(5,2) not null, zc_amount numeric(5,2), "
        + "constraint fk_user foreign key(user_id) references customer(user_id))"),
        
	CREATE_TABLE_TRANSACTION("CREATE TABLE IF NOT EXISTS transaction(user_id int not null,from_account int not null,"
       + " to_account int not null, type varchar(20), amount numeric(5,2), date varchar(20), "
       + "constraint fk_user foreign key(user_id) references customer(user_id), "
       + "constraint fk_account foreign key(from_account) references account(account_num))"),
	
	INSERT_USER("INSERT INTO customer(user_id,name,mobile,human_id,password,rc_amount)"
			+ "VALUES(nextval('u_id'),?,?,?,?,?)"),
	
	INSERT_MAIL("INSERT INTO mail(user_id,mail_id) VALUES(?,?)"),
			
	INSERT_ACCOUNT("INSERT INTO account(user_id,account_num,rc_amount) VALUES(?,nextval('accountNumber'),?)"),
			
	INSERT_TRANSACTION("INSERT INTO transaction(user_id,from_account,to_account,type,amount,date) "
			+ "VALUES(?,?,?,?,?,?)"),
	
	SELECT_WAITING("SELECT * FROM customer WHERE approved=false"),
	
	GET_USER_BY_ID("SELECT * FROM customer WHERE user_id=?"),
			
	GET_ACC_BY_ID("SELECT * FROM account WHERE user_id=?"),
			
	SELECT_TRANSACTION("SELECT * FROM transaction"),
	
	APPROVE_USER("UPDATE customer SET approved=true WHERE user_id=?"),
	
	APPROVE_ADMIN("UPDATE customer SET approved=true, role =? WHERE user_id=?"),
			
	UPDATE_PASSWORD("UPDATE customer SET password=? WHERE user_id=?"),
			
	UPDATE_NAME("UPDATE customer SET name=? WHERE user_id=?"),
	
	UPDATE_RC_AMOUNT("UPDATE account SET rc_amount=? WHERE account_num=?"),
	
		//	("UPDATE account SET rc_amount=? WHERE account_num=?"),
			
	UPDATE_ZC_AMOUNT("UPDATE account SET zc_amount=? WHERE account_num=?"),
			
		//	("UPDATE account SET zc_amount=? WHERE account_num=?"),
	
	SELECT_ACCOUNT("SELECT account_num FROM account WHERE user_id=?"),
	
	GET_RC_AMOUNT("SELECT rc_amount FROM account WHERE account_num=?"),
			
	GET_ZC_AMOUNT("SELECT zc_amount FROM account WHERE account_num=?"),
			
	HISTORY_BY_ID("SELECT * FROM transaction WHERE user_id=?"),
	
	GET_ID_BY_MAIL("SELECT user_id FROM mail WHERE mail_id=?"),
	
	GET_MAIL_BY_ID("SELECT mail_id FROM mail WHERE user_id=?"),
			
	GET_MAIL("SELECT mail_id FROM mail WHERE mail_id=?"),
			
	CHANGE_ZC_AMOUNT("UPDATE account SET zc_amount=zc_amount * ? WHERE zc_amount > 0"),
	
	GET_ROLE_BY_ID("SELECT role FROM customer WHERE user_id=?"),
    
	GET_PASSWORD("SELECT password FROM customer WHERE user_id=?");
	
	
	private final String query;
	
	PsqlQuery(String query) {
		
		this.query=query;
	}
	
	public String getQuery() {

		return query;
	}

	

}
