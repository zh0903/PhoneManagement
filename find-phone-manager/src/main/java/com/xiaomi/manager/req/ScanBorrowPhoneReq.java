package com.xiaomi.manager.req;

import com.xiaomi.manager.validator.annotation.ISNumberValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author LYJ
 * @Description
 * @date 2021 年 10 月 28 日 上午9:59
 */
@Data
@ApiModel(value = "借入手机的参数")
public class ScanBorrowPhoneReq implements Serializable {
    private static final long serialVersionUID = 1L;


    @ISNumberValue(message = "借入者的id格式出错")
    @ApiModelProperty(name = "借入者的id")
    private Long userId;

    @ISNumberValue(message = "手机的id格式出错")
    @ApiModelProperty(name = "手机的id")
    private Long phoneId;

}
