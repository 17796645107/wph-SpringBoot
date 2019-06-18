package hhxy.dn.wph.interceptor;

import hhxy.dn.wph.entity.Resource;
import hhxy.dn.wph.entity.Role;
import hhxy.dn.wph.service.ResourceService;
import hhxy.dn.wph.service.RoleService;
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
 */
/*该类的主要功能就是通过当前的请求地址，获取该地址需要的用户角色和权限*/
@Component
public class UserLoginInterceptor implements FilterInvocationSecurityMetadataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginInterceptor.class);

    //用来查询数据库中url pattern和role的对应关系
    @Autowired
    ResourceService resourceService;

    @Autowired
    RoleService roleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        LOGGER.info("requestUrl = {}",requestUrl);

        //注册/登录请求放行
        if ("/user/login".equals(requestUrl) || "/user/register".equals(requestUrl)) {
            return null;
        }
        Resource resource = resourceService.findResourceByUrl(requestUrl);
        if (resource == null){
            return SecurityConfig.createList("ROLE_no");
        }
        //获取用户角色
        List<Role> roles = roleService.findRolesByResourceId(resource.getId());
        int size = roles.size();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = roles.get(i).getNameZh();
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
