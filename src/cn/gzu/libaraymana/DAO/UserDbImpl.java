package cn.gzu.libaraymana.DAO;

import cn.gzu.libaraymana.domain.User;
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
public class UserDbImpl {
	private SQLiteOpenHelper mOpenHelper;
	
	public UserDbImpl(Context context) {
		// TODO Auto-generated constructor stub
		mOpenHelper = SqlDbHelper.getInstance(context);
	}
	
	/**
	 * 保存借书人信息
	 * @param user	图书信息
	 */
	public void save(User user){
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()){
			ContentValues values = new ContentValues();
			
			if(user.getUsername()!=null && !"".equals(user.getUsername())) values.put("username", user.getUsername());
			if(user.getPassword()!=null && !"".equals(user.getPassword())) values.put("password", user.getPassword());
			if(user.getGender()!=null&& !"".equals(user.getUsername())) values.put("gender", user.getGender());
			if(user.getUsercode()>0) values.put("usercode", user.getUsercode());
			if(user.getBookids()!=null && !"".equals(user.getUsername())) values.put("bookids", user.getBookids());
			if(user.getBr_count()>0) values.put("br_count", user.getBr_count());
			if(user.getPay()>0) values.put("pay", user.getPay());
			
			db.insert("user", "userid", values);
			db.close();
		}
	}
	
	/**
	 * 删除借书人信息
	 * @param username	借书人信息
	 */
	public void delete(String username){
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()){
			db.delete("user", " username = ? ", new String[]{username});
			db.close();
		}
	}
	
	
	/**
	 * 根据借书人id查询借书人信息
	 * @param userid 借书人信息名
	 */
	public User queryBookByUserId(int userid){
		User user = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if(db.isOpen()){
			Cursor c = db.query("user", new String[]{"username","password","usercode","gender","bookids","br_count","pay"}, " userid = ? ", new String[]{userid+""}, null, null, null);
			if(c.moveToFirst()){
				user = new User();
				//username text,gender text,usercode integer,bookids text,br_count integer,pay real
				user.setUserid(userid);
				user.setUsername(c.getString(c.getColumnIndex("username")));
				user.setPassword(c.getString(c.getColumnIndex("password")));
				user.setGender(c.getString(c.getColumnIndex("gender")));
				user.setUsercode(c.getInt(c.getColumnIndex("usercode")));
				user.setBookids(c.getString(c.getColumnIndex("bookids")));
				user.setBr_count(c.getInt(c.getColumnIndex("br_count")));
				user.setPay(c.getFloat(c.getColumnIndex("pay")));
			}
			c.close();
			db.close();
		}
		return user;
	}
	
	
	/**
	 * 根据借书人信息名查询借书人信息
	 * @param username 借书人信息名
	 */
	public User queryBookByUserName(String username){
		User user = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if(db.isOpen()){
			Cursor c = db.query("user", new String[]{"userid","password","usercode","gender","bookids","br_count","pay"}, " username = ? ", new String[]{username}, null, null, null);
			if(c.moveToFirst()){
				user = new User();
				//username text,gender text,usercode integer,bookids text,br_count integer,pay real
				user.setUserid(c.getInt(c.getColumnIndex("userid")));
				user.setUsername(username);
				user.setPassword(c.getString(c.getColumnIndex("password")));
				user.setGender(c.getString(c.getColumnIndex("gender")));
				user.setUsercode(c.getInt(c.getColumnIndex("usercode")));
				user.setBookids(c.getString(c.getColumnIndex("bookids")));
				user.setBr_count(c.getInt(c.getColumnIndex("br_count")));
				user.setPay(c.getFloat(c.getColumnIndex("pay")));
			}
			c.close();
			db.close();
		}
		return user;
	}
	
	/**
	 * 根据借书人学号查询借书人信息
	 * @param usercode 借书人学号
	 */
	public User queryBookByUserCode(int usercode){
		User user = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if(db.isOpen()){
			Cursor c = db.query("user", new String[]{"userid","username","password","gender","bookids","br_count","pay"}, " usercode = ? ", new String[]{usercode+""}, null, null, null);
			if(c.moveToFirst()){
				user = new User();
				user.setUserid(c.getInt(c.getColumnIndex("userid")));
				user.setUsername(c.getString(c.getColumnIndex("username")));
				user.setPassword(c.getString(c.getColumnIndex("password")));
				user.setGender(c.getString(c.getColumnIndex("gender")));
				user.setUsercode(usercode);
				user.setBookids(c.getString(c.getColumnIndex("bookids")));
				user.setBr_count(c.getInt(c.getColumnIndex("br_count")));
				user.setPay(c.getFloat(c.getColumnIndex("pay")));
			}
			c.close();
			db.close();
		}
		return user;
	}
	
	/**
	 * 修改借书人信息
	 * @param book	图书信息
	 */
	public void update(int userid,User user){
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()){
			ContentValues values = new ContentValues();
			
			if(user.getBr_count()>0) values.put("br_count", user.getBr_count());
			if(user.getGender()!=null && !"".equals(user.getGender())) values.put("gender", user.getGender());
			if(user.getPassword()!=null && !"".equals(user.getPassword())) values.put("password", user.getPassword());
			if(user.getPay()>0) values.put("pay", user.getPay());
			if(user.getUsercode()>0) values.put("usercode", user.getUsercode());
			if(user.getUsername()!=null && !"".equals(user.getUsername())) values.put("username", user.getUsername());
			
			if(user.getBookids()!=null && !"".equals(user.getBookids())){
				String[] bookids = user.getBookids().split("-");
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<bookids.length;i++){
					if(i>=10) break;
					sb.append(bookids[i]).append('-');
				}
				sb.deleteCharAt(sb.length()-1);
				values.put("bookids", sb.toString());
			}
			
			db.update("user", values, " userid = ? ", new String[]{userid+""});
			db.close();
		}
	}
	
}
