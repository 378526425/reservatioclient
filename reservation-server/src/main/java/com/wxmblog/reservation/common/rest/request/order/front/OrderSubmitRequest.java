package com.wxmblog.reservation.common.rest.request.order.front;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wxmblog.base.common.annotation.ForeignTable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-13 14:07
 **/

@Data
public class OrderSubmitRequest {

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    @NotNull(message = "请输入服务项目")
    @ForeignTable(value = "product")
    private Integer productId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Future(message = "必须为未来的时间")
    @NotNull(message = "请输入预约时间")
    private Date startTime;

    /**
     * 预约人姓名
     */
    @ApiModelProperty(value = "预约人姓名")
    @NotBlank(message = "请输入预约人姓名")
    private String contacts;

    /**
     * 联系手机号
     */
    @ApiModelProperty(value = "联系手机号")
    @NotBlank(message = "请输入联系手机")
    private String phone;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 人员id
     */
    @ApiModelProperty(value = "人员id")
    @ForeignTable(value = "person")
    @NotNull(message = "请选择服务人员")
    private Integer personId;

}
