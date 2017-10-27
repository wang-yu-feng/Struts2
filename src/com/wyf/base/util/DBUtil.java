package com.wyf.base.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class DBUtil {

	public final String driver = "com.mysql.jdbc.Driver";  
	public final String url = "jdbc:mysql://localhost:3306/crm?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";  
	public final String user = "root";  
	public final String password = "123456";  
	
	/**
	 * 鑾峰彇鏁版嵁搴撹繛鎺�
	 * @return
	 */
	public Connection getConnection(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.err.println("椹卞姩绋嬪簭鍔犺浇澶辫触锛�");
		}
		
		Connection conn = null;
        try {
        	conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) {  
                //System.out.println("杩炴帴鏁版嵁搴撴垚鍔�");  
            } 
        } catch (SQLException e) {
            System.err.println("杩炴帴鏁版嵁搴撳け璐�: "+e.getMessage());  
        }
        
		return conn;
	}
	
	/**
	 * 鍏抽棴鏁版嵁搴撹繛鎺�
	 */
	public void close(Connection conn) {
        try {  
            conn.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }
	
	
	/**
	 * 娣诲姞鏁版嵁
	 * @param table
	 * @param fields
	 * @param values
	 * @return
	 */
	public int insert(String table, String[] fields, Object[] values){
		Connection conn = this.getConnection();
	    int resultNum = 0;
	    StringBuffer sql = new StringBuffer("insert into ");
	    sql.append(table);
	    sql.append(" (" + StringUtils.join(fields, ", ") + ")");
	    sql.append(" values (").append(getMark(values.length)).append(")");
	    
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
	        
	        for(int i=0,len=values.length; i<len; i++){
	        	Object o = values[i];
	        	if (o instanceof Integer) {
	        		pstmt.setInt(i+1, (int) o);
	    		}else if (o instanceof Double) {
	    			pstmt.setDouble(i+1, (double) o);
	    		}else if (o instanceof Long) {
	    			pstmt.setLong(i+1, (long) o);
	    		}else{
	    			pstmt.setString(i+1, o.toString());
	    		}
	        }
	        resultNum = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return resultNum;
	}
	/**
	 * 娣诲姞鏁版嵁
	 * @param sql
	 * @return
	 */
	public int insert(String sql){
		Connection conn = this.getConnection();
	    int resultNum = 0;
	    
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        resultNum = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return resultNum;
	}
	/**
	 * 鑾峰彇鐩稿簲涓暟鐨勯棶鍙�
	 * @param length
	 * @return
	 */
	private String getMark(int length){
		String str = "";
		for(int i=0; i<length; i++){
			if(i==0){
				str = "?";
			}else{
				str += ", ?";
			}
		}
		return str;
	}
	/**
	 * 鏌ヨ
	 * @param sql
	 * @return
	 */
	public JSONObject selectToJson(String sql){
		JSONArray array = this.selectToJsonArray(sql, 1);
		JSONObject json = null;
		if(array!=null && array.length()>0){
			json = array.getJSONObject(0);
		}
		return json;
	}
	/**
	 * 鏌ヨ
	 * @param conn
	 * @param sql
	 * @return
	 */
	public ResultSet select(String sql){
		return this.select(sql, null);
	}
	/**
	 * 鏌ヨ
	 * @param conn
	 * @param sql
	 * @return
	 */
	public ResultSet select(String sql, Integer rows){
		Connection conn = this.getConnection();
		ResultSet rs = null;
		try {
			Statement st = conn.createStatement();
			if(rows!=null){
				sql += " limit 0,"+rows;
			}
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 鏌ヨ
	 * @param sql
	 * @return
	 */
	public JSONArray selectToJsonArray(String sql){
		return this.selectToJsonArray(sql, null);
	}
	/**
	 * 鏌ヨ
	 * @param sql
	 * @return
	 */
	public JSONArray selectToJsonArray(String sql, Integer rows){
		JSONArray array = new JSONArray();
		try {
			ResultSet rs = this.select(sql, rows);
			array = this.resultSetToJsonArray(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	/**
	 * 瀵筊esultSet鏁版嵁杩涜閬嶅巻骞惰繑鍥濴ist
	 * @param rs
	 * @return
	 */
	private JSONArray resultSetToJsonArray(ResultSet rs){
		JSONArray array = new JSONArray();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			Map<String, Integer> keyMap = new LinkedHashMap<String, Integer>();
			for(int i=0; i<columnCount; i++){
				keyMap.put(metaData.getColumnName(i+1), metaData.getColumnType(i+1));
			}
			int i = 0;
			while (rs.next()) {
				JSONObject json = new JSONObject();
				for(String key : keyMap.keySet()){
					switch (keyMap.get(key)) {
						case Types.INTEGER:
							String val = rs.getString(key);
							json.put(key, StringUtils.isEmpty(val)?"":Integer.valueOf(val));
							break;
						case Types.BIGINT:
							json.put(key, rs.getLong(key));
							break;
						case Types.DOUBLE:
							json.put(key, rs.getDouble(key));
							break;
						default:
							json.put(key, rs.getString(key));
							break;
					}
				}
				array.put(i, json);
				i++;
			}
		} catch (Exception e) {
		}
		return array;
	}
	/**
	 * 鏇存柊
	 * @param sql
	 * @return
	 */
	public int update(String sql) {
		Connection conn = this.getConnection();
	    int resultNum = 0;
	    
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        resultNum = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return resultNum;
	}
	/**
	 * 鍒犻櫎
	 * @param sql
	 * @return
	 */
	public int delete(String sql) {
		Connection conn = this.getConnection();
	    int resultNum = 0;
	    
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        resultNum = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return resultNum;
	}
	/**
	 * 鍒嗛〉鏌ヨ
	 * @param page
	 */
	public void search(Page page, String sql){
		Connection conn = this.getConnection();
		ResultSet rs = null;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql + " limit " + ((page.getPageNo()-1)*page.getPageSize()) + ", " + page.getPageSize());
			page.setData(this.resultSetToJsonArray(rs));
			
			String countSql = sql.substring(sql.indexOf("from"));
			if(StringUtils.isEmpty(countSql)){
				countSql = sql.substring(sql.indexOf("FROM"));
			}
			countSql = "select count(1) " + countSql;
			st = conn.createStatement();
			rs = st.executeQuery(countSql);
			rs.next();
			page.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Page page = new Page();
		DBUtil dbUtil = new DBUtil();
		dbUtil.search(page, "select * from test");
		System.out.println(page.getTotalRows());
	}
	
}