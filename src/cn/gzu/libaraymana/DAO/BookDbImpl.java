package cn.gzu.libaraymana.DAO;

import java.util.ArrayList;
import java.util.List;

import cn.gzu.libaraymana.domain.Book;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 图书信息数据业务实现类
 * @author Gym Yung
 * @version 2014-07-02 19:03
 */
public class BookDbImpl {
	private SQLiteOpenHelper mOpenHelper;
	
	public BookDbImpl(Context context) {
		// TODO Auto-generated constructor stub
		mOpenHelper = SqlDbHelper.getInstance(context);
	}
	
	/**
	 * 保存图书信息
	 * @param book	图书信息
	 */
	public void save(Book book){
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()){
			ContentValues values = new ContentValues();
			
			values.put("userid", book.getUserid());
			if(book.getBookname()!=null&& !"".equals(book.getBookname())) values.put("bookname", book.getBookname());
			if(book.getMajor()!=null&& !"".equals(book.getMajor())) values.put("major", book.getMajor());
			if(book.getCode()!=null && !"".equals(book.getCode())) values.put("code", book.getCode());
			values.put("price", book.getPrice());
			if(book.getAuthor()!=null&& !"".equals(book.getAuthor())) values.put("author", book.getAuthor());
			if(book.getPress()!=null&& !"".equals(book.getPress())) values.put("press", book.getPress());
			values.put("existstate", book.getExiststate());
			values.put("date", book.getDate());
			
			db.insert("book", "bookid", values);
			db.close();
		}
	}
	
	/**
	 * 删除图书信息
	 * @param bookname	图书信息名称
	 */
	public void delete(String bookname){
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()){
			db.delete("book", " bookname = ? ", new String[]{bookname});
			db.close();
		}
	}
	
	/**
	 * 修改图书信息
	 * @param book	图书信息
	 */
	public void update(int bookid,Book book){
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()){
			ContentValues values = new ContentValues();
			
			values.put("userid", book.getUserid());
			if(book.getBookname()!=null&& !"".equals(book.getBookname())) values.put("bookname", book.getBookname());
			if(book.getMajor()!=null&& !"".equals(book.getMajor())) values.put("major", book.getMajor());
			if(book.getCode()!=null && !"".equals(book.getCode())) values.put("code", book.getCode());
			values.put("price", book.getPrice());
			if(book.getAuthor()!=null&& !"".equals(book.getAuthor())) values.put("author", book.getAuthor());
			if(book.getPress()!=null&& !"".equals(book.getPress())) values.put("press", book.getPress());
			values.put("existstate", book.getExiststate());
			values.put("date", book.getDate());
			
			db.update("book", values, " bookid = ? ", new String[]{bookid+""});
			db.close();
		}
	}
	
	
	/**
	 * 根据bookid查询图书信息
	 * @param bookid	bookid
	 */
	public Book queryBookById(int bookid){
		Book book = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if(db.isOpen()){
			Cursor c = db.query("book", new String[]{"author","userid","bookname","major","code","price","author","press","existstate","date"}, " bookid = ? ", new String[]{bookid+""}, null, null, null);
			if(c.moveToFirst()){
				book = new Book();
				book.setBookid(bookid);
				book.setUserid(c.getInt(c.getColumnIndex("userid")));
				book.setBookname(c.getString(c.getColumnIndex("bookname")));
				book.setMajor(c.getString(c.getColumnIndex("major")));
				book.setCode(c.getString(c.getColumnIndex("code")));
				book.setPrice(c.getFloat(c.getColumnIndex("price")));
				book.setAuthor(c.getString(c.getColumnIndex("author")));
				book.setPress(c.getString(c.getColumnIndex("press")));
				book.setExiststate(c.getInt(c.getColumnIndex("existstate")));
				book.setDate(c.getLong(c.getColumnIndex("date")));
			}
			c.close();
			db.close();
		}
		return book;
	}
	
	/**
	 * 根据code查询图书信息
	 * @param bookid	code
	 */
	public Book queryBookByCode(String code){
		Book book = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if(db.isOpen()){
			Cursor c = db.query("book", new String[]{"author","userid","bookname","major","bookid","price","author","press","existstate","date"}, " code = ? ", new String[]{code}, null, null, null);
			if(c.moveToFirst()){
				book = new Book();
				book.setBookid(c.getInt(c.getColumnIndex("bookid")));
				book.setUserid(c.getInt(c.getColumnIndex("userid")));
				book.setBookname(c.getString(c.getColumnIndex("bookname")));
				book.setMajor(c.getString(c.getColumnIndex("major")));
				book.setCode(code);
				book.setPrice(c.getFloat(c.getColumnIndex("price")));
				book.setAuthor(c.getString(c.getColumnIndex("author")));
				book.setPress(c.getString(c.getColumnIndex("press")));
				book.setExiststate(c.getInt(c.getColumnIndex("existstate")));
				book.setDate(c.getLong(c.getColumnIndex("date")));
			}
			c.close();
			db.close();
		}
		return book;
	}
	
	
	/**
	 * 根据作者名查询图书信息
	 * @param bookname	作者名
	 */
	public Book queryBookByAuthor(String author){
		Book book = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if(db.isOpen()){
			Cursor c = db.query("book", new String[]{"bookid","userid","bookname","major","code","price","author","press","existstate","date"}, " author = ? ", new String[]{author}, null, null, null);
			if(c.moveToFirst()){
				book = new Book();
				book.setBookid(c.getInt(c.getColumnIndex("bookid")));
				book.setUserid(c.getInt(c.getColumnIndex("userid")));
				book.setBookname(c.getString(c.getColumnIndex("bookname")));
				book.setMajor(c.getString(c.getColumnIndex("major")));
				book.setCode(c.getString(c.getColumnIndex("code")));
				book.setPrice(c.getFloat(c.getColumnIndex("price")));
				book.setAuthor(author);
				book.setPress(c.getString(c.getColumnIndex("press")));
				book.setExiststate(c.getInt(c.getColumnIndex("existstate")));
				book.setDate(c.getLong(c.getColumnIndex("date")));
			}
			c.close();
			db.close();
		}
		return book;
	}
	
	/**
	 * 根据专业领域查询图书信息
	 * @param major 专业领域
	 */
	public Book queryBookByMajor(String major){
		Book book = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if(db.isOpen()){
			Cursor c = db.query("book", new String[]{"bookid","userid","bookname","major","code","price","author","press","existstate","date"}, " major = ? ", new String[]{major}, null, null, null);
			if(c.moveToFirst()){
				book = new Book();
				book.setBookid(c.getInt(c.getColumnIndex("bookid")));
				book.setUserid(c.getInt(c.getColumnIndex("userid")));
				book.setBookname(c.getString(c.getColumnIndex("bookname")));
				book.setMajor(major);
				book.setCode(c.getString(c.getColumnIndex("code")));
				book.setPrice(c.getFloat(c.getColumnIndex("price")));
				book.setAuthor(c.getString(c.getColumnIndex("author")));
				book.setPress(c.getString(c.getColumnIndex("press")));
				book.setExiststate(c.getInt(c.getColumnIndex("existstate")));
				book.setDate(c.getLong(c.getColumnIndex("date")));
			}
			c.close();
			db.close();
		}
		return book;
	}
	
	/**
	 * 根据图书名查询图书信息
	 * @param bookname	图书名
	 */
	public Book queryBookByName(String bookname){
		Book book = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if(db.isOpen()){
			Cursor c = db.query("book", new String[]{"bookid","userid","bookname","major","code","price","author","press","existstate","date"}, " bookname = ? ", new String[]{bookname}, null, null, null);
			if(c.moveToFirst()){
				book = new Book();
				book.setBookid(c.getInt(c.getColumnIndex("bookid")));
				book.setUserid(c.getInt(c.getColumnIndex("userid")));
				book.setBookname(bookname);
				book.setMajor(c.getString(c.getColumnIndex("major")));
				book.setCode(c.getString(c.getColumnIndex("code")));
				book.setPrice(c.getFloat(c.getColumnIndex("price")));
				book.setAuthor(c.getString(c.getColumnIndex("author")));
				book.setPress(c.getString(c.getColumnIndex("press")));
				book.setExiststate(c.getInt(c.getColumnIndex("existstate")));
				book.setDate(c.getLong(c.getColumnIndex("date")));
			}
			c.close();
			db.close();
		}
		return book;
	}
	
	
	
	/**
	 * //得到所有的图书信息
	 * @return 图书信息集合
	 */
	public List<Book> findAll(){
		List<Book> books = new ArrayList<Book>();
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if(db.isOpen()){
		Cursor c = db.query("book", new String[]{"bookid","userid","bookname","major","code","price","author","press","existstate","date"}, null, null, null, null, null);
		while(c.moveToNext()){
			Book book = new Book();
			book.setBookid(c.getInt(c.getColumnIndex("bookid")));
			book.setUserid(c.getInt(c.getColumnIndex("userid")));
			book.setBookname(c.getString(c.getColumnIndex("bookname")));
			book.setMajor(c.getString(c.getColumnIndex("major")));
			book.setCode(c.getString(c.getColumnIndex("code")));
			book.setPrice(c.getFloat(c.getColumnIndex("price")));
			book.setAuthor(c.getString(c.getColumnIndex("author")));
			book.setPress(c.getString(c.getColumnIndex("press")));
			book.setExiststate(c.getInt(c.getColumnIndex("existstate")));
			book.setDate(c.getLong(c.getColumnIndex("date")));
			books.add(book);
		}
		c.close();
		db.close();
		}
		return books;
	}
	
}
