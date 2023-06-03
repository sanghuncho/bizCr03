package com.dksys.biz.user.cr.cr03.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dksys.biz.user.cr.cr03.mapper.UserMapper;
import com.dksys.biz.user.cr.cr03.model.User;
import com.dksys.biz.user.cr.cr03.service.UserSvc;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserSvcImpl implements UserSvc {
    @Autowired
    UserMapper userMapper;
    
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

}
