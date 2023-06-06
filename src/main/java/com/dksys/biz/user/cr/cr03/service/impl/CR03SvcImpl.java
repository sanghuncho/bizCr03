package com.dksys.biz.user.cr.cr03.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dksys.biz.user.cr.cr03.mapper.CR03Mapper;
import com.dksys.biz.user.cr.cr03.service.CR03Svc;

@Service
@Transactional(rollbackFor = Exception.class)
public class CR03SvcImpl implements CR03Svc {
    @Autowired
    CR03Mapper cr03Mapper;
    
    @Override
    public int selectEstCount(Map<String, String> param) {
        return cr03Mapper.selectEstCount(param);
    }

    @Override
    public List<Map<String, Object>> selectEstList(Map<String, String> param) {
        return cr03Mapper.selectEstList(param);
    }

    @Override
    public int deleteEst(Map<String, String> paramMap) {
        int result = cr03Mapper.deleteEst(paramMap);
        result += cr03Mapper.deleteAllEstDetails(paramMap);
        return result;
    }

}
