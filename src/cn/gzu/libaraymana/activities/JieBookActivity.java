package cn.gzu.libaraymana.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.BookDbImpl;
import cn.gzu.libaraymana.DAO.UserDbImpl;
import cn.gzu.libaraymana.Util.DispacUtil;
import cn.gzu.libaraymana.domain.Book;
import cn.gzu.libaraymana.domain.User;
/**
 * 办理借书界面
 * @author GymYung
 * @since 2014-07-05 22:10
 */
public class JieBookActivity extends BaseActivity {
	/** 返回上一步 **/
	private ImageView previousImg;
	/** 菜单 **/
	private ImageView menuImg;
	/** 图书编号输入框 **/
	private EditText bookNumEt;
	/** 借书人学号输入框 **/
	private EditText userNumEt;
	/** 检测图书编号 **/
	private Button bookNumCheckBtn;
	/** 检测借书人学号 **/
	private Button userNumCheckBtn;
	/** 图书信息显示 **/
	private TextView bookNumInfoTx;
	/** 借书人信息显示 **/
	private TextView userNumInfoTx;
	/** 确认办理 **/
	private Button confirmBanliBtn;
	
	private BookDbImpl bookDbImpl;
	private UserDbImpl userDbImpl;
	
	private Book book;
	private User user;
	
	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_jiebook);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.jiebook_previous_img);
		menuImg = (ImageView) findViewById(R.id.jiebook_menu_img);
		bookNumEt = (EditText) findViewById(R.id.jiebook_booknumber_et);
		bookNumCheckBtn = (Button) findViewById(R.id.jiebook_booknumber_checkbtn);
		userNumEt = (EditText) findViewById(R.id.jiebook_usernumber_et);
		userNumCheckBtn = (Button) findViewById(R.id.jiebook_usernumber_checkbtn);
		bookNumInfoTx = (TextView) findViewById(R.id.jiebook_booinfo_tx);
		userNumInfoTx = (TextView) findViewById(R.id.jiebook_userinfo_tx);
		confirmBanliBtn = (Button) findViewById(R.id.jiebook_banli_btn);
		
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		bookNumCheckBtn.setOnClickListener(this);
		userNumCheckBtn.setOnClickListener(this);
		menuImg.setOnClickListener(this);
		confirmBanliBtn.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		bookDbImpl = new BookDbImpl(this);
		userDbImpl = new UserDbImpl(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.jiebook_previous_img:
			//返回上一步
			finish();
			
			break;
		case R.id.jiebook_menu_img:
			//返回主菜单
			DispacUtil.goToMainActivity(JieBookActivity.this);
			
			break;
		case R.id.jiebook_booknumber_checkbtn:
			//检测图书编号
			checkBook();
			
			break;
		case R.id.jiebook_usernumber_checkbtn:
			//检测借书人编号
			checkUser();
			
			break;
		case R.id.jiebook_banli_btn:
			//确认办理借书
			if(checkBook() && checkUser()){
				if(user.getPay()==0 && user.getBr_count()<=10 && book.getExiststate()>0){
					confirmBanliBtn.setText("正在办理中...");
					book.setDate(System.currentTimeMillis());
					book.setExiststate(book.getExiststate()-1);
					book.setUserid(user.getUserid());
					bookDbImpl.update(book.getBookid(), book);
					
					user.setBr_count(user.getBr_count()+1);
					user.setBookids(book.getBookid()+"-"+user.getBookids());
					userDbImpl.update(user.getUserid(), user);
					
					confirmBanliBtn.setText(R.string.banli_jieshu);
					Toast.makeText(getBaseContext(), "办理借书成功！", Toast.LENGTH_SHORT).show();
				}else if(user.getPay()>0){
					alertNoticeDialog("【"+user.getUsername()+"】未缴清罚款，请提醒他缴清罚款后再办理此业务！");
					
				}else if(user.getBr_count()>10){
					alertNoticeDialog("【"+user.getUsername()+"】借书数量已超过10本，请提醒他归还部分图书后再办理此业务！");
					
				}else if(book.getExiststate()==0){
					Toast.makeText(getBaseContext(), "【"+book.getBookname()+"】已经被借出!", Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(getBaseContext(), "信息检测有误！请检查后再办理！", Toast.LENGTH_SHORT).show();
			}
			
			
			break;
		}
	}
	
	
	/** 提示信息对话框 **/
	private void alertNoticeDialog(String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("提示信息");
		builder.setMessage(message);
		builder.setNeutralButton("知道了", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}
		});
		
		builder.create().show();
	}
	
	
	
	
	/** 检测图书信息 **/
	private boolean checkBook(){
		bookNumInfoTx.setText("正在检测中...");
		if(bookNumEt.getText().toString()!=null && !"".equals(bookNumEt.getText().toString())){
			book = bookDbImpl.queryBookByCode(bookNumEt.getText().toString().trim());
			if(book!=null){
				String bookInfo = "";
				if(book.getExiststate()>0){
					bookInfo += "【"+book.getAuthor()+"】 "+book.getBookname()+"\n流通状态：在架";
				}else{
					bookInfo += "【"+book.getAuthor()+"】 "+book.getBookname()+"\n流通状态：已借出";
				}
				bookNumInfoTx.setText(bookInfo);
				
				return true;
			}else{
				bookNumInfoTx.setText("未检测到该图书...");
				return false;
			}
			
		}else{
			bookNumInfoTx.setText("检测图书编号不能为空!");
			Toast.makeText(getBaseContext(), "检测图书编号不能为空!", Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	
	/** 检测用户信息 **/
	private boolean checkUser(){
		userNumInfoTx.setText("正在检测中...");
		if(userNumEt.getText().toString()!=null && !"".equals(userNumEt.getText().toString())){
			user = userDbImpl.queryBookByUserCode(Integer.parseInt(userNumEt.getText().toString().trim()));
			if(user!=null){
				if(user.getPay()>0){
					userNumInfoTx.setText("姓名："+user.getUsername()+"\n性别："+user.getGender()+"\n已借图书："+user.getBr_count()+"\n欠款信息："+user.getPay()+"元");
				}else{
					userNumInfoTx.setText("姓名："+user.getUsername()+"\n性别："+user.getGender()+"\n已借图书："+user.getBr_count()+"\n欠款信息：未欠款");
				}
				return true;
			}else{
				userNumInfoTx.setText("未检测到该用户...");
				return false;
			}
			
		}else{
			userNumInfoTx.setText("检测学号不能为空!");
			Toast.makeText(getBaseContext(), "检测学号不能为空!", Toast.LENGTH_SHORT).show();
			return false;
		}
	}
}
