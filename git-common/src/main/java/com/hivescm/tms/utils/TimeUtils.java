package com.hivescm.tms.utils;


import java.util.Calendar;


public class TimeUtils {
	
	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static Long getNowtamp() {
		 Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis();
	}
	/**
	 * 得到本月初时间戳
	 *
	 * @return
	 */
	public static Long getMonthStartstamp() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
	    return calendar.getTimeInMillis();
	}

	/**
	 * 得到当天开始的时间戳
	 *
	 * @return
	 */
	public static Long getTodaystamp() {
    	Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTimeInMillis();
	}
	/**
	 * 得到本周开始时间戳
	 *
	 * @return
	 */
	public static Long getWeekstamp() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.DAY_OF_WEEK, 1);
	    calendar.set(Calendar.HOUR_OF_DAY, 1);
	    return calendar.getTimeInMillis();
	}

	/**
	 * 得到上月开始时间戳
	 *
	 * @return
	 */
	public static Long getLastMonthStartStamp() {
	    Calendar calendar = Calendar.getInstance();
	    int month = calendar.get(Calendar.MONTH);
	    if (month == 1) {
	        calendar.set(Calendar.MONTH, 12);
	    } else {
	        calendar.set(Calendar.MONTH, month - 1);
	    }
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.set(Calendar.HOUR_OF_DAY, 1);
	    return calendar.getTimeInMillis();
	}

	/**
	 * 得到上月结束时间戳
	 *
	 * @return
	 */
	public static Long getLastMonthEndStamp() {
	    Calendar calendar = Calendar.getInstance();
	    int month = calendar.get(Calendar.MONTH);
	    if (month == 1) {
	        calendar.set(Calendar.MONTH, 12);
	    } else {
	        calendar.set(Calendar.MONTH, month - 1);
	    }
	    int dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    calendar.set(Calendar.DAY_OF_MONTH, dayCount);
	    calendar.set(Calendar.HOUR_OF_DAY, 24);
	    return calendar.getTimeInMillis();
	}

	/**
	 * 得到年度开始时间戳
	 *
	 * @return
	 */
	public static Long getYearStartStamp() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.MONTH, 1);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.set(Calendar.HOUR_OF_DAY, 1);
	    return calendar.getTimeInMillis();
	}
}
