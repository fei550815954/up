package cn.kingcd.up;

import android.util.Log;

/**
 * Log统一管理类
 * 
 * @author fei
 * 
 */
public class L {

	private L() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = false;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	private static final String TAG = "FEI";

	// 下面四个是默认tag的函数
	public static void i(String msg) {
		if (isDebug)
			Log.i(TAG, msg);
	}

	public static void d(String msg) {
		if (isDebug)
			Log.d(TAG, msg);
	}

	public static void e(String msg) {
		if (isDebug)
			Log.e(TAG, msg);
	}

	public static void v(String msg) {
		if (isDebug)
			Log.v(TAG, msg);
	}

	public static void w(String msg) {
		if (isDebug) {
			Log.w(TAG, msg);
		}
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}
	public static void w(String tag, String msg) {
		if (isDebug)
			Log.w(tag, msg);
	}
	/**
	 * 分段打印出较长log文本
	 *
	 * @param log 原log文本
	 */
	public static void longLog(String log) {
		if (log.length() > 4000) {
			String show = log.substring(0, 4000);
			L.e(show + "");
			if ((log.length() - 4000) > 4000) {//剩下的文本还是大于规定长度
				String partLog = log.substring(4000, log.length());
				longLog(partLog);
			} else {
				String surplusLog = log.substring(4000, log.length());
				L.e(surplusLog + "");
			}

		} else {
			L.e(log + "");
		}
	}
}