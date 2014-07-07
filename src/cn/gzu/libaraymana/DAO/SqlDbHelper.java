package cn.gzu.libaraymana.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 数据库操作类（建立数据表）
 * @author Gym Yung
 * @version 2014-07-02 18:02
 */
public class SqlDbHelper extends SQLiteOpenHelper {
	private static SQLiteOpenHelper mInstance;
	
	private final static String name = "librarydatas.db";
	
	public static SQLiteOpenHelper getInstance(Context context){
		if(mInstance == null){
			mInstance = new SqlDbHelper(context, name, null, 1);
		}
		return mInstance;
	}
	
	private SqlDbHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table book(bookid integer primary key autoincrement,userid integer,bookname text,major text,code text,price real," +
        		"author text,press text,existstate integer,date real)");
        
        db.execSQL("create table user(userid integer primary key autoincrement,username text,password text,gender text,usercode integer,bookids text,br_count integer,pay real)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}


}
