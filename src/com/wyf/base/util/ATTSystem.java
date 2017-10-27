package com.wyf.base.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public class ATTSystem {

	/** 鐧诲綍鐢ㄦ埛session key */
	public static final String KEY_SESSION_LOGIN = "sysUsers";
	
	/** 鍒楄〃鍒嗛〉鏌ヨ request key */
	public static final String KEY_REQUEST_PAGE = "page";
	
	/** 璁板綍宸插垹闄ゆ爣璁板�� */
	public static final Integer MARK_DELETE = -1;

	/** 鐢ㄦ埛鏉冮檺鑿滃崟缂撳瓨绌洪棿 */
	public static Map<String, JSONArray> MAP_ROLE_MENU = new HashMap<String, JSONArray>();
	
	/** 绯荤粺瑙掕壊鎷ユ湁鐨勬潈闄愯彍鍗� */
	public static Map<String, List<Long>> MAP_MENU_URI_ROLE = new HashMap<String, List<Long>>();
	
	/** 宸茬櫥褰曠敤鎴风殑MAP闆嗗悎 */
	public static Map<String, Long> MAP_SESSION_USERID = new HashMap<String, Long>();
	/**
	 * 璁茬櫥褰曠敤鎴锋暟鎹繚瀛樿嚦map
	 * @param sessionId
	 * @param userId
	 */
	public static void putMapSessionUserid(String sessionId, Long userId){
		Long val = null;
		for(String key : MAP_SESSION_USERID.keySet()){
			val = MAP_SESSION_USERID.get(key);
			if(val!=null && val==userId){
				MAP_SESSION_USERID.remove(key);
			}
		}
		MAP_SESSION_USERID.put(sessionId, userId);
	}
	
	/** 涓嶉渶瑕佺櫥褰曞苟涓斾笉闇�瑕佹潈闄愰獙璇佺殑URI */
	public static String[] LIST_NOT_LOGIN_URI = new String[]{"/", "/login.jsp", "/index.jsp", "/login", "*.css", "*.js", "*.jpg", "*.png", "*.html", "/AuthCode/*", "/layui/*", "/log.jsp"};
	
	
}