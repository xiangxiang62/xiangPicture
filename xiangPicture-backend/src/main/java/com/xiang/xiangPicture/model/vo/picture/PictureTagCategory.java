package com.xiang.xiangPicture.model.vo.picture;

import lombok.Data;

import java.util.List;

/**
 * @ClassName PictureTagCategory
 * @Description
 * @Author xiangxiang
 * @Date 2025-01-2025/1/14-22:32
 * @Version 1.0
 */
@Data
public class PictureTagCategory {

    /**
     * 分类列表
     */
    private List<String> categoryList;

    /**
     * 标签列表
     */
    private List<String> tagList;

}
