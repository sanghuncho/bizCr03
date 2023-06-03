package com.dksys.biz.user.cr.cr03.service;

import java.util.List;
import java.util.Optional;
import com.dksys.biz.user.cr.cr03.model.User;

public interface UserSvc {
    List<User> findAll();

    Optional<User> findById(Integer id);

    int deleteById(Integer id);

    int insert(User user);

    int update(User user);
}
