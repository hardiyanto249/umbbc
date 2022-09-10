package com.real.umbbc.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.real.umbbc.dao.FilterBlacklistDAO;

@Service
public class FilterBlacklistBO {
	private static final Logger log = Logger.getLogger(FilterBlacklistBO.class);
	@Autowired
	FilterBlacklistDAO filterBlacklistDAO;

	@Cacheable("filterBlacklistCache")
	public int getCountMsisdnValue(String msisdn) {
		log.debug("Check Count Msisdn: " + msisdn);
		return filterBlacklistDAO.selectFilterValue(msisdn);
	}
}
