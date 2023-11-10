package com.pan.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pan.demo.dto.UserDto;
import com.pan.demo.dto.UserQueryDto;
import com.pan.demo.entity.User;
import com.pan.demo.framework.exception.NotFoundException;
import com.pan.demo.mapper.UserMapper;
import com.pan.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author panjb
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserDto queryByUserId(String id) {
        User user = this.getById(id);
        if (user == null) {
            throw new NotFoundException("The user [" + id + "] not exists", "User Not Exist");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public List<UserDto> queryByPage(IPage<UserDto> page, UserQueryDto dto) {
        return this.baseMapper.selectByPage(page, dto);
    }
}
