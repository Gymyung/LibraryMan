package cn.gzu.libaraymana.activities;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.UserDbImpl;
import cn.gzu.libaraymana.domain.User;

public class UserListActivity extends BaseActivity {
	/** 返回上一步 **/
	private ImageView previousImg;
	/** 菜单 **/
	private ImageView menuImg;
	/** 数据列表 **/
	private ListView userListLv;
	
	private Button banliBtn;
	
	private UserDbImpl userDbImpl;
	private List<User> users;
	
	private MyUserListAdapter myUserListAdapter;
	
	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_userlist);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.userlist_previous_img);
		menuImg = (ImageView) findViewById(R.id.userlist_menu_img);
		userListLv = (ListView) findViewById(R.id.userlist_listview);
		banliBtn = (Button) findViewById(R.id.userlist_banli_btn);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
		banliBtn.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		userDbImpl = new UserDbImpl(this);
		users = userDbImpl.findAll();
		
		
		myUserListAdapter = new MyUserListAdapter();
		userListLv.setAdapter(myUserListAdapter);
		
		userListLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				User user = users.get(position);
				
				Intent intent = new Intent(UserListActivity.this,QueryResultActivity.class);
				intent.putExtra("information", user.getUsername());
				intent.putExtra("searchWaysIndex", 4);
				startActivity(intent);
				overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			}
		});
		
		
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.userlist_previous_img:
			
			finish();
			
			break;
		case R.id.userlist_menu_img:
			
			goToMainActivity();
			
			break;
		case R.id.userlist_banli_btn:
			
			Intent intent = new Intent(UserListActivity.this,RegisterUserActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			
			break;
		}

	}
	
	/** 回到主界面 **/
	private void goToMainActivity(){
		Intent intent = new Intent(UserListActivity.this,MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
		UserListActivity.this.finish();
	}
	
	
	
private class MyUserListAdapter extends BaseAdapter{
		
		@Override
		public int getCount() {
			return users.size();
		}

		@Override
		public Object getItem(int position) {
			return users.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			ViewHolder viewHolder = null;
			if(convertView!=null){
				view = convertView;
				viewHolder = (ViewHolder) view.getTag();
			}else{
				view = getLayoutInflater().inflate(R.layout.editbok_booklist_item, null);
				viewHolder = new ViewHolder();
				viewHolder.authorTx = (TextView) view.findViewById(R.id.booklist_author_tx);
				viewHolder.nameTx = (TextView) view.findViewById(R.id.booklist_name_tx);
				view.setTag(viewHolder);
			}
			
			User user = users.get(position);
			
			viewHolder.authorTx.setText("【"+user.getUsercode()+"】");
			viewHolder.nameTx.setText(user.getUsername()+"     ("+user.getGender()+")");
			
			return view;
		}}
	
	static class ViewHolder{
		TextView authorTx;
		TextView nameTx;
	}
	
	
	@Override
	protected void onResume() {
		loadingDeal();
		super.onResume();
	}

}
