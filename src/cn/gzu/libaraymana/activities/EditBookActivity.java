package cn.gzu.libaraymana.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.BookDbImpl;
import cn.gzu.libaraymana.DAO.UserDbImpl;
import cn.gzu.libaraymana.domain.Book;
import cn.gzu.libaraymana.domain.ResultSet;
import cn.gzu.libaraymana.domain.User;
/**
 * 修改图书界面
 * @author GymYung
 * @since 2014-07-04 14:54
 */
public class EditBookActivity extends BaseActivity {
	/** 返回上一步 **/
	private ImageView previousImg;
	/** 菜单 **/
	private ImageView menuImg;
	/** 数据列表 **/
	private ListView bookListLv;
	/** 界面标题 **/
	private TextView titleTx;
	
	List<ResultSet> showInfos;
	
	private BookDbImpl bookDbImpl;
	private UserDbImpl userDbImpl;

	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_editbook);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.editbook_previous_img);
		menuImg = (ImageView) findViewById(R.id.editbook_menu_img);
		titleTx = (TextView) findViewById(R.id.editbook_title_tx);
		bookListLv = (ListView) findViewById(R.id.editbook_listview);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		showInfos = new ArrayList<ResultSet>();
		bookDbImpl = new BookDbImpl(this);
		userDbImpl = new UserDbImpl(this);
		int searchWaysIndex = getIntent().getIntExtra("searchWaysIndex", 0);
		
		if(searchWaysIndex == 0){
			dataFromEdit();
		}else{
			titleTx.setText(R.string.query_result_list);
			String searchInfo = getIntent().getStringExtra("bookInputInfo");
			dataFromQuery(searchWaysIndex,searchInfo);
		}
		
		
	}
	
	
	/**
	 * 数据来自信息修改 即点击条目跳转至信息修改
	 */
	private void dataFromEdit(){
		titleTx.setText(R.string.book_list);
		final List<Book> books = bookDbImpl.findAll();
		
		if(showInfos != null){
			showInfos.clear();
		}
		
		for(Book book : books){
			ResultSet resultSet = new ResultSet();
			resultSet.setAuthor(book.getAuthor());
			resultSet.setInformation(book.getBookname());
			showInfos.add(resultSet);
		}
		
		MyBookListAdapter adapter = new MyBookListAdapter();
		bookListLv.setAdapter(adapter);
		bookListLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Book book = books.get(position);
				Intent intent = new Intent(EditBookActivity.this,AddBookActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("name", book.getBookname());
				bundle.putString("major", book.getMajor());
				bundle.putString("number", book.getCode());
				bundle.putFloat("price", book.getPrice());
				bundle.putString("author", book.getAuthor());
				bundle.putString("press", book.getPress());
				bundle.putInt("exist", book.getExiststate());
				intent.putExtras(bundle);
				startActivity(intent);
				overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
				
			}
		});
		
		bookListLv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				final Book book = books.get(position);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(EditBookActivity.this);
				builder.setTitle("提示信息");
				builder.setMessage("您真的要删除该图书吗？");
				builder.setPositiveButton("是的", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						bookDbImpl.delete(book.getBookname());
						dialog.dismiss();
						loadingDeal();
					}
				});
				builder.setNegativeButton("取消", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				
				builder.create().show();
				return false;
			}
		});
	}
	
	
	/**
	 * 数据来自信息查询 即点击条目跳转至信息显示
	 */
	private void dataFromQuery(final int searchWaysIndex,String searchInfo){
		List<Book> books;
		List<User> users;
		
		if(showInfos != null){
			showInfos.clear();
		}
		
		switch (searchWaysIndex) {
		/** 图书查询方式 1按作者名查询 2按专业查询 3按图书名查询 4查询用户**/
		case 1:
			books = bookDbImpl.findAll();
			for(Book book : books){
				if(book.getAuthor().contains(searchInfo)){
					ResultSet resultSet = new ResultSet();
					resultSet.setAuthor(book.getAuthor());
					resultSet.setInformation(book.getBookname());
					showInfos.add(resultSet);
				}
			}
			
			break;
		case 2:
			books = bookDbImpl.findAll();
			for(Book book : books){
				if(book.getMajor().contains(searchInfo)){
					ResultSet resultSet = new ResultSet();
					resultSet.setAuthor(book.getAuthor());
					resultSet.setInformation(book.getBookname());
					showInfos.add(resultSet);
				}
			}
			
			break;
		case 3:
			books = bookDbImpl.findAll();
			for(Book book : books){
				if(book.getBookname().contains(searchInfo)){
					ResultSet resultSet = new ResultSet();
					resultSet.setAuthor(book.getAuthor());
					resultSet.setInformation(book.getBookname());
					showInfos.add(resultSet);
				}
			}
			
			break;
		case 4:
			users = userDbImpl.findAll();
			for(User user : users){
				if(user.getUsercode() == Integer.parseInt(searchInfo)){
					ResultSet resultSet = new ResultSet();
					resultSet.setAuthor(user.getUsercode()+"");
					resultSet.setInformation(user.getUsername());
					showInfos.add(resultSet);
				}
			}
			
			break;
		}
		
		
		MyBookListAdapter adapter = new MyBookListAdapter();
		bookListLv.setAdapter(adapter);
		bookListLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ResultSet resultSet = showInfos.get(position);
				
				Toast.makeText(getBaseContext(), resultSet.getInformation()+" "+searchWaysIndex, Toast.LENGTH_SHORT).show();
				
				
				Intent intent = new Intent(EditBookActivity.this,QueryResultActivity.class);
				intent.putExtra("information", resultSet.getInformation());
				intent.putExtra("searchWaysIndex", searchWaysIndex);
				startActivity(intent);
				overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
				
			}
		});
		
		
		
		
	}
	
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.editbook_previous_img:
			
			finish();
			
			break;
		case R.id.editbook_menu_img:
			
			goToMainActivity();
			
			break;
		}

	}
	
	/** 回到主界面 **/
	private void goToMainActivity(){
		Intent intent = new Intent(EditBookActivity.this,MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
		EditBookActivity.this.finish();
	}
	
	
	private class MyBookListAdapter extends BaseAdapter{
		
		@Override
		public int getCount() {
			return showInfos.size();
		}

		@Override
		public Object getItem(int position) {
			return showInfos.get(position);
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
			
			ResultSet resultSet = showInfos.get(position);
			
			viewHolder.authorTx.setText("【"+resultSet.getAuthor()+"】");
			viewHolder.nameTx.setText(resultSet.getInformation());
			
			return view;
		}}
	
	static class ViewHolder{
		TextView authorTx;
		TextView nameTx;
	}

}
