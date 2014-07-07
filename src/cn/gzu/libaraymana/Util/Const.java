package cn.gzu.libaraymana.Util;

import cn.gzu.libaraymana.domain.User;
/**
 * 常量定义容器类
 * @author GymYung
 * @since 2014-07-03 19:35
 */
public class Const {
	public static User user;
	
	public final static String SharedPerName = "MySharedPerName";
	
	public final static String USERNAMEET = "usernameet";
	
	public final static String PASSWORDET = "passwordet";
	
	public final static String FAKUANDAY = "faKuanDays";
	
	public final static String PRICEADAY = "priceDay";
	
	
	/** 借书免费天数 **/
	public final static int faKuanDays_default = 60;
	/** 罚款价格（以天计算） **/
	public final static float priceDay_default = 0.5f;
	
}
