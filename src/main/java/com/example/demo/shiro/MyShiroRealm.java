
package com.example.demo.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.login.UserEntity;
import com.example.demo.service.login.UserService;
import com.example.demo.util.MathUtil;
import com.example.demo.util.StringUtil;

// 指定至少一个Realm 来实现认证（authentication）和/或授权（authorization） 
@Component
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 获取身份验证信息 Shiro中最终是通过Realm来获取应用程序中的用户、角色及权限信息
	 *
	 * @param authenticationToken 用户身份信息 token
	 * @return 返回封装了用户信息的 AuthenticationInfo 实例
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException { 
		// System.out.println("————身份认证方法————");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		char[] charpassword = token.getPassword();
		String password = "";
		for (int i = 0; i < charpassword.length; i++) {
			password = password+charpassword[i];
		}
		if(StringUtil.isNull(token.getUsername())) {
			return	null;
		}
		if(StringUtil.isNull(password)) {
			return	null;
		}
		UserEntity user = new UserEntity();
		user.setPassword(password);
		System.out.println(MathUtil.getMD5(password));
		System.out.println(MathUtil.getMD5(password));
		user.setEmail(token.getUsername());
		
		user = userService.findUserLogin(user);
		
		if(user == null) {
			throw new AccountException("账号密码错误");
		}
		return new SimpleAuthenticationInfo(user.getEmail(), user.getPassword(), getName());
	}

	/**
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) { //
		System.out.println("————权限认证————");
		//String username = (String) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(); 
		// 获得该用户角色
		//String role = userMapper.getRole(username);
		Set<String> set = new HashSet<>();
		// 需要将 role封装到 Set 作为 info.setRoles()的参数 // set.add(role); //设置该用户拥有的角色
		info.setRoles(set);
		return info;
	}
	
}
