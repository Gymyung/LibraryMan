package cn.gzu.libaraymana.activities;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.Util.Const;
/**
 * 主界面
 * @author GymYung
 * @since 2014-07-03 19:54
 */
public class MainActivity extends BaseActivity {
	private TextView welcomeTx;
	private LinearLayout addbookLl;
	private LinearLayout editbookLl;
	private LinearLayout querybookLl;
	private LinearLayout jiebookLl;
	private LinearLayout huanbookLl;
	private LinearLayout usermanaLl;
	private LinearLayout settingLl;
	private LinearLayout versionLl;

	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void findViewById() {
		welcomeTx = (TextView) findViewById(R.id.main_welcome);
		addbookLl = (LinearLayout) findViewById(R.id.main_menuitem_addbook_ll);
		editbookLl = (LinearLayout) findViewById(R.id.main_menuitem_editbook_ll);
		querybookLl = (LinearLayout) findViewById(R.id.main_menuitem_querybook_ll);
		jiebookLl = (LinearLayout) findViewById(R.id.main_menuitem_jiebook_ll);
		huanbookLl = (LinearLayout) findViewById(R.id.main_menuitem_huanbook_ll);
		usermanaLl = (LinearLayout) findViewById(R.id.main_menuitem_usermana_ll);
		settingLl = (LinearLayout) findViewById(R.id.main_menuitem_setting_ll);
		versionLl = (LinearLayout) findViewById(R.id.main_menuitem_version_ll);
	}

	@Override
	protected void setCompanentListener() {
		addbookLl.setOnClickListener(this);
		editbookLl.setOnClickListener(this);
		querybookLl.setOnClickListener(this);
		jiebookLl.setOnClickListener(this);
		huanbookLl.setOnClickListener(this);
		usermanaLl.setOnClickListener(this);
		settingLl.setOnClickListener(this);
		versionLl.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		welcomeTx.setText("欢迎您： "+Const.user.getUsername());
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.main_menuitem_addbook_ll:
			//添加图书
			Toast.makeText(getBaseContext(), "Hello Test Successful!", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.main_menuitem_editbook_ll:
			//修改图书
			Toast.makeText(getBaseContext(), "Hello Test Successful!", Toast.LENGTH_SHORT).show();
					
					break;
		case R.id.main_menuitem_querybook_ll:
			//查询检索
			Toast.makeText(getBaseContext(), "Hello Test Successful!", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.main_menuitem_jiebook_ll:
			//办理借书
			Toast.makeText(getBaseContext(), "Hello Test Successful!", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.main_menuitem_huanbook_ll:
			//办理还书
			Toast.makeText(getBaseContext(), "Hello Test Successful!", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.main_menuitem_usermana_ll:
			//借书人管理
			Toast.makeText(getBaseContext(), "Hello Test Successful!", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.main_menuitem_setting_ll:
			//系统设置
			Toast.makeText(getBaseContext(), "Hello Test Successful!", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.main_menuitem_version_ll:
			//版本信息
			Toast.makeText(getBaseContext(), "Hello Test Successful!", Toast.LENGTH_SHORT).show();
			
			break;
		}
	}

}
