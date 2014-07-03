package cn.gzu.libaraymana.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.UserDbImpl;
import cn.gzu.libaraymana.Util.Const;
import cn.gzu.libaraymana.domain.User;

public class EnterActivity extends BaseActivity {
	/** 输入用户名 **/
	private EditText usernameEt;
	/** 输入密码 **/
	private EditText passwordEt;
	/** 忘记密码 **/
	private TextView forgetPasTx;
	/** 登录按钮 **/
	private Button loginBtn;
	/** 是否记住密码 **/
	private CheckBox rempwdCb;
	
	private UserDbImpl userDbImpl;
	
	private SharedPreferences sp;

	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.enter_activity);
	}

	@Override
	protected void findViewById() {
		usernameEt = (EditText) findViewById(R.id.enter_input_account_et);
		passwordEt = (EditText) findViewById(R.id.enter_input_password_et);
		forgetPasTx = (TextView) findViewById(R.id.enter_forget_password_tx);
		rempwdCb = (CheckBox) findViewById(R.id.enter_rembpwd_cb);
		loginBtn = (Button) findViewById(R.id.enter_login_btn);
	}

	@Override
	protected void setCompanentListener() {
		forgetPasTx.setOnClickListener(this);
		loginBtn.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		userDbImpl = new UserDbImpl(this);
		User user = userDbImpl.queryBookByUserName("系统管理员");
		if(user == null){
			user = new User();
			user.setUsername("系统管理员");
			user.setPassword("123");
			user.setBookids("6-5");
			user.setGender("男");
			user.setUsercode(1207010209);
			user.setPay(0f);
			userDbImpl.save(user);
		}
		
		sp = getSharedPreferences(Const.SharedPerName, Context.MODE_PRIVATE);
		String username = sp.getString(Const.USERNAMEET, null);
		String password = sp.getString(Const.PASSWORDET, null);
		if(username!=null && !"".equals(username)){
			usernameEt.setText(username);
		}
		if(password!=null && !"".equals(password)){
			passwordEt.setText(password);
		}
		
	}
	
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.enter_forget_password_tx:
			//忘记密码
			
			
			break;
		case R.id.enter_login_btn:
			//登录
			
			String username = usernameEt.getText().toString();
			String password = passwordEt.getText().toString();
			
			if(username==null || "".equals(username)){
				Toast.makeText(getBaseContext(), R.string.username_not_null, Toast.LENGTH_SHORT).show();
				return;
			}
			if(password==null || "".equals(password)){
				Toast.makeText(getBaseContext(), R.string.password_not_null, Toast.LENGTH_SHORT).show();
				return;
			}
			
			User user = userDbImpl.queryBookByUserName(username.trim());
			if(user!=null){
				if(password.equals(user.getPassword())){
					Toast.makeText(getBaseContext(), "["+username+"]"+R.string.login_success, Toast.LENGTH_SHORT).show();
					
					if(rempwdCb.isChecked()){
						Editor edit = sp.edit();
						edit.putString(Const.USERNAMEET, username);
						edit.putString(Const.PASSWORDET, password);
						edit.commit();
					}else{
						Editor edit = sp.edit();
						edit.remove(Const.USERNAMEET);
						edit.remove(Const.PASSWORDET);
						edit.commit();
					}
					
					Intent intent = new Intent(EnterActivity.this,MainActivity.class);
					Const.user = user;
					startActivity(intent);
					overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
					EnterActivity.this.finish();
					
				}else{
					Toast.makeText(getBaseContext(), R.string.username_or_password_error, Toast.LENGTH_SHORT).show();
					return;
				}
			}else{
				Toast.makeText(getBaseContext(), R.string.user_not_exist, Toast.LENGTH_SHORT).show();
				return;
			}
			
			break;
		}
	}
	
}


