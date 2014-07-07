package cn.gzu.libaraymana.activities;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.DAO.BookDbImpl;
import cn.gzu.libaraymana.domain.Book;
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
	
	private ListView bookListLv;
	
	private List<Book> books;

	@Override
	protected void setContentViewLayout() {
		setContentView(R.layout.activity_editbook);
	}

	@Override
	protected void findViewById() {
		previousImg = (ImageView) findViewById(R.id.editbook_previous_img);
		menuImg = (ImageView) findViewById(R.id.editbook_menu_img);
		bookListLv = (ListView) findViewById(R.id.editbook_listview);
	}

	@Override
	protected void setCompanentListener() {
		previousImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		BookDbImpl bookDbImpl = new BookDbImpl(this);
		books = bookDbImpl.findAll();
		
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
			return books.size();
		}

		@Override
		public Object getItem(int position) {
			return books.get(position);
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
			
			Book book = books.get(position);
			
			viewHolder.authorTx.setText("【"+book.getAuthor()+"】");
			viewHolder.nameTx.setText(book.getBookname());
			
			return view;
		}}
	
	static class ViewHolder{
		TextView authorTx;
		TextView nameTx;
	}

}
