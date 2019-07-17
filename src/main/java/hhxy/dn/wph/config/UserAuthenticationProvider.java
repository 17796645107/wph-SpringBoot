package hhxy.dn.wph.config;

import hhxy.dn.wph.service.UserService;
import hhxy.dn.wph.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *   @Author: 邓宁
 *   @Date: Created in 23:02 2019/7/2
 *   @Describe: 自定义登录认证
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //表单输入的用户名
        String username = (String) authentication.getPrincipal();
        //表单输入的密码
        String password = (String) authentication.getCredentials();

        //获取用户信息
        UserDetails userInfo = userService.loadUserByUsername(username);
        boolean matches = new BCryptPasswordEncoder().matches(MD5Util.getMD5(password), userInfo.getPassword());
        if (!matches) {
            throw new BadCredentialsException("The password is incorrect!!");
        }
        return new UsernamePasswordAuthenticationToken(username, userInfo.getPassword(), userInfo.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
