package com.cn.common;

import com.cn.together.Activityinfo;
import com.cn.together.EmployerPartner;
import com.cn.together.Projectinfo;
import com.cn.together.Userinfo;
import com.cn.together.UserInfoController;
import com.cn.together.Visitorinfo;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		loadPropertyFile("com/cn/together/user.txt");				// 加载少量必要配置，随后可用getProperty(...)获取值
		me.setDevMode(getPropertyToBoolean("devMode", false));
		me.setViewType(ViewType.FREE_MARKER); 		
	   // 设置视图类型为Jsp，否则默认为FreeMarker
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/user", UserInfoController.class,"/index");	// 第三个参数为该Controller的视图存放路径
		me.add("/userlist", UserInfoController.class);	// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		me.add("/regeist",UserInfoController.class);
		me.add("/userlogin",UserInfoController.class);
		me.add("/visitorlist",UserInfoController.class);
		me.add("/activitylist",UserInfoController.class);
		me.add("/projectlist",UserInfoController.class);
		
		//me.add("/visitorlist",VisitorInfoController.class);
		
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("userinfo",Userinfo.class);	// 映射userinfo 表到 userinfo模型
		arp.addMapping("visitorinfo",Visitorinfo.class);
		arp.addMapping("activityinfo",Activityinfo.class);
		arp.addMapping("project",Projectinfo.class);
		arp.addMapping("employpartner",EmployerPartner.class);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}
}
