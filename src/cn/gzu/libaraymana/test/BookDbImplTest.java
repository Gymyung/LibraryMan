package cn.gzu.libaraymana.test;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.BookDbImpl;
import cn.gzu.libaraymana.DAO.UserDbImpl;
import cn.gzu.libaraymana.domain.Book;
import cn.gzu.libaraymana.domain.User;
/**
 * 数据库操作测试类 这里直接用activity
  * @author Gym Yung
  * @version 2014-07-02 21:02
 */
public class BookDbImplTest extends Activity {
	private BookDbImpl bookDbImpl;
	private UserDbImpl userDbImpl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.dbtest);
		
		bookDbImpl = new BookDbImpl(this);
		userDbImpl = new UserDbImpl(this);
	}
	
	/** 图书添加测试  **/
	public void bookAddTest(View view){
		Book book = new Book();
		book.setAuthor("曹雪芹");
		book.setBookname("红楼梦");
		book.setCode("HLM001");
		book.setMajor("文学院");
		book.setPress("贵州大学出版社");
		book.setPrice(57.5f);
		book.setUserid(1);
		bookDbImpl.save(book);
		Toast.makeText(getBaseContext(), "图书添加成功！", 1).show();
		System.out.println("图书添加成功！");
	}
	
	/** 图书删除测试  **/
	public void bookDeleteTest(View view){
		bookDbImpl.delete("新版红楼梦");
		System.out.println("图书删除测试成功！");
	}
	
	/** 图书修改测试 **/
	public void bookUpdateTest(View view){
		Book book = bookDbImpl.queryBookById(2);
		book.setBookname("新版红楼梦");
		bookDbImpl.update(2, book);
		System.out.println("图书修改测试成功！");
	}
	
	/** 图书查询测试  **/
	public void bookQueryTest(View view){
		List<Book> books = bookDbImpl.findAll();
		
		for(Book book : books){
			System.out.println(book.getBookname());
		}
		
		Toast.makeText(getBaseContext(), "图书查询测试成功！"+books.size(), 1).show();
	}
	
	
	
	/** 借书人添加测试  **/
	public void userAddTest(View view){
		User user = new User();
		user.setUsername("系统管理员");
		user.setPassword("123");
		user.setBookids("6-5");
		user.setGender("男");
		user.setUsercode(1207010209);
		user.setPay(0f);
		userDbImpl.save(user);
		Toast.makeText(getBaseContext(), "借书人添加测试成功！", 1).show();
		System.out.println("借书人添加测试成功！");
	}
	
	/** 借书人查询测试  **/
	public void userQueryTest(View view){
		User user = userDbImpl.queryBookByUserCode(1207010209);
		Toast.makeText(getBaseContext(), "欢迎您："+user.getUsername(), 1).show();
		System.out.println("欢迎您："+user.getUsername());
	}
	
}
