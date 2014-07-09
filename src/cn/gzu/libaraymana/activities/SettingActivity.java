package cn.gzu.libaraymana.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.Util.Const;
/**
 * 系统设置界面
 * @author GymYung
 * @since 2014-07-09 22:35
 */
public class SettingActivity extends BaseActivity {
	private ImageView previousImg;
	private ImageView muneImg;
	private RelativeLayout sysadmShowRl;
	private RelativeLayout maxDaysRl;
	private RelativeLayout paymentPriceRl;
	private RelativeLayout remePwdRl;
	private RelativeLayout autoLogonRl;
	private RelativeLayout msgNoticeRl;
	private RelativeLayout functionRl;
	private RelativeLayout securityRl;
	private RelativeLayout versionRl;
	
	private SharedPreferences sp;
	
	private int flagRadio;
	

	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_setting);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.setting_previous_img);
		muneImg = (ImageView) findViewById(R.id.setting_menu_img);
		sysadmShowRl = (RelativeLayout) findViewById(R.id.setting_sysadm_show_rl);
		maxDaysRl = (RelativeLayout) findViewById(R.id.setting_max_days_rl);
		paymentPriceRl = (RelativeLayout) findViewById(R.id.setting_payment_price_rl);
		remePwdRl = (RelativeLayout) findViewById(R.id.setting_logon_remepwd_rl);
		autoLogonRl = (RelativeLayout) findViewById(R.id.setting_auto_logon_rl);
		msgNoticeRl = (RelativeLayout) findViewById(R.id.setting_message_notice_rl);
		functionRl = (RelativeLayout) findViewById(R.id.setting_function_rl);
		securityRl = (RelativeLayout) findViewById(R.id.setting_security_rl);
		versionRl = (RelativeLayout) findViewById(R.id.setting_version_rl);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		muneImg.setOnClickListener(this);
		sysadmShowRl.setOnClickListener(this);
		maxDaysRl.setOnClickListener(this);
		paymentPriceRl.setOnClickListener(this);
		remePwdRl.setOnClickListener(this);
		autoLogonRl.setOnClickListener(this);
		msgNoticeRl.setOnClickListener(this);
		functionRl.setOnClickListener(this);
		securityRl.setOnClickListener(this);
		versionRl.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		sp = getSharedPreferences(Const.SharedPerName, Context.MODE_PRIVATE);
	}
	
	@Override
	public void onClick(View v) {
		View view;
		TextView textView;
		EditText editText;
		RadioButton yesRadio;
		RadioButton noRadio;
		
		switch(v.getId()){
		case R.id.setting_previous_img:
			
			finish();
			
			break;
		case R.id.setting_menu_img:
			
			goToMainActivity();
			
			break;
		case R.id.setting_sysadm_show_rl:
			
			Toast.makeText(getBaseContext(), "setting_sysadm_show_rl", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.setting_max_days_rl:
			
			view = getLayoutInflater().inflate(R.layout.dialog_setting_input, null);
			textView = (TextView) view.findViewById(R.id.dialog_setting_tx);
			textView.setText("输入您要设置的最大借阅天数.");
			editText = (EditText) view.findViewById(R.id.dialog_setting_et);
			msgInputDialog(view, textView, editText.getText().toString(), 1);
			
			break;
		case R.id.setting_payment_price_rl:
			
			view = getLayoutInflater().inflate(R.layout.dialog_setting_input, null);
			textView = (TextView) view.findViewById(R.id.dialog_setting_tx);
			textView.setText("输入您要设置的超期罚款金额（元/天）.");
			editText = (EditText) view.findViewById(R.id.dialog_setting_et);
			msgInputDialog(view, textView, editText.getText().toString(), 2);
			
			break;
		case R.id.setting_logon_remepwd_rl:
			
			view = getLayoutInflater().inflate(R.layout.dialog_setting_radio, null);
			textView = (TextView) view.findViewById(R.id.dialog_setting_tx);
			textView.setText("是否登录时记住密码？");
			yesRadio = (RadioButton) view.findViewById(R.id.dialog_setting_yesRb);
			noRadio = (RadioButton) view.findViewById(R.id.dialog_setting_noRb);
			msgRadioDialog(view,1);
			
			break;
		case R.id.setting_auto_logon_rl:
			
			view = getLayoutInflater().inflate(R.layout.dialog_setting_radio, null);
			textView = (TextView) view.findViewById(R.id.dialog_setting_tx);
			textView.setText("是否启动时自动登录？");
			yesRadio = (RadioButton) view.findViewById(R.id.dialog_setting_yesRb);
			noRadio = (RadioButton) view.findViewById(R.id.dialog_setting_noRb);
			msgRadioDialog(view,1);
			
			break;
		case R.id.setting_message_notice_rl:
			
			view = getLayoutInflater().inflate(R.layout.dialog_setting_radio, null);
			textView = (TextView) view.findViewById(R.id.dialog_setting_tx);
			textView.setText("是否启动消息通知？");
			yesRadio = (RadioButton) view.findViewById(R.id.dialog_setting_yesRb);
			noRadio = (RadioButton) view.findViewById(R.id.dialog_setting_noRb);
			msgRadioDialog(view,1);
			
			break;
		case R.id.setting_function_rl:
			
			view = getLayoutInflater().inflate(R.layout.dialog_setting_radio, null);
			textView = (TextView) view.findViewById(R.id.dialog_setting_tx);
			textView.setText("是否使用辅助功能？");
			yesRadio = (RadioButton) view.findViewById(R.id.dialog_setting_yesRb);
			noRadio = (RadioButton) view.findViewById(R.id.dialog_setting_noRb);
			msgRadioDialog(view,1);
			
			break;
		case R.id.setting_security_rl:
			
			view = getLayoutInflater().inflate(R.layout.dialog_setting_radio, null);
			textView = (TextView) view.findViewById(R.id.dialog_setting_tx);
			textView.setText("是否使用安全与隐私？");
			yesRadio = (RadioButton) view.findViewById(R.id.dialog_setting_yesRb);
			noRadio = (RadioButton) view.findViewById(R.id.dialog_setting_noRb);
			msgRadioDialog(view,1);
			
			break;
		case R.id.setting_version_rl:
			
			Toast.makeText(getBaseContext(), "setting_version_rl", Toast.LENGTH_SHORT).show();
			
			break;
		}
	}
	
	/** 
	 * 信息选择对话框
	 * @param view 
	 * @param flag 1是 0否
	 */
	private void msgRadioDialog(View view,int flag){
		AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
		builder.setTitle("提示信息");
		builder.setView(view);
		
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Toast.makeText(getBaseContext(), "设置成功！", Toast.LENGTH_SHORT).show();
				
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}
		});
		
		builder.create().show();
	}
	
	/** 信息对话框 **/
	private void msgInputDialog(View view,TextView textView,final String editText,final int index){
		AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
		builder.setTitle("提示信息");
		builder.setView(view);
		
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(editText!=null&&!"".equals(editText)){
					Editor edit = sp.edit();
					if(index==1){
						edit.putInt(Const.FAKUANDAY, Integer.parseInt(editText));
					}else if(index==2){
						edit.putFloat(Const.PRICEADAY, Float.parseFloat(editText));
					}
					edit.commit();
					dialog.dismiss();
					Toast.makeText(getBaseContext(), "设置成功！", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getBaseContext(), "检测到您什么都没有输入...", Toast.LENGTH_SHORT).show();
				}
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}
		});
		
		builder.create().show();
	}
	
	
	/** 回到主界面 **/
	private void goToMainActivity(){
		Intent intent = new Intent(SettingActivity.this,MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
		SettingActivity.this.finish();
	}

}
