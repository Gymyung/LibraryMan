package cn.gzu.libaraymana.activities;

import cn.gzu.libaraymana.R;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class VersionInfoActivity extends BaseActivity {
	/** 返回上一步 **/
	private ImageView previousImg;
	/** 菜单 **/
	private ImageView menuImg;

	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_version_info);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.version_previous_img);
		menuImg = (ImageView) findViewById(R.id.version_menu_img);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
	}
		

	@Override
	protected void loadingDeal() {
		
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.version_previous_img:
			
			finish();
			
			break;
		case R.id.version_menu_img:
			
			goToMainActivity();
			
			break;
		}

	}
	
	/** 回到主界面 **/
	private void goToMainActivity(){
		Intent intent = new Intent(VersionInfoActivity.this,MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
		VersionInfoActivity.this.finish();
	}

}
