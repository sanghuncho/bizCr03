package com.dksys.biz.user.cr.cr03;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.dksys.biz.user.cr.cr03.model.CR0302P01Model;
import com.dksys.biz.user.cr.cr03.model.CR0302P01ModelList;
import com.dksys.biz.user.cr.cr03.service.CR03Svc;
import com.dksys.biz.user.cr.cr03.util.MessageUtils;
import com.dksys.biz.user.cr.cr03.vo.PaginationInfo;

@Controller
@RequestMapping("/user/cr/cr03")
public class CR03Ctr {
    @Autowired
    CR03Svc cr03svc;
    
    @Autowired
    MessageUtils messageUtils;
    
    @PostMapping("/selectEstList")
    public String selectEstList(@RequestBody Map<String, String> param, ModelMap model) {
        int totalCnt = cr03svc.selectEstCount(param);
        System.out.println(totalCnt+" 총 로우");
        PaginationInfo paginationInfo = new PaginationInfo(param, totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        List<Map<String, Object>> estList = cr03svc.selectEstList(param);
        model.addAttribute("estList", estList);
        return "jsonView";
    }
    
    @PostMapping("/selectEstDetailList")
    public String selectEstDetailList(@RequestBody Map<String, String> param, ModelMap model) {
        int totalCnt = cr03svc.selectEstDetailCount(param);
        System.out.println(totalCnt+" 총 로우");
        PaginationInfo paginationInfo = new PaginationInfo(param, totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        List<Map<String, Object>> estList = cr03svc.selectEstDetailList(param);
        model.addAttribute("estList", estList);
        return "jsonView";
    }
    
    @DeleteMapping(value = "/deleteEst")
    public String deleteEst(@RequestBody Map<String, String> paramMap, ModelMap model) {
        model.addAttribute("resultCode", cr03svc.deleteEst(paramMap));
        model.addAttribute("resultMessage", messageUtils.getMessage("delete"));
        return "jsonView";
    }
    
    @PostMapping(value = "/updateEstDetail")
    public String updateEst(@RequestBody Map<String, String> paramMap, ModelMap model) {
        System.out.println(paramMap.get("detailArr"));
        try {
            Map<String, Object> updateEstMap =  cr03svc.updateEstDetailList(paramMap);
            model.addAttribute("resultCode", updateEstMap.get("resultCode"));
            model.addAttribute("resultMessage", messageUtils.getMessage("update"));
            model.addAttribute("param", updateEstMap );
        }catch(Exception e) {
            model.addAttribute("resultCode", 500);
            model.addAttribute("resultMessage", messageUtils.getMessage("fail"));
        }
        return "jsonView";
    }
    
    @PostMapping(value = "/insertEstDetail")
    public String insertEstDetailList(@RequestParam Map<String, String> paramMap, ModelMap model) {
        try {
            Map<String, Object> newEstMap  = cr03svc.insertEstDetailList(paramMap);
            System.out.println(newEstMap+"최종");
            model.addAttribute("resultCode", 200);
            model.addAttribute("resultMessage", messageUtils.getMessage("insert"));
            model.addAttribute("param", newEstMap );
        }catch(Exception e) {
            model.addAttribute("resultCode", 500);
            model.addAttribute("resultMessage", e.getLocalizedMessage());
        }
        return "jsonView";
    }
}
