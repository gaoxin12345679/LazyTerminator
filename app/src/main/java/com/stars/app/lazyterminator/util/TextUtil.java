package com.stars.app.lazyterminator.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return true 空，false非空
	 */
	public static boolean isEmpty(String str) {
		boolean b = false;
		if (str == null || "".equals(str) || "null".equals(str)
				|| "NULL".equals(str)) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}

	/**
	 * 验证是否是邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_.]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}


	/**
	 * 获取关键帧图片
	 * 
	 * @param video_url
	 * @param count
	 * @return
	 */
	public static String getCIFImageUrl(String video_url, int count) {
		if (video_url == null)
			return null;
		// getbasepath
		int index = video_url.lastIndexOf("/");
		String basepath = video_url.substring(0, index + 1);
		// getbasefilename
		String tmp = video_url.substring(index + 1);
		tmp = tmp.substring(0, tmp.lastIndexOf("."));

		if (count > 3)
			count = 0;
		return basepath + tmp + "_cif_" + count + ".jpg";
	}

	/**
	 * 截取域中的前10为，为附近
	 * 
	 * @param str
	 * @return
	 */
	public static String getNearBase(String str, int length) {
		String re = null;
		if (length > 7)
			length = 7;
		if (str.length() > 10) {
			re = str.substring(0, length);
		}
		return re;

	}


	/**
	 * 替换字符
	 * 
	 * @param text
	 * @param value
	 *            要替换成的字符
	 * @return
	 */
	public static String replace(String text, String value) {
		String[] lines = text.split("&&P&&");
		Pattern pattern = Pattern.compile("\\*\\d+\\*");

		Map<String, String> maps = new HashMap<String, String>();
		for (String line : lines) {
			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
				if (!maps.containsKey(matcher.group())) {
					String key = matcher.group();
					int len = Integer.parseInt(key.replace("*", ""));
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < len; i++) {
						sb.append(value);
					}
					maps.put(key, sb.toString());
				}
			}
		}
		for (String key : maps.keySet()) {
			text = text.replace(key, maps.get(key));
		}
		return text;
	}

	/**
	 * 处理空字符串
	 * 
	 * @param str
	 * @return String
	 */
	public static String doEmpty(String str) {
		return doEmpty(str, "");
	}

	/**
	 * 处理空字符串
	 * 
	 * @param str
	 * @param defaultValue
	 * @return String
	 */
	public static String doEmpty(String str, String defaultValue) {
		if (str == null || str.equalsIgnoreCase("null")
				|| str.trim().equals("") || str.trim().equals("－请选择－")) {
			str = defaultValue;
		} else if (str.startsWith("null")) {
			str = str.substring(4, str.length());
		}
		return str.trim();
	}

	/**
	 * 请选择
	 */
	final static String PLEASE_SELECT = "请选择...";

	public static boolean notEmpty(Object o) {
		return o != null && !"".equals(o.toString().trim())
				&& !"null".equalsIgnoreCase(o.toString().trim())
				&& !"undefined".equalsIgnoreCase(o.toString().trim())
				&& !PLEASE_SELECT.equals(o.toString().trim());
	}
	
	public static boolean empty(Object o) {
		return o == null || "".equals(o.toString().trim())
				|| "null".equalsIgnoreCase(o.toString().trim())
				|| "undefined".equalsIgnoreCase(o.toString().trim());
	}
	
	/**
	 * 给用户名返回JID
	 * 
	 * @param jidFor
	 *            域名//如ahic.com.cn
	 * @param userName
	 * @return
	 */
	public static String getJidByName(String userName, String jidFor) {
		if (empty(userName) || empty(jidFor)) {
			return null;
		}
		
		if(userName.contains(jidFor)){
			return userName;
		}
		
		return userName + "@" + jidFor;
	}
	
	/**
	 * 给JID返回用户名
	 * 
	 * @param Jid
	 * @return
	 */
	public static String getUserNameByJid(String Jid) {
		if (empty(Jid)) {
			return null;
		}
		if (!Jid.contains("@")) {
			return Jid;
		}
		return Jid.split("@")[0];
	}

}
