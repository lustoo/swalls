package com.stylefeng.guns.modular.system.auth.controller;

import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.modular.system.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.modular.system.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.modular.system.auth.util.JwtTokenUtil;
import com.stylefeng.guns.modular.system.auth.validator.IReqValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "simpleValidator")
    private IReqValidator reqValidator;

    @RequestMapping(value = "auth")
    public ResponseEntity<?> createAuthenticationToken(AuthRequest authRequest) {

        boolean validate = reqValidator.validate(authRequest);

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new GunsException(BizExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }
}
