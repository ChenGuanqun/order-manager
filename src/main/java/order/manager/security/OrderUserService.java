package order.manager.security;

import com.google.common.collect.Lists;
import order.manager.constant.OrderConstants;
import order.manager.dao.user.UserInfo;
import order.manager.dao.user.UserInfoQuery;
import order.manager.dao.user.UserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static order.manager.constant.OrderConstants.USER_STATUS_DISABLED;
import static order.manager.constant.OrderConstants.USER_STATUS_ENABLE;

/**
 * Created by tachen on 2018/2/11.
 */
@Component(value = "orderUserService")
public class OrderUserService implements UserDetailsService{

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoQuery query = new UserInfoQuery();
        query.setUserName(username);
        List<UserInfo> list = userInfoService.queryList(query);
        if(CollectionUtils.isEmpty(list)) {
           throw new UsernameNotFoundException("user not found:" + username);
        }

        UserInfo user = list.get(0);
        if(user.getStatus() == USER_STATUS_DISABLED) {
            throw new UsernameNotFoundException("user not found:" + username);
        }

        Role role = new Role();
        role.setUsername("chentao");
        role.setAuthority("ROLE_USER");
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
