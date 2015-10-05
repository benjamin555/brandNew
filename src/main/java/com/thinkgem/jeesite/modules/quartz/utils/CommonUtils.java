package com.thinkgem.jeesite.modules.quartz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author 陈嘉镇
* @version 创建时间：2015-10-5 上午10:04:49
*/
public class CommonUtils {
	/***
	 * 
	 * @param date
	 * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	private static String formatDateByPattern(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}

	/***
		 * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
		 * @param date  : 时间点
		 * @return
		 */
	public static String getCron(java.util.Date date) {
		String dateFormat = "ss mm HH dd MM ? yyyy";
		return formatDateByPattern(date, dateFormat);
	}
}
