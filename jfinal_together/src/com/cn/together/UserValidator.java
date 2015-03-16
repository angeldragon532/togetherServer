package com.cn.together;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 *UserinfoValidator. 暂时不验证
 */
public class UserValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("userinfo.name", "titleMsg", "");
		validateRequiredString("userinfo.content", "contentMsg", "");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Userinfo.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/user/save"))
			controller.render("add.jsp");
		else if (actionKey.equals("/user/regeist"))
			controller.render("edit.jsp");
	}
}
