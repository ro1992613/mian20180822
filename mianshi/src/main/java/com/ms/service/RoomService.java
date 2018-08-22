package com.ms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.dao.RoomDAO;

@Service
public class RoomService {
	@Autowired
	RoomDAO roomDAO;
	
	public List<Map<String, Object>> find(String name,String status,int max_area,int min_area,int max_price,int min_price,
			Date max_start_time,Date max_end_time,Date min_start_time,Date min_end_time,String level,String type,String location,String key,int page_size,int page_index) {
		return roomDAO.find(name, status, max_area, min_area, max_price, min_price, max_start_time, max_end_time, min_start_time, min_end_time, level, type, location, key, page_size, page_index);
	}
	
	public int count(String name,String status,int max_area,int min_area,int max_price,int min_price,
			Date max_start_time,Date max_end_time,Date min_start_time,Date min_end_time,String level,String type,String location,String key) {
		return roomDAO.count(name, status, max_area, min_area, max_price, min_price, max_start_time, max_end_time, min_start_time, min_end_time, level, type, location, key);
	}

}
