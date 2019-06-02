package hhxy.dn.wph.service.impl;

/**
 * @Author: 邓宁
 * @Date: Created in 20:51 2018/12/6
 */
//配置自定义用户服务
/*@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();
        //创建权限列表
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user =new User("admin","123456",authorities);

        return user;
    }
}*/
