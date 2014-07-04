package cn.gzu.libaraymana.domain;

import java.util.Date;
/**
 * 图书信息业务bean
 * @author Gym Yung
 * @version 2014-07-02 19:03
 */
public class Book {
	/** 图书id **/
	private int bookid;
	/** 借书人id： **/
	private int userid = 0;
	/** 图书名称 **/
	private String bookname;
	/** 专业领域 **/
	private String major;
	/** 图书编号 **/
	private String code;
	/** 单价 **/
	private float price;
	/** 作者 **/
	private String author;
	/** 出版社 **/
	private String press;
	/** 存在状态  1在架 0借出 **/
	private int existstate = 1;
	/** 借出时间 **/
	@SuppressWarnings("deprecation")
	private String date = new Date().toLocaleString();
	
	public Book(){}
	
	public Book(String bookname, String major, String code, float price,
			String author, String press, int existstate) {
		this.bookname = bookname;
		this.major = major;
		this.code = code;
		this.price = price;
		this.author = author;
		this.press = press;
		this.existstate = existstate;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public int getExiststate() {
		return existstate;
	}
	public void setExiststate(int existstate) {
		this.existstate = existstate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
