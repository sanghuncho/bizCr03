package com.dksys.biz.user.cr.cr03.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CR03Mapper {
    int selectEstCount(Map<String, String> param);
    int selectEstDetailCount(Map<String, String> param);
    List<Map<String, Object>> selectEstList(Map<String, String> param);
    List<Map<String, Object>> selectEstDetailList(Map<String, String> param);
    Map<String, Object> insertEstDetailList(Map<String, String> param);
    int updateEstDetailList(Map<String, String> param);
    int deleteEst(Map<String, String> paramMap);
    int deleteAllEstDetails(Map<String, String> paramMap);
}
