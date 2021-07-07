package com.neosoft.controller;

import aj.org.objectweb.asm.ConstantDynamic;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

//@EnableOAuth2Sso
@RestController
public class OauthController {

    @RequestMapping("/github")
    public String msg(Principal principal) {

        return "Hi "+principal.getName()+" welcome to spring security application";
    }
}
