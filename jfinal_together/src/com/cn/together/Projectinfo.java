package com.cn.together;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Projectinfo extends Model<Projectinfo>{
	
	public static final Projectinfo projectinfo = new Projectinfo();
	
	
	/**
	 * 所有 sql与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
	 */
	public Page<Projectinfo> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from project");
	}
}
