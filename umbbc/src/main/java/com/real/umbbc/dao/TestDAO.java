package com.real.umbbc.dao;

import org.apache.ibatis.annotations.Select;

public interface TestDAO {

	@Select("select 'testing' as value from dual")
	public String selectTestValue();
}
