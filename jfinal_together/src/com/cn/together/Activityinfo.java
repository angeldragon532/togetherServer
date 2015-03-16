package com.cn.together;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Activityinfo extends Model<Activityinfo>{
	
	public static final Activityinfo activityinfo = new Activityinfo();
	
	
	/**
	 * 所有 sql与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
	 */
	public Page<Activityinfo> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from activityinfo");
	}
}
