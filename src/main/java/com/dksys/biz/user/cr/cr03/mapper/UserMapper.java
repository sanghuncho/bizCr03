package com.dksys.biz.user.cr.cr03.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import com.dksys.biz.user.cr.cr03.model.User;

@Mapper
public interface UserMapper {
    List<User> findAll();

    Optional<User> findById(Integer id);

    int deleteById(Integer id);

    int insert(User user);

    int update(User user);
}
