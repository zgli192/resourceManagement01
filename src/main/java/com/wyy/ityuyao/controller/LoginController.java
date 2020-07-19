package com.wyy.ityuyao.controller;

import com.wyy.ityuyao.entity.User;
import com.wyy.ityuyao.service.UserService;
import com.wyy.ityuyao.util.DateUtil;
import com.wyy.ityuyao.util.ItdragonUtils;
import com.wyy.ityuyao.util.Result;
import com.wyy.ityuyao.util.ResultResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 用户登入登出接口
 *
 * @author 王钰尧
 * @create 2018/10/7 15:57
 * @since 1.0.0
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItdragonUtils itdragonUtils;

    private static final transient Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * 用户登录操作
     *
     * @param userName
     * @return
     */
    @ResponseBody
    @PostMapping("login.do")
    public ResultResponse login(String userName, String password) {
        log.info("^^^^^^^^^^^^^^^^^^^^ ITYao 登录信息:用户名: " + userName + "密码: " + password);
        try {
            Subject currentUser = SecurityUtils.getSubject();
            log.info("^^^^^^^^^^^^^^^^^^^^ ITYao 是否已通过认证" + currentUser.isAuthenticated());
            //验证用户是否被禁用
            User user = userService.getUserByUserName(userName);
            if (user == null) {
                return Result.resuleError("用户不存在");
            }
            if (user.getStatus() == 1) {
                if (!currentUser.isAuthenticated()) {
                    log.info("^^^^^^^^^^^^^^^^^^^^ ITYao 执行登录操作");
                    UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//	            token.setRememberMe(true);
                    try {
                        log.info("~~~~~~~~~~~~~Kelly");
                        // 执行登录,验证是否正确的关键点
                        currentUser.login(token);
                        log.info("^^^^^^^^^^^^^^^^^^^^ ITYao 登录成功");
                        //将登陆信息存储到session中
                        User loginUserInfo = new User();
                        //获取当前时间
                        String loginTime = DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
                        log.info("^^^^^^^^^^^^^^^^^^^^ ITYao 登陆时间为" + loginTime);
                        loginUserInfo.setUserName(userName);
                        loginUserInfo.setPlainPassword(password);
                        itdragonUtils.getShiroSession().setAttribute("loginUserInfo", loginUserInfo);
                        itdragonUtils.getShiroSession().setAttribute("userInfo", user);
                        //修改登陆时间
                        userService.updateLoginTime(loginUserInfo.getUserName(), loginTime);
                        return Result.resuleSuccess();
                    } catch (AuthenticationException ae) {
                        log.info("^^^^^^^^^^^^^^^^^^^^ ITYao 登录失败,账号密码不匹配: " + ae.getMessage());
                        return Result.resuleError("账号密码不匹配");
                    }
                }
            }
            return Result.resuleError("用户已被禁用;请联系管理员");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.resuleError(e.getMessage());
        }

    }

    /**
     * 登出
     *
     * @param session
     */
    @GetMapping("logout.do")
    public void logout(HttpSession session) {
        try {
            //退出
            SecurityUtils.getSubject().logout();
            log.info("^^^^^^^^^^^^^^^^^^^^ ITYao 登出成功");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}