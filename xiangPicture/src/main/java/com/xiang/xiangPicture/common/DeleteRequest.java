package com.xiang.xiangPicture.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局删除请求类
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
