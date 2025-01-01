package com.xiang.xiangPicture.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserRegisterRequest
 * @Description
 * @Author xiangxiang
 * @Date 2025-01-2025/1/1-15:04
 * @Version 1.0
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     *用户密码
     */
    private String userPassword;

    /**
     *重复密码
     */
    private String checkPassword;

}
