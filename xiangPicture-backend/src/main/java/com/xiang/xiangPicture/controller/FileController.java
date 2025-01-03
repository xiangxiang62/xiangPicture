package com.xiang.xiangPicture.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.xiangPicture.annotation.AuthCheck;
import com.xiang.xiangPicture.common.BaseResponse;
import com.xiang.xiangPicture.common.DeleteRequest;
import com.xiang.xiangPicture.common.ResultUtils;
import com.xiang.xiangPicture.constant.UserConstant;
import com.xiang.xiangPicture.exception.BusinessException;
import com.xiang.xiangPicture.exception.ErrorCode;
import com.xiang.xiangPicture.exception.ThrowUtils;
import com.xiang.xiangPicture.manage.CosManager;
import com.xiang.xiangPicture.model.dto.user.*;
import com.xiang.xiangPicture.model.entity.User;
import com.xiang.xiangPicture.model.vo.user.LoginUserVO;
import com.xiang.xiangPicture.model.vo.user.UserVO;
import com.xiang.xiangPicture.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.CodeSigner;
import java.util.List;

/**
 * @ClassName UserController
 * @Description
 * @Author xiangxiang
 * @Date 2025-01-2025/1/1-15:11
 * @Version 1.0
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {


    @Resource
    private CosManager cosManager;


    /**
     * 测试文件上传
     *
     * @param multipartFile
     * @return
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/test/upload")
    public BaseResponse<String> testUploadFile(@RequestPart("file") MultipartFile multipartFile) {
        // 文件目录
        String filename = multipartFile.getOriginalFilename();
        String filepath = String.format("/test/%s", filename);
        File file = null;
        try {
            // 上传文件
            file = File.createTempFile(filepath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filepath, file);
            // 返回可访问地址
            return ResultUtils.success(filepath);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filepath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("file delete error, filepath = {}", filepath);
                }
            }
        }
    }

}
