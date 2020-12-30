package com.it.favorites.controller.record;

import com.it.favorites.exception.AppException;
import com.it.favorites.exception.AppExceptionBadRequest;
import com.it.favorites.exception.AppExceptionServerError;
import com.it.favorites.exception.HttpResult;
import com.it.favorites.model.record.Record;
import com.it.favorites.service.record.RecordService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
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
        try {
            return new HttpResult(recordService.findAll());
        } catch (Exception e) {
            log.error(e.toString());
            throw new AppExceptionServerError("内部错误");
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public HttpResult save(@RequestBody Record record) {
        try {
            return new HttpResult(recordService.save(record));
        } catch (TransactionSystemException e) {
            log.error(e.toString());
            throw new AppExceptionBadRequest("参数错误");
        } catch (Exception e) {
            log.error(e.toString());
            throw new AppExceptionServerError("内部错误");
        }
    }

    @GetMapping("/show")
    public String sayHello(Model model) {
        model.addAttribute("favList", recordService.findAll());
        return "fav-list";
    }
}
