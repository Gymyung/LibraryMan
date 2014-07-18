package cn.gzu.libaraymana.domain;
/**
  * 借书人业务bean实体对象
  * @author Gym Yung
  * @version 2014-07-02 21:02
 */
public class User {
	/** 姓名： **/
	private int userid;
	/** 性别： **/
	private String username;
	/** 密码    **/
	private String password;
	/** 学号： **/
	private String gender;
	/** 姓名： **/
	private int usercode;
	/** 已借图书id(保存形式2-4-3-2)： **/
	private String bookids = "";
	/** 借书数量： **/
	private int br_count;
	/** 罚款信息： **/
	private float pay;
	
	
	public User(String username, String password, String gender, int usercode) {
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.usercode = usercode;
	}


	public User() {
		super();
	}
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getUsercode() {
		return usercode;
	}
	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}
	public String getBookids() {
		return bookids;
	}
	public void setBookids(String bookids) {
		this.bookids = bookids;
	}
	public int getBr_count() {
		return br_count;
	}
	public void setBr_count(int br_count) {
		this.br_count = br_count;
	}
	public float getPay() {
		return pay;
	}
	public void setPay(float pay) {
		this.pay = pay;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
