package com.becheer.donation.controller;

import com.becheer.donation.strings.ConstString;
import com.becheer.donation.utils.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* VerifyController 验证码控制器
* Creator : xiaokepu
* Date : 2017-09-15
*/

@Controller
@RequestMapping("/Verify")
public class VerifyController extends BaseController {

    @RequestMapping("/code.jpg")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        VerifyUtil verifyUtil = VerifyUtil.Instance();
        // 将验证码输入到session中，用来验证
        String verifyCode = verifyUtil.getString();
        request.getSession().setAttribute(ConstString.LOGIN_VERIFY_CODE, verifyCode);
        // 输出到web页面
        ImageIO.write(verifyUtil.getImage(), "jpg", response.getOutputStream());
    }
}
