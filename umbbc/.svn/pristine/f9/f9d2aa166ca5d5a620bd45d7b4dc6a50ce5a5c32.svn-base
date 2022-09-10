package com.real.umbbc.dao;

import org.apache.ibatis.annotations.Select;

public interface FilterBlacklistDAO {

	@Select("SELECT count(*) FROM rbtuser.blacklist_postcall where msisdn=#{msisdn}")
	public int selectFilterValue(String msisdn);
}
