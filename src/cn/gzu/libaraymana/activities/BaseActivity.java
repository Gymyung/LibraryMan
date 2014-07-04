package cn.gzu.libaraymana.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
/**
 * Activity基类
 * @author GymYung
 * @since 2014-07-03 19:35
 */
public abstract class BaseActivity extends Activity implements OnClickListener{
	/**
	 * 1加载主布局界面
	 */
	protected abstract void setContentViewLayout();
	
	/**
	 * 2找到相关组件
	 */
	protected abstract void findViewById();
	
	/**
	 * 3为组件添加事件监听
	 */
	protected abstract void setCompanentListener();
	
	/**
	 * 4上线处理逻辑
	 */
	protected abstract void loadingDeal();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		initView();
	}

	private void initView(){
		setContentViewLayout();
		findViewById();
		setCompanentListener();
		loadingDeal();
	}
	
	
}
