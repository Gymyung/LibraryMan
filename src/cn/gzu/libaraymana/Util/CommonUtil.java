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
	
	/**
	 * 时间比较 以天数返回
	 * @param nowTime	现在的时间
	 * @param compareTime	要比较的时间
	 * @return 天数
	 */
	public static long compareTimeToDay(long nowTime,long compareTime){
		long distance = 0;
		if(nowTime>compareTime){
			distance = nowTime - compareTime;
		}else{
			distance = compareTime - nowTime;
		}
		 
		return distance/1000/60/60/24;
	}
	
	/**
	 * 超期罚款计算
	 * @param days 天数
	 * @param price	每天罚款数
	 * @return	总罚款数
	 */
	public static float getPayment(int days,float price){
		return days * price;
	}
	
}
