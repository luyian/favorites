package com.it.favorites.controller.record;

import com.it.favorites.exception.HttpResult;
import com.it.favorites.model.record.Record;
import com.it.favorites.service.record.RecordService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/record")
@Api(tags = "收藏记录")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/list")
    @ResponseBody
    public HttpResult getList() {
        return new HttpResult(recordService.findAll());
    }

    @PostMapping("/save")
    @ResponseBody
    public HttpResult save(@RequestBody Record record) {
        return new HttpResult(recordService.save(record));
    }

    @GetMapping("/show")
    public String sayHello(Model model) {
        model.addAttribute("favList", recordService.findAll());
        return "fav-list";
    }
}
