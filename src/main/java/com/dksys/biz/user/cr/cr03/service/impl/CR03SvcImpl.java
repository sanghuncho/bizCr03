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
    public Map<String, Object> insertEstDetailList(Map<String, String> paramMap) {
        Map<String, Object> responseMap = new HashMap<>();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Type mapList = new TypeToken<ArrayList<Map<String, String>>>() {
        }.getType();
        List<Map<String, String>> detailList = gson.fromJson(paramMap.get("detailArr"), mapList);
        for (Map<String, String> estDetail : detailList) {
            cr03Mapper.insertEstDetailList(estDetail);
        }
        responseMap.put("resultCode",0);
        return responseMap;
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
            int count = cr03Mapper.estDetailCount4update(estDetail);
            if(count == 1) {
                cr03Mapper.updateEstDetailList(estDetail);
            } else if(count == 0) {
                cr03Mapper.insertEstDetailList(estDetail);
            } else {
                throw new IllegalArgumentException("insert/update failed:" + estDetail);
            }
        }
        
        Map<String, String> allDetailParam = new HashMap();
        allDetailParam.put("coCd", paramMap.get("coCd"));
        allDetailParam.put("salesCd", paramMap.get("salesCd"));
        List<Map<String, Object>> detailListDb = cr03Mapper.selectEstDetailList(allDetailParam);
        for(Map<String, Object> detailElement : detailListDb) {
            String bomSeq = (String) detailElement.get("bomSeq");
            boolean match = false;
            for (Map<String, String> estDetail : detailList) {
                String bomSeqUpdate = estDetail.get("bomSeq");
                match = bomSeq.equals(bomSeqUpdate) ? true : false;
            }
            
            if(!match) {
                Map<String, String> deleted = new HashMap();
                deleted.put("bomSeq", bomSeq);
                deleted.put("coCd", paramMap.get("coCd"));
                deleted.put("salesCd", paramMap.get("salesCd"));
                cr03Mapper.deleteEstDetailList(deleted);
            }
        }
        
        responseMap.put("resultCode",0);
        return responseMap;
    }
}
