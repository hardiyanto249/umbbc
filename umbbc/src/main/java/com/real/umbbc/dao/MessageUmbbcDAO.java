package com.real.umbbc.dao;

import org.apache.ibatis.annotations.Select;

public interface MessageUmbbcDAO {

	@Select("SELECT value FROM rbtuser.rbt_config WHERE name = #{param}")
	public String selectMessageValue(String param);

	@Select("SELECT value FROM rbtuser.rbt_config WHERE name = #{param}")
	public String selectMessageAxisValue(String param);

	@Select("SELECT group_id FROM rbtuser.rbt_umbbc_group WHERE servicecode = #{param}")
	public String getGroupMessageUmbbc(String param);

}