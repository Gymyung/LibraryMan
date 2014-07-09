package cn.gzu.libaraymana.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.Util.DispacUtil;
/**
 * 信息检索界面
 * @author GymYung
 * @since 2014-07-07 11:12
 */
public class SearchInfoActivity extends BaseActivity {
	/** 返回上一步 **/
	private ImageView previousImg;
	/** 菜单 **/
	private ImageView menuImg;
	/** 显示查询方式 **/
	private TextView waysShowTx;
	/** 选择查询方式按钮 **/
	private Button waysSelectBtn;
	/** 查询图书输入信息 **/
	private EditText searchBookEt;
	/** 开始查询图书按钮 **/
	private Button searchBookBtn;
	/** 输入查询借书人信息 **/
	private EditText searchUserEt;
	/** 开始查询借书人 **/
	private Button searchUserBtn;
	
	/** 图书查询方式 1按作者名查询 2按专业查询 3按图书名查询 **/
	private int searchWaysIndex = 1;
	
	/** 按作者名查询 **/
	private RadioButton searchByAuthorRb;
	/** 按专业查询 **/
	private RadioButton searchByMajorRb;
	/** 按图书名查询 **/
	private RadioButton searchByNameRb;
	
	private AlertDialog dialog;
	

	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_queryserach);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.search_previous_img);
		menuImg = (ImageView) findViewById(R.id.search_menu_img);
		waysShowTx = (TextView) findViewById(R.id.search_ways_show_tx);
		waysSelectBtn = (Button) findViewById(R.id.search_ways_select_btn);
		searchBookEt = (EditText) findViewById(R.id.search_book_searchinfo_et);
		searchBookBtn = (Button) findViewById(R.id.search_book_start_btn);
		searchUserEt = (EditText) findViewById(R.id.search_usercode_input_et);
		searchUserBtn = (Button) findViewById(R.id.search_user_start_btn);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
		waysSelectBtn.setOnClickListener(this);
		searchBookBtn.setOnClickListener(this);
		searchUserBtn.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.search_previous_img:
			//返回上一步
			finish();
			
			break;
		case R.id.search_menu_img:
			//返回主菜单
			DispacUtil.goToMainActivity(SearchInfoActivity.this);
			
			break;
		case R.id.search_ways_select_btn:
			/** 选择查询方式按钮 **/
			selectWaysDialog();
			
			break;
		case R.id.search_book_start_btn:
			/** 开始查询图书按钮 **/
			searchBook();
			
			break;
		case R.id.search_user_start_btn:
			/** 开始查询借书人 **/
			searchUser();
			
			break;
			
		case R.id.dialog_select_way_author_ll:
			/** 按作者名查询 **/
			initRadioBtn(1);
			
			break;
		case R.id.dialog_select_way_major_ll:
			/** 按专业查询 **/
			initRadioBtn(2);
			
			break;
		case R.id.dialog_select_way_name_ll:
			/** 按图书名查询 **/
			initRadioBtn(3);
			
			break;
		}
	}
	
	/** 开始查询图书 **/
	private void searchBook(){
		String bookInputInfo = searchBookEt.getText().toString();
		if(bookInputInfo != null && !"".equals(bookInputInfo)){
			Intent intent = new Intent(SearchInfoActivity.this,EditBookActivity.class);
			intent.putExtra("searchWaysIndex", searchWaysIndex);
			intent.putExtra("bookInputInfo", bookInputInfo.trim());
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			
		}else{
			Toast.makeText(getBaseContext(), "请输入查询图书信息!", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	/** 开始查询借书人 **/
	private void searchUser(){
		String userInputInfo = searchUserEt.getText().toString();
		if(userInputInfo != null && !"".equals(userInputInfo)){
			Intent intent = new Intent(SearchInfoActivity.this,EditBookActivity.class);
			intent.putExtra("searchWaysIndex", 4);
			intent.putExtra("bookInputInfo", userInputInfo.trim());
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			
		}else{
			Toast.makeText(getBaseContext(), "请输入查询借书人信息!", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	/** 初始化单选按钮 **/
	private void initRadioBtn(int index){
		searchByAuthorRb.setChecked(false);
		searchByMajorRb.setChecked(false);
		searchByNameRb.setChecked(false);
		
		searchWaysIndex = index;
		switch(index){
		case 1:
			searchByAuthorRb.setChecked(true);
			waysShowTx.setText(R.string.search_by_author);
			break;
		case 2:
			searchByMajorRb.setChecked(true);
			waysShowTx.setText(R.string.search_by_major);
			break;
		case 3:
			searchByNameRb.setChecked(true);
			waysShowTx.setText(R.string.search_by_bookname);
			break;
		}
		
		if(dialog!=null) dialog.dismiss();
		
	}
	
	/** 选择查询方式对话框 **/
	private void selectWaysDialog(){
		View view = getLayoutInflater().inflate(R.layout.dialog_select_search_way, null);
		LinearLayout searchByAuthorLL = (LinearLayout) view.findViewById(R.id.dialog_select_way_author_ll);
		LinearLayout searchByMajorLL = (LinearLayout) view.findViewById(R.id.dialog_select_way_major_ll);
		LinearLayout searchByNameLL = (LinearLayout) view.findViewById(R.id.dialog_select_way_name_ll);
		searchByAuthorRb = (RadioButton) view.findViewById(R.id.dialog_select_way_author_rb);
		searchByMajorRb = (RadioButton) view.findViewById(R.id.dialog_select_way_major_rb);
		searchByNameRb = (RadioButton) view.findViewById(R.id.dialog_select_way_name_rb);
		
		initRadioBtn(searchWaysIndex);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("提示信息");
		builder.setView(view);
		
		builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog = builder.create();
		dialog.show();
		
		
		searchByAuthorLL.setOnClickListener(this);
		searchByMajorLL.setOnClickListener(this);
		searchByNameLL.setOnClickListener(this);
		
	}
	
	

}
