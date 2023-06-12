package com.dksys.biz.user.cr.cr03.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dksys.biz.user.cr.cr03.mapper.CR03Mapper;
import com.dksys.biz.user.cr.cr03.service.CR03Svc;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
    public int selectEstDetailCount(Map<String, String> param) {
        return cr03Mapper.selectEstDetailCount(param);
    }

    @Override
    public List<Map<String, Object>> selectEstList(Map<String, String> param) {
        return cr03Mapper.selectEstList(param);
    }
    @Override
    public List<Map<String, Object>> selectEstDetailList(Map<String, String> param) {
        return cr03Mapper.selectEstDetailList(param);
    }
    @Override
    public Map<String, Object> insertEstDetailList(Map<String, String> param) {
        Map<String, String> estDetail = new HashMap<String, String>();
        estDetail.put("coCd", param.get("coCd"));
        estDetail.put("salesCd", param.get("salesCd"));
        estDetail.put("bomSeq", param.get("bomSeq"));
        estDetail.put("unitNo", param.get("unitNo"));
        estDetail.put("revNo", param.get("revNo"));
        return cr03Mapper.insertEstDetailList(param);
    }

    @Override
    public int deleteEst(Map<String, String> paramMap) {
        int result = cr03Mapper.deleteEst(paramMap);
        result += cr03Mapper.deleteAllEstDetails(paramMap);
        return result;
    }

    @Override
    public Map<String, Object> updateEstDetailList(Map<String, String> paramMap) {
        Map<String, Object> responseMap = new HashMap<>();
        
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Type mapList = new TypeToken<ArrayList<Map<String, String>>>() {
        }.getType();
        List<Map<String, String>> detailList = gson.fromJson(paramMap.get("detailArr"), mapList);
        for (Map<String, String> estDetail : detailList) {
            cr03Mapper.updateEstDetailList(estDetail);
        }
        responseMap.put("resultCode",0);
        return responseMap;
    }
}
