package cn.gzu.libaraymana.activities;

import java.util.Date;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.BookDbImpl;
import cn.gzu.libaraymana.DAO.UserDbImpl;
import cn.gzu.libaraymana.Util.CommonUtil;
import cn.gzu.libaraymana.domain.Book;
import cn.gzu.libaraymana.domain.User;
/**
 * 查询结果界面
 * @author GymYung
 * @since 2014-07-08 23:11
 */
public class QueryResultActivity extends BaseActivity {
	/** 返回上一步 **/
	private ImageView previousImg;
	/** 菜单 **/
	private ImageView menuImg;
	/** 查询结果显示 **/
	private TextView infoShowTx;
	/** 加载中提示信息 **/
	private TextView loadingTx;
	
	private String information;
	/** 图书查询方式 1按作者名查询 2按专业查询 3按图书名查询 4查询用户**/
	private int searchWaysIndex;
	
	private UserDbImpl userDbImpl;
	private BookDbImpl bookDbImpl;

	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_query_result);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.query_result_previous_img);
		menuImg = (ImageView) findViewById(R.id.query_result_menu_img);
		infoShowTx = (TextView) findViewById(R.id.query_result_tx);
		loadingTx = (TextView) findViewById(R.id.query_result_load_tx);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		loadingTx.setVisibility(View.VISIBLE);
		userDbImpl = new UserDbImpl(this);
		bookDbImpl = new BookDbImpl(this);
		
		information = getIntent().getStringExtra("information");
		searchWaysIndex = getIntent().getIntExtra("searchWaysIndex", 1);
		
		if(searchWaysIndex==4){
			queryUserByCode();
		}else{
			/** 图书查询方式 3按图书名查询 **/
			Book book = bookDbImpl.queryBookByName(information);
			showBookInfo(book);
		}
		
	}
	
	
	/** 图书查询方式 4查询用户**/
	private void queryUserByCode(){
		User user = userDbImpl.queryBookByUserCode(Integer.parseInt(information));
		
		String info = "\n学号："+user.getUsercode()+"\n姓名："+user.getUsername()+"\n性别："+user.getGender()+"\n已借图书："+user.getBr_count()+"本\n欠款情况："+user.getPay()+"元\n";
		
		if(user.getBookids().length()>0){
			String[] bookids = user.getBookids().split("-");
			info += "\n已借图书：\n";
			int index = 0;
			for(String bookid : bookids){
				Book book = bookDbImpl.queryBookById(Integer.parseInt(bookid));
				if(book!=null) info += "    "+(++index)+"：  "+book.getBookname()+" 【"+book.getAuthor()+"】";
			}
		}
		
		loadingTx.setVisibility(View.GONE);
		
		infoShowTx.setText(info);
	}
	
	
	private void showBookInfo(Book book){
		String info = "";
		if(book!=null){
			info += "\n图书id："+book.getBookid()+"\n图书全名："+book.getBookname()+"\n作者："+book.getAuthor()+"\n图书编号："+book.getCode()
					+"\n上次操作时间："+CommonUtil.formatTime(new Date(book.getDate()))+"\n图书流通状态：";
			
			info += (book.getExiststate()==1) ? "在架" : "已借出";
			
			info += "\n专业领域："+book.getMajor()+"\n出版社："+book.getPress()+"\n购进价格："+book.getPrice()+"元\n";
			
			if(book.getUserid()>0){
				User user = userDbImpl.queryBookByUserId(book.getUserid());
				if(user!=null){
					info += "\n借书人学号："+user.getUsercode()+"\n借书人姓名："+user.getUsername()+"\n借书人性别："+user.getGender()+"\n";
				}
			}
		}else{
			info += "查询出错...";
		}
		
		loadingTx.setVisibility(View.GONE);
		
		infoShowTx.setText(info);
	}
	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.query_result_previous_img:
			
			finish();
			
			break;
		case R.id.query_result_menu_img:
			
			goToMainActivity();
			
			break;
		}
	}
	
	
	/** 回到主界面 **/
	private void goToMainActivity(){
		Intent intent = new Intent(QueryResultActivity.this,MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
		QueryResultActivity.this.finish();
	}
}
