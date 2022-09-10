package com.real.umbbc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.real.umbbc.bo.TestServiceBO;

@Controller
public class TestController {

	private static final Logger log = Logger.getLogger(TestController.class);

	@Autowired
	TestServiceBO testServiceBO;

	@RequestMapping(value = "/test")
	@ResponseBody
	public String test() {
		String test = testServiceBO.getTestValue("key");
		log.debug("allohaa..." + test);
		return test;
	}

	@RequestMapping(value = "/testUmbHit")
	@ResponseBody
	public void testUmbHit(
			@RequestParam(value = "msisdn") String msisdn,
			@RequestParam(value = "servicecode", required = false) String serviceCode,
			@RequestParam(value = "page", required = false) String page) {
		log.debug("Receive umb hit with msisdn[" + msisdn + "], servicecode["
				+ serviceCode + "], page[" + page + "]");
	}
}
