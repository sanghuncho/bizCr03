package com.dksys.biz.user.cr.cr03.service;

import java.util.List;
import java.util.Map;

public interface CR03Svc {
    public int selectEstCount(Map<String, String> param);
    public List<Map<String, Object>> selectEstList(Map<String, String> param);
    int deleteEst(Map<String, String> paramMap);
    Map<String, Object> updateEst(Map<String, String> paramMap);
}
