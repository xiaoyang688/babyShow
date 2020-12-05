package com.xiaoyang.controller;

import com.xiaoyang.dto.ResultApi;
import com.xiaoyang.service.RequestService;
import com.xiaoyang.util.LocalCache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaoyang
 * @create 2020/10/10 9:12 下午
 */
@RestController
public class UpdateSessionController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/update/{sessionId}")
    public ResultApi img(@PathVariable("sessionId") String session) {
        try {
            requestService.saveSession(session);
            return new ResultApi();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultApi(500, "更新失败");
        }
    }

}
