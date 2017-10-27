package com.wyf.base.util;

public class MD0Util {
	/**
	 * 閾炬帴鐨勫瓧绗�
	 */
	private static String connection_char = "-";
	/**
	 * 鍔犲瘑鐨勫鏉傜▼搴�
	 */
	private static int encryption_num = 1;
	/**
	 * 鍔犲瘑鎴栬В瀵嗚繑鍥炵殑鏁版嵁
	 */
	private static StringBuffer sb_str;
	
	private static MD0Util instance = null;
	/**
	 * 鑾峰彇涓�涓狹D0Util瀵硅薄
	 * @author zejunwu
	 * @date Aug 26, 2013 4:43:39 PM
	 * @param c		杩炴帴绗�
	 * @param e_num 鍔犲瘑鐨勫鏉傚害锛屽�艰秺澶ц秺澶嶆潅(闈炲繀瑕佽涓嶅～鎴栧～鍐�3浠ヤ笅鐨勬暟瀛楋紝鑻ュ～鍐欒鍊硷紝鍔犲瘑鍜岃В瀵嗗繀椤讳竴鑷�)
	 * @return
	 */
	public static MD0Util getInstance(String c, int e_num){
		sb_str = new StringBuffer("");
		if(c==null || "".equals(c)){
			connection_char = "-";
		}else{
			connection_char = c;
		}
		if(e_num>0){
			encryption_num = e_num;
		}else{
			encryption_num = 1;
		}
		if(instance==null){
			instance = new MD0Util();
		}
		return instance;
	}
	public static MD0Util getInstance(int e_num){
		connection_char = "-";
		encryption_num = e_num;
		sb_str = new StringBuffer("");
		if(instance==null){
			instance = new MD0Util();
		}
		return instance;
	}
	public static MD0Util getInstance(){
		connection_char = ".";
		encryption_num = 1;
		sb_str = new StringBuffer("");
		if(instance==null){
			instance = new MD0Util();
		}
		return instance;
	}
	
	/**
	 * 鍔犲瘑鏁版嵁
	 * @author zejunwu
	 * @date Aug 26, 2013 3:59:44 PM
	 * @param str
	 * @return
	 */
	public static String encryptionMD0ofStr(String str){
		if(str!=null && !"".equals(str)){
			int random = (int)(Math.random()*100);
			StringBuffer sb = new StringBuffer(""+random);
			char[] cs = str.trim().toCharArray();
			for(char c : cs){
				sb.append(connection_char+(((int)c)+random));
			}
			sb_str = sb;
			encryption_num--;
			if(encryption_num>0){
				encryptionMD0ofStr(sb_str.toString());
			}
		}
		return sb_str.toString();
	}
	/**
	 * 瑙ｅ瘑鏁版嵁
	 * @author zejunwu
	 * @date Aug 26, 2013 4:00:24 PM
	 * @param str
	 * @return
	 */
	public static String decryptionMD0ofStr(String str){
		if(str!=null && !"".equals(str)){
			String[] strs = str.trim().split(connection_char);
			if(strs.length>0){
				StringBuffer sb = new StringBuffer("");
				int random = Integer.valueOf(strs[0]);
				int str_test = 0;
				for(int i=1,len=strs.length; i<len; i++){
					str_test = Integer.valueOf(strs[i])-random;
					sb.append((char)str_test);
				}
				sb_str = sb;
				encryption_num--;
				if(encryption_num>0){
					decryptionMD0ofStr(sb_str.toString());
				}
			}
		}
		return sb_str.toString();
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		System.out.println(MD0Util.getInstance(".", 10).encryptionMD0ofStr("123456"));
	}

	
}
