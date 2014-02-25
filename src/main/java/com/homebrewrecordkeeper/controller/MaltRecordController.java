package com.homebrewrecordkeeper.controller;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.service.MaltRecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MaltRecordController {
    @Autowired
    private MaltRecordManager maltRecordManager;

    @RequestMapping(value = "/MaltRecords", method = RequestMethod.GET)
    public String listMaltRecord(ModelMap map)
    {
        map.addAttribute("maltRecord", new MaltRecordEntity());
        map.addAttribute("maltRecordList", maltRecordManager.getAll());

        return "maltRecordList";
    }
}
