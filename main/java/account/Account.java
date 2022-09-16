package account;

public class Account {

	private int user_id;
	private int account_num;
	private double rc_amount;
	private double zc_amount;

	public int getUser_id() {
		
		return user_id;
	}

	public void setUser_id(int user_id) {
		
		this.user_id = user_id;
		
	}

	public int getAccount_num() {
		
		return account_num;
	}

	public void setAccount_num(int account_num) {
		
		this.account_num = account_num;
		
	}

	public double getRc_amount() {
		
		return rc_amount;
	}

	public void setRc_amount(double rc_amount) {
		
		this.rc_amount = rc_amount;
		
	}

	public double getZc_amount() {
		
		return zc_amount;
	}

	public void setZc_amount(double zc_amount) {
		
		this.zc_amount = zc_amount;
		
	}

	@Override
	public String toString() {
		return "user_id : " + user_id + ", account_num : " + account_num + ", rc_amount : " + rc_amount
				+ ", zc_amount : " + zc_amount;
	}

}
