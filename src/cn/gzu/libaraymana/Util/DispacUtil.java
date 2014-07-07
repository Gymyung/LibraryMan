package cn.gzu.libaraymana.Util;

import android.content.Intent;
import cn.gzu.libaraymana.R;
import cn.gzu.libaraymana.activities.BaseActivity;
import cn.gzu.libaraymana.activities.MainActivity;

public class DispacUtil {
	
	/** 回到主界面 **/
	public static void goToMainActivity(BaseActivity activity){
		Intent intent = new Intent(activity,MainActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.ad_enter_lefttoright, R.anim.ad_exit_righttoleft);
		activity.finish();
	}
}
