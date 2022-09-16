package transaction;

public class Transaction {

	private int user_id;
	private int from_account;
	private int to_account;
	private String type;
	private double amount;
	private String date;

	public String getDate() {

		return date;
	}

	public void setDate(String date) {
		
		this.date = date;
		
	}

	public int getUser_id() {
		
		return user_id;
	}

	public void setUser_id(int user_id) {
		
		this.user_id = user_id;
		
	}

	public int getFrom_account() {
		
		return from_account;
	}

	public void setFrom_account(int from_account) {
		
		this.from_account = from_account;
		
	}

	public int getTo_account() {
		
		return to_account;
	}

	public void setTo_account(int to_account) {
		
		this.to_account = to_account;
		
	}

	public String getType() {
		
		return type;
	}

	public void setType(String type) {
		
		this.type = type;
		
		
	}

	public double getAmount() {
		
		return amount;
	}

	public void setAmount(double amount) {
		
		this.amount = amount;
		
	}

	@Override
	public String toString() {
		return "user_id : " + user_id + ", from_account : " + from_account + ", to_account : " + to_account
				+ ", type : " + type + ", amount : " + amount + ", date : " + date;
	}

}
