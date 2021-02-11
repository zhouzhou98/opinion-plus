package com.fxys.opinion_plus.other;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class routerController {
    @GetMapping("/index")
    public String file() {
        return "index";
    }
}
