package cn.gzu.libaraymana.Util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 普通工具类
 * @author GymYung
 * @since 2014-07-05 22:48
 */
public class CommonUtil {
	/**
	 * 本地时间转换
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
}
