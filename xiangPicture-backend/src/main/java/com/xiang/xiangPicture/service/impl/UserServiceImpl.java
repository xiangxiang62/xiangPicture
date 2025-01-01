package com.xiang.xiangPicture.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiang.xiangPicture.domain.User;
import com.xiang.xiangPicture.service.UserService;
import com.xiang.xiangPicture.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 73148
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-01-01 14:26:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




