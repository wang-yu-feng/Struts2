package com.wyf.base.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class Page {
	private Integer pageNo;	
	private Integer pageSize;
	private Integer totalRows;
	private Integer totalPage;
	private JSONArray data;
	private JSONObject other;
	
	public Page(){
		this.pageNo = 1;
		this.pageSize = 20;
	}
	
	public Page(int pageNo, int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize==null?20:pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
		if(totalRows%this.getPageSize()==0){
			this.totalPage = totalRows/this.getPageSize();
		}else{
			this.totalPage = totalRows/this.getPageSize() + 1;
		}
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public JSONArray getData() {
		return data;
	}
	public void setData(JSONArray data) {
		this.data = data;
	}
	public JSONObject getOther() {
		return other;
	}
	public void setOther(JSONObject other) {
		this.other = other;
	}
	public String pageToJson(){
		JSONObject json = new JSONObject();
		json.put("pageNo", this.pageNo);
		json.put("pageSize", this.pageSize);
		json.put("totalRows", this.totalRows);
		json.put("totalPage", this.totalPage);
		json.put("data", this.data==null?"[]":this.data.toString());
		json.put("other", this.other);
		return json.toString();
	}
}
