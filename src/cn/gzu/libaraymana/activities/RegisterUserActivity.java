package cn.gzu.libaraymana.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.UserDbImpl;
import cn.gzu.libaraymana.domain.User;

public class RegisterUserActivity extends BaseActivity {
	/** 返回上一步 **/
	private ImageView previousImg;
	/** 菜单 **/
	private ImageView menuImg;
	
	private EditText nameEt;
	private EditText codeEt;
	private EditText pwdEt;
	private EditText repwdEt;
	
	private RadioButton manRb;
	private RadioButton womanRb;
	
	private Button addBtn;
	private Button getPwdBtn;
	
	private UserDbImpl userDbImpl;
	
	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_adduser);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.adduser_previous_img);
		menuImg = (ImageView) findViewById(R.id.adduser_menu_img);
		nameEt = (EditText) findViewById(R.id.adduser_name_et);
		codeEt = (EditText) findViewById(R.id.adduser_usercode_et);
		manRb = (RadioButton) findViewById(R.id.adduser_man_rb);
		womanRb = (RadioButton) findViewById(R.id.adduser_woman_rb);
		pwdEt = (EditText) findViewById(R.id.adduser_pwd_et);
		repwdEt = (EditText) findViewById(R.id.adduser_repwd_et);
		addBtn = (Button) findViewById(R.id.adduser_add_btn);
		getPwdBtn = (Button) findViewById(R.id.adduser_return_btn);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
		addBtn.setOnClickListener(this);
		getPwdBtn.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		userDbImpl = new UserDbImpl(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.adduser_previous_img:
			
			finish();
			
			break;
		case R.id.adduser_menu_img:
			
			goToMainActivity();
			
			break;
		case R.id.adduser_add_btn:
			
			RegisterUser();
			
			break;
		case R.id.adduser_return_btn:
			
			getPassword();
			
			break;
		}
	}
	
	
	private void getPassword() {
		
		String username = nameEt.getText().toString().trim();
		User user = userDbImpl.queryBookByUserName(username);
		if(user != null){
			Toast.makeText(getBaseContext(), "【"+username+"】的密码是："+user.getPassword()+"！请牢记！", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getBaseContext(), "【"+username+"】用户不存在！", Toast.LENGTH_SHORT).show();
		}
	}

	private void RegisterUser() {
		String username = nameEt.getText().toString().trim();
		String userCode = codeEt.getText().toString().trim();
		String password = pwdEt.getText().toString().trim();
		String rePassword = repwdEt.getText().toString().trim();
		String gender = "男";
		
		if(username==null && "".equals(username)){
			Toast.makeText(getBaseContext(), "【姓名】不能为空！", Toast.LENGTH_SHORT).show();
			nameEt.setFocusable(true);
			return;
		}
		
		if(userCode==null && "".equals(userCode)){
			Toast.makeText(getBaseContext(), "【学号】不能为空！", Toast.LENGTH_SHORT).show();
			codeEt.setFocusable(true);
			return;
		}
		
		if(password==null && "".equals(password)){
			Toast.makeText(getBaseContext(), "【密码】不能为空！", Toast.LENGTH_SHORT).show();
			pwdEt.setFocusable(true);
			return;
		}
		
		if(!password.equals(rePassword)){
			Toast.makeText(getBaseContext(), "两次输入密码不一致！", Toast.LENGTH_SHORT).show();
			repwdEt.setFocusable(true);
			return;
		}
		
		if(manRb.isChecked()){
			gender = "男";
		}else{
			gender = "女";
		}
		
		User user = new User(username,password,gender,Integer.parseInt(userCode));
		userDbImpl.save(user);
		Toast.makeText(getBaseContext(), "注册成功！", Toast.LENGTH_SHORT).show();
		this.finish();
	}

	/** 回到主界面 **/
	private void goToMainActivity(){
		Intent intent = new Intent(RegisterUserActivity.this,MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
		RegisterUserActivity.this.finish();
	}
}
