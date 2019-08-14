package hhxy.dn.wph.interceptor;

import hhxy.dn.wph.entity.Resource;
import hhxy.dn.wph.entity.Role;
import hhxy.dn.wph.service.ResourceService;
import hhxy.dn.wph.service.RoleService;
import hhxy.dn.wph.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 11:22 2018/12/7
 * 该类的主要功能就是通过当前的请求地址，获取该地址需要的用户角色和权限
 */
@Component
public class UrlInterceptor implements FilterInvocationSecurityMetadataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlInterceptor.class);

    /**
     * 用来查询数据库中url pattern和role的对应关系
     */
    @Autowired
    ResourceService resourceService;

    @Autowired
    RoleService roleService;

    /**
     * 接收用户请求的地址，返回访问该地址需要的所有权限
     * @param object
     * @return java.util.Collection<org.springframework.security.access.ConfigAttribute>
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

//        CookieUtil.getCookieValue(object)
        //获取请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        LOGGER.info("requestUrl = {}",requestUrl);

        //获取请求资源
        Resource resource = resourceService.getResourceByUrl(requestUrl);
        if (resource == null){
            //如果没有匹配的url则说明大家都可以访问
            return SecurityConfig.createList("ROLE_NO");
        }
        //获取用户角色
        List<Role> roleList = roleService.listRoleByResourceId(resource.getId());
        int size = roleList.size();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = roleList.get(i).getNameZh();
        }
        return SecurityConfig.createList(values);

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
