package com.bigff.blog.shiro;

import com.bigff.blog.entity.User;
import com.bigff.blog.entity.util.JwtUtils;
import com.bigff.blog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//
//@Component
//public class AccountRealm extends AuthorizingRealm {
//
//  @Autowired
//  JwtUtils jwtUtils;
//
//  @Autowired
//  UserService userService;
//
//  @Override
//  public boolean supports(AuthenticationToken token){
//    return token instanceof JwtToken;
//  }
//
//  @Override
//  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//    return null;
//  }
//
//  //获取token
//  @Override
//  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//    JwtToken jwtToken = (JwtToken) token;
//    String id =   jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
//
//    User user = userService.getById(Long.valueOf(id));
//
//    if (user==null){
//      throw new UnknownAccountException("账户不存在");
//    }
//    AccountProfile profile = new AccountProfile();
//    BeanUtils.copyProperties(user,profile);
//
//    return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
//  }
//}
import cn.hutool.core.bean.BeanUtil;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Shiro配置信息
 */
@Component
public class AccountRealm extends AuthorizingRealm {

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserService userService;

  @Override
  public boolean supports(AuthenticationToken token) {
    // 判断是否是JWT Token
    return token instanceof JwtToken;
  }

  /**
   * 授权认证
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    return null;
  }

  /**
   * 身份验证
   * @param token
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    JwtToken jwtToken = (JwtToken) token;

    String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
    User user = userService.getById(Long.valueOf(userId));
    if (user==null){
      throw new UnknownAccountException("account is not nonexistent");
    }

    AccountProfile accountProfile = new AccountProfile();
    BeanUtil.copyProperties(user,accountProfile);

    return new  SimpleAuthenticationInfo(accountProfile,jwtToken.getCredentials(),getName());
  }
}