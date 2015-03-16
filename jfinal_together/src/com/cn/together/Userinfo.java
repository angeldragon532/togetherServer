package com.cn.together;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Userinfo extends Model<Userinfo>{
	
	public static final Userinfo userinfo = new Userinfo();
	
	
	/**
	 * 所有 sql与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
	 */
	public Page<Userinfo> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from userinfo");
	}
}
