package cn.gzu.libaraymana.activities;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.Util.Const;
import cn.gzu.libaraymana.Util.MainMenuAnimaUtil;
/**
 * 主界面
 * @author GymYung
 * @since 2014-07-03 19:54
 */
public class MainActivity extends BaseActivity {
	/** 问候语 **/
	private TextView welcomeTx;
	/** 主菜单 添加图书 **/
	private LinearLayout addbookLl;
	/** 主菜单  修改图书 **/
	private LinearLayout editbookLl;
	/** 主菜单  查询检索 **/
	private LinearLayout querybookLl;
	/** 主菜单  办理借书 **/
	private LinearLayout jiebookLl;
	/** 主菜单  办理还书 **/
	private LinearLayout huanbookLl;
	/** 主菜单  借书人管理 **/
	private LinearLayout usermanaLl;
	/** 主菜单  系统设置 **/
	private LinearLayout settingLl;
	/** 主菜单  版本信息 **/
	private LinearLayout versionLl;
	
	/** 底部菜单   主菜单布局**/
	private RelativeLayout animMenuMainRl;
	/** 底部菜单   一级菜单布局 **/
	private RelativeLayout animMenulevel1Rl;
	/** 底部菜单   二级菜单布局  **/
	private RelativeLayout animMenulevel2Rl;
	/** 底部菜单   三级菜单布局  **/
	private RelativeLayout animMenulevel3Rl;
	
	/** 底部菜单   主菜单 **/
	private ImageView honeMenuImg;
	/** 底部菜单   搜索 **/
	private ImageView searchImg;
	/** 底部菜单   二级主菜单 **/
	private ImageView menuImg;
	/** 底部菜单   版本信息 **/
	private ImageView infoImg;
	
	/** 底部菜单  三级菜单1  **/
	private ImageView level3Menu1Img;
	/** 底部菜单  三级菜单2 **/
	private ImageView level3Menu2Img;
	/** 底部菜单   三级菜单3**/
	private ImageView level3Menu3Img;
	/** 底部菜单   三级菜单4 **/
	private ImageView level3Menu4Img;
	/** 底部菜单   三级菜单7 **/
	private ImageView level3Menu7Img;
	/** 底部菜单   三级菜单6 **/
	private ImageView level3Menu6Img;
	/** 底部菜单   三级菜单5 **/
	private ImageView level3Menu5Img;
	
	
	/**
	 * 判断 第3级菜单是否显示
	 * true 为显示
	 */
	private boolean isLevel3Show = true;
	/**
	 * 判断 第2级菜单是否显示
	 * true 为显示
	 */
	private boolean isLevel2Show = true;
	/**
	 * 判断 第1级菜单是否显示
	 * true 为显示
	 */
	private boolean isLevel1show = true;
	/**
	 * 判断 底部全部菜单是否显示
	 * true 为显示
	 */
	private boolean isAllMenuShow = false;

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
		
		animMenuMainRl = (RelativeLayout) findViewById(R.id.anim_menu_rl);
		animMenulevel1Rl = (RelativeLayout) findViewById(R.id.anim_menu_level1);
		animMenulevel2Rl = (RelativeLayout) findViewById(R.id.anim_menu_level2);
		animMenulevel3Rl = (RelativeLayout) findViewById(R.id.anim_menu_level3);
		honeMenuImg = (ImageView) findViewById(R.id.anim_menu_icon_home);
		searchImg = (ImageView) findViewById(R.id.anim_menu_icon_search);
		menuImg = (ImageView) findViewById(R.id.anim_menu_icon_menu);
		infoImg = (ImageView) findViewById(R.id.anim_menu_icon_myyouku);
		level3Menu1Img = (ImageView) findViewById(R.id.anim_menu_channel1);
		level3Menu2Img = (ImageView) findViewById(R.id.anim_menu_channel2);
		level3Menu3Img = (ImageView) findViewById(R.id.anim_menu_channel3);
		level3Menu4Img = (ImageView) findViewById(R.id.anim_menu_channel4);
		level3Menu7Img = (ImageView) findViewById(R.id.anim_menu_channel7);
		level3Menu6Img = (ImageView) findViewById(R.id.anim_menu_channel6);
		level3Menu5Img = (ImageView) findViewById(R.id.anim_menu_channel5);
		
		
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
		
