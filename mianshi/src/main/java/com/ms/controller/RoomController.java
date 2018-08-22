package com.ms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.service.RoomService;
import com.ms.utils.APIResult;
import com.ms.utils.Page;

import cn.hutool.core.date.DateUtil;
@RestController
public class RoomController {

	@Autowired
	RoomService roomService;
	
	@RequestMapping(value="/find")
	public void findList(HttpServletRequest request,HttpServletResponse rsp) throws IOException {
		String name=request.getParameter("name");
		String status=request.getParameter("status");
		int max_area=getNum(request.getParameter("max_area"));
		int min_area=getNum(request.getParameter("min_area"));
		int max_price=getNum(request.getParameter("max_price"));
		int min_price=getNum(request.getParameter("min_price"));
		Date max_start_time=getDate(request.getParameter("max_start_time"));
		Date max_end_time=getDate(request.getParameter("max_end_time"));
		Date min_start_time=getDate(request.getParameter("min_start_time"));
		Date min_end_time=getDate(request.getParameter("min_end_time"));
		String level=request.getParameter("level");
		String type=request.getParameter("type");
		String location=request.getParameter("location");
		String key=request.getParameter("key");
		int page_index=getNum(request.getParameter("page_index"));
		int page_size=20;
		
		List<Map<String, Object>> list=roomService.find(name, status, max_area, min_area, max_price, min_price, max_start_time, max_end_time, min_start_time, min_end_time, level, type, location, key, page_size, page_index);
		int count =roomService.count(name, status, max_area, min_area, max_price, min_price, max_start_time, max_end_time, min_start_time, min_end_time, level, type, location, key);
		APIResult<Page<Map<String, Object>>> result=new APIResult<>();
		result.setCode(1);		
		Page<Map<String, Object>> page=new Page<>(count, page_index, page_size, list);
		result.setData(page);
		
		
		
	}
	
	
	public int getNum(String str) {
		int num=0;
		try {
			num=Integer.parseInt(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return num;
	}
	
	public Date getDate(String str) {
		Date date=null;
		
		try {
			date=DateUtil.parse(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return date;
	}
}
