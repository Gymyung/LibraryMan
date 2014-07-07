package cn.gzu.libaraymana.activities;

import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.BookDbImpl;
import cn.gzu.libaraymana.DAO.UserDbImpl;
import cn.gzu.libaraymana.Util.CommonUtil;
import cn.gzu.libaraymana.Util.Const;
import cn.gzu.libaraymana.Util.DispacUtil;
import cn.gzu.libaraymana.domain.Book;
import cn.gzu.libaraymana.domain.User;
/**
 * 办理还书界面
 * @author GymYung
 * @since 2014-07-06 22:10
 */
public class HuanBookActivity extends BaseActivity {
	/** 返回上一步 **/
	private ImageView previousImg;
	/** 菜单 **/
	private ImageView menuImg;
	/** 输入图书编号 **/
	private EditText bookNumEt;
	/** 检测图书编号 **/
	private Button bookNumCheckBtn;
	/** 图书信息显示 **/
	private TextView bookInfoTx;
	/** 缴纳罚款 **/
	private Button jiaoKuanBtn;
	/** 确认办理业务 **/
	private Button confirmBtn;

	private BookDbImpl bookDbImpl;
	private UserDbImpl userDbImpl;
	
	private Book book;
	private User user;
	
	private SharedPreferences sp;
	
	/** 罚款信息 **/
	private float allPrice;
	
	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_huanbook);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.huanbook_previous_img);
		menuImg = (ImageView) findViewById(R.id.huanbook_menu_img);
		bookNumEt = (EditText) findViewById(R.id.huanbook_booknumber_et);
		bookNumCheckBtn = (Button) findViewById(R.id.huanbook_booknumber_checkbtn);
		bookInfoTx = (TextView) findViewById(R.id.huanbook_booinfo_tx);
		jiaoKuanBtn = (Button) findViewById(R.id.huanbook_jiaona_fakuan_btn);
		confirmBtn = (Button) findViewById(R.id.huanbook_banli_btn);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
		bookNumCheckBtn.setOnClickListener(this);
		jiaoKuanBtn.setOnClickListener(this);
		confirmBtn.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		bookDbImpl = new BookDbImpl(this);
		userDbImpl = new UserDbImpl(this);
		sp = getSharedPreferences(Const.SharedPerName, Context.MODE_PRIVATE);
		
		jiaoKuanBtn.setVisibility(View.GONE);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.huanbook_previous_img:
			//返回上一步
			finish();
			
			break;
		case R.id.huanbook_menu_img:
			//返回主菜单
			DispacUtil.goToMainActivity(HuanBookActivity.this);
			
			break;
		case R.id.huanbook_booknumber_checkbtn:
			/** 检测图书编号 **/
			checkBook();
			
			break;
		case R.id.huanbook_jiaona_fakuan_btn:
			/** 缴纳罚款 **/
			clearPay();
					
			break;
		case R.id.huanbook_banli_btn:
			/** 确认办理业务 **/
			confirmDoWork();
			
			break;
		}
	}
	
	/** 缴纳罚款 **/
	private void clearPay(){
		allPrice = 0;
		Toast.makeText(getBaseContext(), "已成功缴纳罚款!", Toast.LENGTH_SHORT).show();
	}
	
	/** 检测图书信息 **/
	private boolean checkBook(){
		bookInfoTx.setText("正在检测中...");
		if(bookNumEt.getText().toString()!=null && !"".equals(bookNumEt.getText().toString())){
			book = bookDbImpl.queryBookByCode(bookNumEt.getText().toString().trim());
			if(book!=null){
				String bookInfo = "";
				bookInfo = bookInfo+"图书编号："+book.getCode()+"\n"+"图书名称： 《"+book.getBookname()+"》\n";
				
				user = userDbImpl.queryBookByUserId(book.getUserid());
				if(user !=null){
					bookInfo = bookInfo+"借书人学号："+user.getUsercode()+"\n"+"借书人姓名："+user.getUsername()+"\n"
							+"借出时间："+CommonUtil.formatTime(new Date(book.getDate()))+"\n";
					
					int allowDays = sp.getInt(Const.FAKUANDAY, Const.faKuanDays_default);
					float fakuanPrice = sp.getFloat(Const.PRICEADAY, Const.priceDay_default);
					int realDays = (int) CommonUtil.compareTimeToDay(System.currentTimeMillis(), book.getDate());
					
					if(realDays > allowDays){
						allPrice = CommonUtil.getPayment(realDays-allowDays, fakuanPrice);
						bookInfo = bookInfo+"超期罚款信息：【超期"+(realDays-allowDays)+"天】;罚款 "+allPrice+"元\n";
						jiaoKuanBtn.setVisibility(View.VISIBLE);
					}else{
						bookInfo = bookInfo+"超期罚款信息：未超期\n";
					}
					
					bookInfoTx.setText(bookInfo);
				}else{
					bookInfo = bookInfo+"该图书未被借出!\n";
					bookInfoTx.setText(bookInfo);
					Toast.makeText(getBaseContext(), "该图书未被借出!", Toast.LENGTH_SHORT).show();
				}
				
				
				return true;
			}else{
				bookInfoTx.setText("未检测到该图书...");
				return false;
			}
			
		}else{
			bookInfoTx.setText("检测图书编号不能为空!");
			Toast.makeText(getBaseContext(), "检测图书编号不能为空!", Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	
	
	/** 确认办理业务 **/
	private void confirmDoWork(){
		confirmBtn.setText("正在办理中...");
		if(user!=null && book!=null){
			if(allPrice>0){
				noticePriceDialog(user);
			}else{
				user.setPay(0);
			}
			user.setBr_count(user.getBr_count()-1);
			
			String[] bookids = user.getBookids().split("-");
			String bookid = book.getBookid()+"";
			StringBuffer sb = new StringBuffer();
			for(String book : bookids){
				if(!book.equals(bookid)){
					sb.append(book).append('-');
				}
			}
			sb.deleteCharAt(sb.length()-1);
			user.setBookids(sb.toString());
			userDbImpl.update(user.getUserid(), user);
			
			book.setUserid(-1);
			book.setExiststate(1);
			bookDbImpl.update(book.getBookid(), book);
			
			Toast.makeText(getBaseContext(), "成功办理还书！", Toast.LENGTH_SHORT).show();
			
		}else{
			bookInfoTx.setText("检测图书编号不能为空!");
			Toast.makeText(getBaseContext(), "检测图书编号不能为空!", Toast.LENGTH_SHORT).show();
		}
		flushView();
	}
	
	
	/** 提示罚款对话框 **/
	private void noticePriceDialog(User user){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("提示信息");
		builder.setMessage("【"+user.getUsername()+"】借阅图书超期罚款"+allPrice+"元。请提醒他缴纳罚款！");
		builder.setPositiveButton("已缴纳", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				clearPay();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		builder.create().show();
		user.setPay(allPrice);
	}
	
	
	/** 刷新界面 **/
	private void flushView(){
		bookNumEt.setText("");
		bookInfoTx.setText("");
		jiaoKuanBtn.setVisibility(View.GONE);
		confirmBtn.setText(R.string.confirm_banli_huanshu);
	}
	
}