		honeMenuImg.setOnClickListener(this);
		searchImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
		infoImg.setOnClickListener(this);
		level3Menu1Img.setOnClickListener(this);
		level3Menu2Img.setOnClickListener(this);
		level3Menu3Img.setOnClickListener(this);
		level3Menu4Img.setOnClickListener(this);
		level3Menu7Img.setOnClickListener(this);
		level3Menu6Img.setOnClickListener(this);
		level3Menu5Img.setOnClickListener(this);
	}

	@Override
	protected void loadingDeal() {
		welcomeTx.setText("欢迎您： "+Const.user.getUsername());
		animMenuMainRl.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View view) {
		Intent intent;
		switch(view.getId()){
		case R.id.main_menuitem_addbook_ll:
			//添加图书
			intent = new Intent(MainActivity.this,AddBookActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			
			break;
		case R.id.main_menuitem_editbook_ll:
			//修改图书
			intent = new Intent(MainActivity.this,EditBookActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
					
			break;
		case R.id.main_menuitem_querybook_ll:
			//查询检索
			intent = new Intent(MainActivity.this,SearchInfoActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			
			break;
		case R.id.main_menuitem_jiebook_ll:
			//办理借书
			intent = new Intent(MainActivity.this,JieBookActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			
			break;
		case R.id.main_menuitem_huanbook_ll:
			//办理还书
			intent = new Intent(MainActivity.this,HuanBookActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			
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
			
			/************************************以下是底部菜单项目*****************************************************/
		case R.id.anim_menu_icon_home:
			/** 底部菜单   主菜单 **/
			// 如果第2级菜单是显示状态，那么就隐藏，2，3级菜单
			if(isLevel2Show ){
				MainMenuAnimaUtil.startAnimOut(animMenulevel2Rl);
				isLevel2Show = false;
				
				if(isLevel3Show){ // 如果此时，第3级菜单也显示，那也将其隐藏
					MainMenuAnimaUtil.startAnimOut(animMenulevel3Rl,200);
					isLevel3Show = false;
				}
				
			}else{
				// 如果第2级菜单是隐藏状态，那么就显示2级菜单
				MainMenuAnimaUtil.startAnimIn(animMenulevel2Rl);
				isLevel2Show = true;
			}
			
			break;
		case R.id.anim_menu_icon_search:
			/** 底部菜单   搜索 **/
					
			break;
		case R.id.anim_menu_icon_menu:
			/** 底部菜单   二级主菜单 **/
			//处理 menu 图标的点击事件
			// 如果第3级菜单是显示状态，那么将其隐藏
			if(isLevel3Show){
				//隐藏 第3级菜单
				MainMenuAnimaUtil.startAnimOut(animMenulevel3Rl);
			}else{
				// 如果第3级菜单是隐藏状态，那么将其显示
				MainMenuAnimaUtil.startAnimIn(animMenulevel3Rl);
			}
			
			isLevel3Show = !isLevel3Show;
			
			break;
		case R.id.anim_menu_icon_myyouku:
			/** 底部菜单   版本信息 **/
			
			break;
		case R.id.anim_menu_channel1:
			/** 底部菜单  三级菜单1  **/
			//添加图书
			intent = new Intent(MainActivity.this,AddBookActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			
			break;
		case R.id.anim_menu_channel2:
			/** 底部菜单  三级菜单2  **/
			//修改图书
			intent = new Intent(MainActivity.this,EditBookActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
			
			break;
		case R.id.anim_menu_channel3:
			/** 底部菜单  三级菜单3  **/
			
			break;
		case R.id.anim_menu_channel4:
			/** 底部菜单  三级菜单4  **/
			
			break;
		case R.id.anim_menu_channel7:
			/** 底部菜单  三级菜单7  **/
			
			break;
		case R.id.anim_menu_channel6:
			/** 底部菜单  三级菜单6  **/
			
			break;
		case R.id.anim_menu_channel5:
			/** 底部菜单  三级菜单5  **/
			
			break;
		}
	}
	
	
	private long firstClick;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==event.KEYCODE_BACK){
			long secondClick = System.currentTimeMillis();
			if(secondClick - firstClick > 1000){
				Toast.makeText(MainActivity.this, R.string.click_again_exit_program, Toast.LENGTH_SHORT).show();
				firstClick = secondClick;
				return true;
			}else{
				//此处添加退出整个应用的代码
				finish();
				return true;
			}
		}
		
		if(keyCode == KeyEvent.KEYCODE_MENU){ // 监听 menu 按键
			if(isAllMenuShow){
				changeLevel1State();
				//isAllMenuShow = false;
			}else{
				animMenuMainRl.setVisibility(View.VISIBLE);
				isAllMenuShow = true;
			}
			
		}
		
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	/**
	 * 改变第1级菜单的状态
	 */
	private void changeLevel1State() {
		//如果第1级菜单是显示状态，那么就隐藏 1，2，3级菜单 
		if(isLevel1show){
			MainMenuAnimaUtil.startAnimOut(animMenulevel1Rl);
			isLevel1show = false;
			
			if(isLevel2Show){ // 判断2级菜单是否显示
				MainMenuAnimaUtil.startAnimOut(animMenulevel2Rl,100);
				isLevel2Show = false;
				if(isLevel3Show){ // 判断3级菜单是否显示
					MainMenuAnimaUtil.startAnimOut(animMenulevel3Rl,200);
					isLevel3Show = false;
				}
			}
			
		}else{
			//如果第1级菜单是隐藏状态，那么就显示 1，2级菜单 
			MainMenuAnimaUtil.startAnimIn(animMenulevel1Rl);
			isLevel1show = true;
			
			MainMenuAnimaUtil.startAnimIn(animMenulevel2Rl,200);
			isLevel2Show = true;
			
		}
		
	}

}
