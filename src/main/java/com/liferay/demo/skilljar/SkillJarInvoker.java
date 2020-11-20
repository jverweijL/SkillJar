package com.liferay.demo.skilljar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import java.io.IOException;
@Component(
		immediate = true,
		service = SkillJarInvoker.class
)
public class SkillJarInvoker {
	public String getSkillJarInfo() throws PortalException, IOException {
		long userId = PrincipalThreadLocal.getUserId();
		User user = userLocalService.getUser(userId);
		if (user.getExpandoBridge().hasAttribute("SkilljarID")) {
			String userSkilljarID = user.getExpandoBridge().getAttribute("SkilljarID").toString();
			if ((userSkilljarID != null) && (!userSkilljarID.isEmpty())) {
				String api = "/users?user__email=" + user.getEmailAddress();
				String apiURL = "https://cors-anywhere.herokuapp.com/https://api.skilljar.com/v1/domains/educate.liferay.com/" + api;
				Http.Options options = new Http.Options();
				options.addHeader("Authorization", PortalUtil.getPortalProperties().getProperty("com.liferay.demo.skilljar.authorization"));
				options.setLocation(apiURL);
				return HttpUtil.URLtoString(options);
			}
			return userSkilljarID;
		}
		return "";
	}
	@Reference
	private UserLocalService userLocalService;
}