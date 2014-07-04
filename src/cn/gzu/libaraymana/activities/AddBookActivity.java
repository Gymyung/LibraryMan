package cn.gzu.libaraymana.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.BookDbImpl;
import cn.gzu.libaraymana.domain.Book;
/**
 * 添加图书界面
 * @author GymYung
 * @since 2014-07-04 14:54
 */
public class AddBookActivity extends BaseActivity {
	/** 返回上一步 **/
	private ImageView previousImg;
	/** 界面标题**/
	private TextView titleTx;
	/** 菜单 **/
	private ImageView menuImg;
	/** 图书名称 **/
	private EditText nameEt;
	/** 专业领域 **/
	private EditText majorEt;
	/** 图书编号 **/
	private EditText bookNumEt;
	/** 单价 **/
	private EditText priceEt;
	/** 作者 **/
	private EditText authorEt;
	/** 出版社 **/
	private EditText pressEt;
	
	/** 存在状态在架 **/
	private RadioButton existStateRb;
	/** 存在状态借出**/
	private RadioButton notExistStateRb;
	/** 添加 **/
	private Button addBtn;
	/** 返回 **/
	private Button returnBtn;
	
	private BookDbImpl bookDbImpl;

	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_addbook);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.addbook_previous_img);
		menuImg = (ImageView) findViewById(R.id.addbook_menu_img);
		titleTx = (TextView) findViewById(R.id.addbook_title_tx);
		nameEt = (EditText) findViewById(R.id.addbook_name_et);
		majorEt = (EditText) findViewById(R.id.addbook_major_et);
		bookNumEt = (EditText) findViewById(R.id.addbook_code_et);
		priceEt = (EditText) findViewById(R.id.addbook_price_et);
		authorEt = (EditText) findViewById(R.id.addbook_author_et);
		pressEt = (EditText) findViewById(R.id.addbook_press_et);
		existStateRb = (RadioButton) findViewById(R.id.addbook_exist_rb);
		notExistStateRb = (RadioButton) findViewById(R.id.addbook_not_exist_rb);
		addBtn = (Button) findViewById(R.id.addbook_add_btn);
		returnBtn = (Button) findViewById(R.id.addbook_return_btn);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
		addBtn.setOnClickListener(this);
		returnBtn.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		bookDbImpl = new BookDbImpl(this);
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
			if(bundle.getString("name")!=null){
				nameEt.setText(bundle.getString("name"));
				titleTx.setText("修改图书信息");
			}
			if(bundle.getString("major")!=null){
				majorEt.setText(bundle.getString("major"));
			}
			if(bundle.getString("number")!=null){
				bookNumEt.setText(bundle.getString("number"));
			}
			if(bundle.getFloat("price")>0){
				priceEt.setText(bundle.getFloat("price")+"");
			}
			if(bundle.getString("author")!=null){
				authorEt.setText(bundle.getString("author"));
			}
			if(bundle.getString("press")!=null){
				pressEt.setText(bundle.getString("press"));
			}
			if(bundle.getInt("exist")>0){
				existStateRb.setChecked(true);
			}
		}

	}
	
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.addbook_previous_img:
			
			finish();
			
			break;
		case R.id.addbook_menu_img:
			
			goToMainActivity();
			
			break;
		case R.id.addbook_add_btn:
			
			saveBookToDb();
			
			break;
		case R.id.addbook_return_btn:
			
			finish();
			
			break;
		}

	}
	
	/** 保存数据 **/
	private void saveBookToDb(){
		String name = nameEt.getText().toString();
		String major = majorEt.getText().toString();
		String bookNum = bookNumEt.getText().toString();
		float price = Float.valueOf(priceEt.getText().toString());
		String author = authorEt.getText().toString();
		String press = pressEt.getText().toString();
		int existstate = 1;
		
		if(name==null && "".equals(name)){
			Toast.makeText(getBaseContext(), "【图书名称】不能为空！", Toast.LENGTH_SHORT).show();
			nameEt.setFocusable(true);
			return;
		}
		
		if(major==null && "".equals(major)){
			Toast.makeText(getBaseContext(), "【专业领域】不能为空！", Toast.LENGTH_SHORT).show();
			majorEt.setFocusable(true);
			return;
		}
		
		if(bookNum==null && "".equals(bookNum)){
			Toast.makeText(getBaseContext(), "【图书编号】不能为空！", Toast.LENGTH_SHORT).show();
			bookNumEt.setFocusable(true);
			return;
		}
		
		if(price<=0){
			Toast.makeText(getBaseContext(), "【单价】不能为空！", Toast.LENGTH_SHORT).show();
			priceEt.setFocusable(true);
			return;
		}
		
		if(author==null && "".equals(author)){
			Toast.makeText(getBaseContext(), "【作者】不能为空！", Toast.LENGTH_SHORT).show();
			authorEt.setFocusable(true);
			return;
		}
		
		if(press==null && "".equals(press)){
			Toast.makeText(getBaseContext(), "【出版社】不能为空！", Toast.LENGTH_SHORT).show();
			pressEt.setFocusable(true);
			return;
		}
		
		if(notExistStateRb.isChecked()){
			existstate = 0;
		}
		
		Book book = new Book(name, major, bookNum, price, author, press, existstate);
		bookDbImpl.save(book);
		Toast.makeText(getBaseContext(), "添加成功！", Toast.LENGTH_SHORT).show();
		
	}
	
	/** 回到主界面 **/
	private void goToMainActivity(){
		Intent intent = new Intent(AddBookActivity.this,MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
		AddBookActivity.this.finish();
	}

}
