package com.ms.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.StrUtil;

@Component
public class RoomDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> find(String name,String status,int max_area,int min_area,int max_price,int min_price,
			Date max_start_time,Date max_end_time,Date min_start_time,Date min_end_time,String level,String type,String location,String key,int page_size,int page_index) {
		List<Map<String, Object>> list=null;
		
		String sql="select * from room_info where 1=1 ";
		if(!StrUtil.isEmpty(name)) {
			sql=sql+" and room_name like '%"+name+"%' ";
		}
		if(!StrUtil.isEmpty(status)) {
			sql=sql+" and room_status='"+status+"' ";
		}
		if(!StrUtil.isEmpty(level)) {
			sql=sql+" and room_level='"+level+"' ";
		}
		if(!StrUtil.isEmpty(location)) {
			sql=sql+" and room_location='"+location+"' ";
		}
		if(!StrUtil.isEmpty(type)) {
			sql=sql+" and room_type='"+type+"' ";
		}
		if(min_price>0 ) {
			sql=sql+" and room_price>"+min_price;
		}
		if(max_price>0 ) {
			sql=sql+" and room_price<"+max_price;
		}
		
		if(min_area>0 ) {
			sql=sql+" and room_area>"+min_area;
		}
		if(max_area>0 ) {
			sql=sql+" and room_area<"+max_area;
		}
		
		if(max_start_time!=null ) {
			sql=sql+" and start_time<"+max_start_time;
		}
		if(min_start_time!=null ) {
			sql=sql+" and start_time>"+min_start_time;
		}
		
		if(max_end_time!=null ) {
			sql=sql+" and end_time<"+max_end_time;
		}
		if(min_end_time!=null ) {
			sql=sql+" and end_time>"+min_end_time;
		}
		
		if(page_index<=1) {
			page_index=1;
		}
		if(page_size<=1) {
			page_size=10;
		}
		sql=sql+" limit "+(page_index-1)*page_size+","+page_size ;
		
		try {
			list=jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int count(String name,String status,int max_area,int min_area,int max_price,int min_price,
			Date max_start_time,Date max_end_time,Date min_start_time,Date min_end_time,String level,String type,String location,String key) {
		int count=0;
		
		String sql="select count(1) as size from room_info where 1=1 ";
		if(!StrUtil.isEmpty(name)) {
			sql=sql+" and room_name like '%"+name+"%' ";
		}
		if(!StrUtil.isEmpty(status)) {
			sql=sql+" and room_status='"+status+"' ";
		}
		if(!StrUtil.isEmpty(level)) {
			sql=sql+" and room_level='"+level+"' ";
		}
		if(!StrUtil.isEmpty(location)) {
			sql=sql+" and room_location='"+location+"' ";
		}
		if(!StrUtil.isEmpty(type)) {
			sql=sql+" and room_type='"+type+"' ";
		}
		if(min_price>0 ) {
			sql=sql+" and room_price>"+min_price;
		}
		if(max_price>0 ) {
			sql=sql+" and room_price<"+max_price;
		}
		
		if(min_area>0 ) {
			sql=sql+" and room_area>"+min_area;
		}
		if(max_area>0 ) {
			sql=sql+" and room_area<"+max_area;
		}
		
		if(max_start_time!=null ) {
			sql=sql+" and start_time<"+max_start_time;
		}
		if(min_start_time!=null ) {
			sql=sql+" and start_time>"+min_start_time;
		}
		
		if(max_end_time!=null ) {
			sql=sql+" and end_time<"+max_end_time;
		}
		if(min_end_time!=null ) {
			sql=sql+" and end_time>"+min_end_time;
		}
		try {
			SqlRowSet rs=jdbcTemplate.queryForRowSet(sql);
			if(rs.next()) {
				count=rs.getInt("size");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
