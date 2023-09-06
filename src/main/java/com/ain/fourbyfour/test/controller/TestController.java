package com.ain.fourbyfour.test.controller;


import com.ain.fourbyfour.config.customAnnotation.ControllerReqLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping
    @ControllerReqLog
    public ResponseEntity connectionTest(){

        return ResponseEntity.ok("test");
    }
}
