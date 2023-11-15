package com.wxmblog.reservation.controller;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wxmblog.reservation.service.UserToPersonService;


/**
 * 用户关联人员
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-08-18 15:28:26
 */
@RestController
@RequestMapping("reservation/usertoperson")
public class UserToPersonController {
    @Autowired
    private UserToPersonService userToPersonService;

}
