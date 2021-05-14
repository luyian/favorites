package com.it.favorites.controller.tools;

import com.it.favorites.exception.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/timestamp")
public class TimeToolsController {

    @GetMapping("/to-date")
    public HttpResult toDate(@RequestParam("timestamp") Long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new HttpResult(format.format(date));
    }

    @GetMapping("/to-timestamp")
    public HttpResult toTimeStamp(@RequestParam("datetime") String datetime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new HttpResult(parse.getTime());
    }
}
