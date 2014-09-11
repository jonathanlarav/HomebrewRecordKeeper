package com.homebrewrecordkeeper.controller;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.services.MaltRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MaltRecordController {
    @Autowired
    private MaltRecordService _maltRecordService;

    @RequestMapping(value = "/MaltRecords", method = RequestMethod.GET)
    public String listMaltRecord(ModelMap map)
    {
        map.addAttribute("maltRecord", new MaltRecordEntity());
        map.addAttribute("maltRecordList", _maltRecordService.getAll());

        return "maltRecordList";
    }
}
