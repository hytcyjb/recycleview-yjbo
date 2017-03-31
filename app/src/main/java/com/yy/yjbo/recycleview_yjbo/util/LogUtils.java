package com.yy.yjbo.recycleview_yjbo.util;

import android.util.Log;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
/**
 * 参考：http://os.51cto.com/art/201609/517854.htm
 *     http://os.51cto.com/art/201609/517855.htm
 *
 * @author yjbo
 * @time 2017/3/31 9:57
 * @mail 1457521527@qq.com
 */
//        Logger.init("MainActivity");
//        Logger.i("yjbo--i-1");
//        Logger.d("yjbo--d");
//        Logger.e("yjbo--e--d以下的级别不打印-1");
//        Logger.v("yjbo--v");
//        Logger.w("yjbo--w-1");
//        Logger.wtf("yjbo--wtf-1");
//        Logger.t("yjbo--t");
//        Logger.xml("yjbo-xml-1");
//        Settings setting = Logger.init("MainActivity");
//        setting.logLevel(LogLevel.FULL) //  显示全部日志，LogLevel.NONE不显示日志，默认是Full
//                .methodCount(5)         //  方法栈打印的个数，默认是2
//                .methodOffset(0)        //  设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
//                .hideThreadInfo();     //  隐藏线程信息
//                .logAdapter(new AndroidLogAdapter());// 自定义一个打印适配器，这里适配了Android的Log打印，你也可以自己实现LogAdapter接口来做一些特殊需求的日志打印适配

public class LogUtils {
	private static boolean isDebug = false;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	private static final String TAG = "89mc_log";

	public LogUtils(boolean misDebug){

		this.isDebug = misDebug;

		Settings setting = Logger.init(TAG);
		setting.logLevel(LogLevel.FULL) //  显示全部日志，LogLevel.NONE不显示日志，默认是Full
				.methodCount(5)         //  方法栈打印的个数，默认是2
				.methodOffset(0)        //  设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
				.hideThreadInfo();     //  隐藏线程信息
//                .logAdapter(new AndroidLogAdapter());// 自定义一个打印适配器，这里适配了Android的Log打印，你也可以自己实现LogAdapter接口来做一些特殊需求的日志打印适配
	}
	// 下面四个是默认tag的函数
	public static void i(String msg) {
		if (isDebug)
			yjbo(TAG, msg);
	}

	public static void d(String msg) {
		if (isDebug)
			yjbo(TAG, msg);
//			Log.d(TAG, msg);
	}

	public static void e(String msg) {
		if (isDebug)
			yjbo(TAG, msg);
//			Log.e(TAG, msg);
	}

	public static void v(String msg) {
		if (isDebug)
			yjbo(TAG, msg);
//			Log.v(TAG, msg);
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg) {
		if (isDebug)
			yjbo(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			yjbo(tag, msg);
//			Log.i(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			yjbo(tag, msg);
//			Log.i(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void e(String msg, Exception tr) {
		if (isDebug) {
			Log.i(TAG, msg, tr);
//			Log.e(TAG, msg, tr);
		}
		
	}
	public static void yjbo(String tag, String msg) {
		if (isDebug) {
//			Log.i(TAG, msg);
//			Log.d(tag, msg);
			Logger.i(msg);
		}

	}
}
