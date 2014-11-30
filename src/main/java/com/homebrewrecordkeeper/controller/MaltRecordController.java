package com.homebrewrecordkeeper.controller;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.service.MaltRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/maltRecords")
public class MaltRecordController {
    @Autowired
    private MaltRecordService maltRecordService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<MaltRecordEntity> maltRecords() {
        return maltRecordService.getAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody MaltRecordEntity getMaltRecordById(@PathVariable("id") int id)
    {
        return maltRecordService.getMaltRecordById(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody MaltRecordEntity createMaltRecord(@RequestBody MaltRecordEntity maltRecordEntity)
    {
        return maltRecordService.addMaltRecord(maltRecordEntity);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody boolean deleteMaltRecord(@PathVariable("id") int id)
    {
        return maltRecordService.deleteMaltRecord(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody MaltRecordEntity updateMaltRecord(@RequestBody MaltRecordEntity maltRecordEntity, @PathVariable("id") int id)
    {
        return maltRecordService.updateMaltRecord(maltRecordEntity, id);
    }
}