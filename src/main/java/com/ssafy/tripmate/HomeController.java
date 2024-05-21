package com.ssafy.tripmate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/oauth-login")
    public String oauthLogin() {
        return "oauth-login"; // 로그인 후 보여줄 뷰의 이름을 반환합니다. 뷰 템플릿 파일이 필요합니다.
    }

    @GetMapping("/oauth-login/login")
    public String loginPage() {
        return "login"; // 로그인 페이지 뷰의 이름을 반환합니다. 뷰 템플릿 파일이 필요합니다.
    }

    @GetMapping("/oauth-login/logout")
    public String logoutPage() {
        return "logout"; // 로그아웃 페이지 뷰의 이름을 반환합니다. 뷰 템플릿 파일이 필요합니다.
    }
}
