package com.real.umbbc.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.real.umbbc.dao.TestDAO;

@Service
public class TestServiceBO {
	@Autowired TestDAO testDAO;
	
	@Cacheable("testCache")
	public String getTestValue(String key){
		return testDAO.selectTestValue();
	}
}
