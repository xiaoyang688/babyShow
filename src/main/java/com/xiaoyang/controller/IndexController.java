package com.xiaoyang.controller;

import com.xiaoyang.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author xiaoyang
 * @create 2020/7/8 10:17 下午
 */
@Controller
public class IndexController {

    @Autowired
    private ImgService imgService;

    @GetMapping("/")
    public String index() {
        List<String> imageList = imgService.getImage();
        if (imageList.size() == 0) {
            return "error";
        }
        return "index";
    }

}
