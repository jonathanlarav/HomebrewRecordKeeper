package com.homebrewrecordkeeper.controller;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import com.homebrewrecordkeeper.service.MaltRecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MaltRecordController {
    @Autowired
    private MaltRecordManager maltRecordManager;

    @RequestMapping(value = "/maltRecords", method = RequestMethod.GET)
    public @ResponseBody List<MaltRecordEntity> maltRecords(ModelMap map) {
        return maltRecordManager.getAll();
    }
    @RequestMapping(value = "/maltRecords/{id}", method = RequestMethod.GET)
    public @ResponseBody MaltRecordEntity getMaltRecordById(@PathVariable("id") int id)
    {
        return maltRecordManager.getMaltRecordById(id);
    }
    @RequestMapping(value = "/maltRecords", method = RequestMethod.POST)
    public @ResponseBody MaltRecordEntity createMaltRecord(@RequestBody MaltRecordEntity maltRecordEntity)
    {
        return maltRecordManager.addMaltRecord(maltRecordEntity);
    }
    @RequestMapping(value = "/maltRecords/{id}", method = RequestMethod.DELETE)
    public @ResponseBody boolean deleteMaltRecord(@PathVariable("id") int id)
    {
        return maltRecordManager.deleteMaltRecord(id);
    }
    @RequestMapping(value = "/maltRecords/{id}", method = RequestMethod.PUT)
    public @ResponseBody MaltRecordEntity updateMaltRecord(@RequestBody MaltRecordEntity maltRecordEntity, @PathVariable("id") int id)
    {
        return maltRecordManager.updateMaltRecord(maltRecordEntity, id);
    }
}