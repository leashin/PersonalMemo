package com.leashin.personalmemo.util;

import android.util.Log;

public final class Logs {
	private static final boolean DEBUG_V = true;
	private static final boolean DEBUG_D = true;
	private static final boolean DEBUG_I = true;
	private static final boolean DEBUG_W = true;
	private static final boolean DEBUG_E = true;

	public static void v(String tag, String msg) {
		Logs.v(tag, msg, DEBUG_V);
	}

	public static void v(String tag, String msg, boolean debug) {
		if (debug) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		Logs.d(tag, msg, DEBUG_D);
	}

	public static void d(String tag, String msg, boolean debug) {
		if (debug) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		Logs.i(tag, msg, DEBUG_I);
	}

	public static void i(String tag, String msg, boolean debug) {
		if (debug) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		Logs.w(tag, msg, DEBUG_W);
	}

	public static void w(String tag, String msg, boolean debug) {
		if (debug) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		Logs.e(tag, msg, DEBUG_E);
	}

	public static void e(String tag, String msg, boolean debug) {
		if (debug) {
			Log.e(tag, msg);
		}
	}
}
