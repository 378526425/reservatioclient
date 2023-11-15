package com.wxmblog.reservation.controller;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wxmblog.reservation.service.ProductToPersonService;


/**
 * 产品人员关联表
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-12 11:18:35
 */
@RestController
@RequestMapping("reservation/producttoperson")
public class ProductToPersonController {
    @Autowired
    private ProductToPersonService productToPersonService;

}
