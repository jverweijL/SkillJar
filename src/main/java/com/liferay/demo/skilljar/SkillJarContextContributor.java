package com.liferay.demo.skilljar;

import com.liferay.portal.kernel.template.TemplateContextContributor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component(
        immediate = true,
        property = "type=" + TemplateContextContributor.TYPE_GLOBAL,
        service = TemplateContextContributor.class
)
public class SkillJarContextContributor implements TemplateContextContributor {
    @Override
    public void prepare(Map<String, Object> contextObjects, HttpServletRequest httpServletRequest) {
        contextObjects.put("skillJar", skillJarInvoker);
    }
    @Reference
    private SkillJarInvoker skillJarInvoker;
}