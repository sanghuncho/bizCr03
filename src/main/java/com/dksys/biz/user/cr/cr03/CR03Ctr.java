package com.dksys.biz.user.cr.cr03;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @DeleteMapping(value = "/deleteEst")
    public String deleteEst(@RequestBody Map<String, String> paramMap, ModelMap model) {
//        Map<String, Integer> paramMapT = new HashMap<String, Integer>();
//        paramMapT.put("fileTrgtKey", 9);
//        model.addAttribute("resultCode", cr03svc.deleteEst(paramMapT));
        
        model.addAttribute("resultCode", cr03svc.deleteEst(paramMap));
        model.addAttribute("resultMessage", messageUtils.getMessage("delete"));
        return "jsonView";
    }
}
