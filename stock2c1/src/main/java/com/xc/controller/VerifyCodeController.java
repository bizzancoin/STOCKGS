package com.xc.controller;

import com.google.code.kaptcha.Producer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping({"/code/"})
public class VerifyCodeController {
    private static final Logger log = LoggerFactory.getLogger(VerifyCodeController.class);

    @Autowired
    private Producer captchaProducer;

    @RequestMapping({"getCaptcha.do"})
    public ModelAndView getCaptcha() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("timestamp", Long.valueOf(System.currentTimeMillis()));
        mv.setViewName("test/test_code");
        return mv;
    }

    @RequestMapping({"getCode.do"})
    public ModelAndView getCode(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "timestamp", required = false) String timestamp) throws IOException {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isEmpty(timestamp)) {
            mv.addObject("timestamp", Long.valueOf(System.currentTimeMillis()));
        } else {
            mv.addObject("timestamp", timestamp);
        }
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = this.captchaProducer.createText();
        HttpSession session = request.getSession();
        session.removeAttribute("KAPTCHA_SESSION_KEY");
        session.setAttribute("KAPTCHA_SESSION_KEY", capText);
        BufferedImage bi = this.captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    //验证码
    @RequestMapping({"checkCode.do"})
    @ResponseBody
    public String checkCode(@RequestParam(value = "timestamp", required = false) String timestamp, @RequestParam(value = "code", required = false) String code, HttpServletRequest request) {
        boolean returnStr = false;
        HttpSession session = request.getSession();
        String original = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
        log.info("======用户输入的验证码：" + code);
        log.info("======正确的验证码：" + original);
        if (StringUtils.isNotEmpty(code) && code.equalsIgnoreCase(original)) {
            returnStr = true;
        }
        return returnStr + "";
    }
}
