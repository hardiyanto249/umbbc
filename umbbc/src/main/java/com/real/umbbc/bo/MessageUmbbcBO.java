package com.real.umbbc.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.real.umbbc.dao.MessageUmbbcDAO;

@Service
public class MessageUmbbcBO {
	private static final Logger log = Logger.getLogger(MessageUmbbcBO.class);

	@Autowired
	MessageUmbbcDAO messageUmbbcDAO;

	@Cacheable("messageUmbbcCache")
	public String messageUmbbc(int i, String j) {
		//log.debug("Check dayofweek xl: " + i);
		String param = "umb.umbbc.message.day."+ i +"."+ j;
		return messageUmbbcDAO.selectMessageValue(param);
	}

	@Cacheable("messageUmbbcAxisCache")
	public String messageUmbbcAxis(int i, String j) {
		//log.debug("Check dayofweek axis: " + i);
		String param = "umb.umbbc.message.axis.day."+ i +"."+ j;
		return messageUmbbcDAO.selectMessageAxisValue(param);
	}

	@Cacheable("groupMessageUmbbcCache")
	public String getGroupMessageUmbbc(String i) {
		String param = i;
		return messageUmbbcDAO.getGroupMessageUmbbc(param);
	}
}
