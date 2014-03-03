package com.homebrewrecordkeeper.controller;

import com.homebrewrecordkeeper.entity.HopRecordEntity;
import com.homebrewrecordkeeper.service.HopRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/hopRecords")
public class HopRecordController {
    @Autowired
    private HopRecordService hopRecordService;

    @RequestMapping( method = RequestMethod.GET)
    public @ResponseBody
    List<HopRecordEntity> hopRecords() {
        return hopRecordService.getAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody HopRecordEntity getHopRecordById(@PathVariable("id") int id)
    {
        return hopRecordService.getHopRecordById(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody HopRecordEntity createHopRecord(@RequestBody HopRecordEntity hopRecordEntity)
    {
        return hopRecordService.addHopRecord(hopRecordEntity);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody boolean deleteHopRecord(@PathVariable("id") int id)
    {
        return hopRecordService.deleteHopRecord(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody HopRecordEntity updateHopRecord(@RequestBody HopRecordEntity hopRecordEntity, @PathVariable("id") int id)
    {
        return hopRecordService.updateHopRecord(hopRecordEntity, id);
    }
}
