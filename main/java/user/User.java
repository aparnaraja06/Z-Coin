package user;

public class User {

	private int user_id;
	private String name;
	private long mobile;
	private String human_id;
	private String password;
	private double rc_amount;
	private String role = "user";
	private boolean approved = false;
	private String mail;

	public String getMail() {
		
		
		 // ZCoin.User user = ZCoin.User.newBuilder().build();
		 
		 //return user.getMail();
		 

		return mail;
	}

	public void setMail(String mail) {
		
		this.mail = mail;
		
		ZCoin.User.newBuilder().setMail(mail).build();
	}

	public int getUser_id() {
		
		return user_id;
	}

	public void setUser_id(int user_id) {
		
		this.user_id = user_id;
		
		ZCoin.User.newBuilder().setUserId(user_id).build();
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
		
		ZCoin.User.newBuilder().setName(name).build();
	}

	public long getMobile() {
		
		return mobile;
	}

	public void setMobile(long mobile) {
		
		this.mobile = mobile;
		
		ZCoin.User.newBuilder().setMobile(mobile).build();
	}

	public String getHuman_id() {
		
		return human_id;
	}

	public void setHuman_id(String human_id) {
		
		this.human_id = human_id;
		
		ZCoin.User.newBuilder().setHumanId(human_id).build();
	}

	public String getPassword() {
		
		return password;
	}

	public void setPassword(String password) {
		
		this.password = password;
		
		ZCoin.User.newBuilder().setPassword(password).build();
	}

	public double getRc_amount() {
		
		return rc_amount;
	}

	public void setRc_amount(double rc_amount) {
		
		this.rc_amount = rc_amount;
		
		ZCoin.User.newBuilder().setRcAmount(rc_amount).build();
	}

	public String getRole() {
		
		return role;
	}

	public void setRole(String role) {
		
		this.role = role;
		
		ZCoin.User.newBuilder().setRole(role).build();
	}

	public boolean isApproved() {
		
		return approved;
	}

	public void setApproved(boolean approved) {
		
		this.approved = approved;
		
		ZCoin.User.newBuilder().setApproved(approved).build();
	}

	@Override
	public String toString() {
		return "user_id : " + user_id + ", name : " + name + ", mobile : " + mobile + ", human_id : " + human_id
				+ ", password : " + password + ", rc_amount : " + rc_amount + ", mail : " + mail + ", role : " + role
				+ ", approved : " + approved;
	}

}
