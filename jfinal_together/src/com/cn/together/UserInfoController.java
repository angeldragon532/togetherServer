package com.cn.together;

import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * UserInfoController
 */
public class UserInfoController extends Controller {
	
	//{"code":200,"userId":"402880ef4a",
	//"token":"OJCIkZ8MEMqYCdZhdXG+FKh8pJ5XWEGNBbPum96TxKiuX+NjPIWCXP7wake+4XVMu7P/ayGClCTEE3pcr14Qjo+8Am9aMJ4v"}
	private String userToken;
	
	public void index() {
		System.out.println("-----index-----");
		render("index.jsp");
	}
	
	public void userlist(){
		int pageIndex = Integer.valueOf(getPara("pageIndex"));
		int pageSize = Integer.valueOf(getPara("pageSize"));
		String roleId =  getPara("userRole");
		Page<Userinfo> userinfos = Userinfo.userinfo.paginate(pageIndex,pageSize,"select * ", "from userinfo where userRole='"+roleId+"'");
		List<Userinfo> userList = userinfos.getList();
		System.out.println("--------userlist------"+userList.size());
		renderJson(userList);
	}
	
	public void visitorlist(){
		int pageIndex = Integer.valueOf(getPara("pageIndex"));
		int pageSize = Integer.valueOf(getPara("pageSize"));
		Page<Visitorinfo> visitorinfos = Visitorinfo.visitorinfo.paginate(pageIndex,pageSize);
		List<Visitorinfo> visitorList = visitorinfos.getList();
		System.out.println("--------visitorlist------"+visitorList.size());
		renderJson(visitorList);
	}
	
	public void regeist(){
		String roleID = getPara("roleID");
		if("2".equals(roleID)){//投资人
			Visitorinfo visitorinfo = getModel(Visitorinfo.class);
			boolean result = visitorinfo.save();
			if(result){
				try {
					String name = getPara("visitorinfo.name");
					SdkHttpResult value = ApiHttpClient.getToken(Constant.APPKEY,Constant.APP_SELECT, name, name,
							"http://qlogo3.store.qq.com/qzone/664703194/664703194/100?1424861160", FormatType.json);
					if(value!=null){
						userToken = value.getResult();
						JSONObject jsonReult = JSONObject.parseObject(userToken);
						visitorinfo.set("rongyunToken",jsonReult.getString("token"));
						visitorinfo.update();
					}
					System.out.println("-------userToken="+value.getResult());
				} catch (Exception e) {
					e.printStackTrace();
				}
				renderJson(visitorinfo.toJson());
			}else{
				renderError(500);
			}
		}else{//发起人、合伙人
			Userinfo userfoUserinfo = getModel(Userinfo.class);
			boolean result1 =  userfoUserinfo.save();
			if(result1){
				try {
					String name = getPara("userinfo.name");
					SdkHttpResult value = ApiHttpClient.getToken(Constant.APPKEY,Constant.APP_SELECT, name, name,
							"http://qlogo3.store.qq.com/qzone/664703194/664703194/100?1424861160", FormatType.json);
					if(value!=null){
						userToken = value.getResult();
						JSONObject jsonReult = JSONObject.parseObject(userToken);
						userfoUserinfo.set("rongyunToken",jsonReult.getString("token"));
						userfoUserinfo.update();
					}
					System.out.println("-------userToken="+value.getResult());
				} catch (Exception e) {
					e.printStackTrace();
				}
				renderJson(userfoUserinfo.toJson());
			}else{
				renderError(500);
			}
		}
	}
	
	public void userlogin(){
		String userphone = getPara("userphone","");
		String userpassword = getPara("userpassword","");
		System.out.println("-------userphone="+userphone+",----userpassword="+userpassword);
			Userinfo user = Userinfo.userinfo.findFirst("select * from userinfo where phone=? and password=?",userphone,userpassword);
		    Visitorinfo visitorinfo = Visitorinfo.visitorinfo.findFirst("select * from visitorinfo where phone=? and password=?",userphone,userpassword);
			if(user != null){
			    JSONObject jsonObjectResult = JSONObject.parseObject(user.toJson());
			    System.out.println("----long---userToken="+user.toJson());
				renderJson(jsonObjectResult);
			}else if(visitorinfo != null){
				JSONObject jsonObjectResult = JSONObject.parseObject(visitorinfo.toJson());
			    System.out.println("----long---userToken="+visitorinfo.toJson());
				renderJson(jsonObjectResult);
			}else{	
				renderError(500);
			}
	 }
	
	public void activitylist(){
		int pageIndex = Integer.valueOf(getPara("pageIndex"));
		int pageSize = Integer.valueOf(getPara("pageSize"));
		Page<Activityinfo> activityinfos = Activityinfo.activityinfo.paginate(pageIndex,pageSize);
		List<Activityinfo> activityList = activityinfos.getList();
		System.out.println("--------activitylist------"+activityList.size());
		renderJson(activityList);
	}
	
	public void projectlist(){
		int pageIndex = Integer.valueOf(getPara("pageIndex"));
		int pageSize = Integer.valueOf(getPara("pageSize"));
		Page<Projectinfo> projectinfos = Projectinfo.projectinfo.paginate(pageIndex,pageSize);
		List<Projectinfo> projectList = projectinfos.getList();
		for(Projectinfo info : projectList){
			List<EmployerPartner> employList = EmployerPartner.employpPartner.find("select e.* from employpartner e join projectemploy p on e.id = p.employ_id where p.project_id=?",
					info.get("id"));
			info.put("partnerList",employList);
		}
		System.out.println("--------projectlist------"+projectList.size()+"--"+projectList.toString());
		renderJson(projectList);
	}
}


