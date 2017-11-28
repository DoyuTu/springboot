package com.doyutu.springbootswagger.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "测试Swagger")
@RequestMapping("/swagger/*")
public class Swagger {

    @GetMapping(value = "get")
    @ApiOperation(value = "get hello", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "msg", value = "内容", paramType = "query", dataType = "String", required = true)})
    @ApiResponse(code = 200, message = "aaaa")
    public String hello(@RequestParam String msg) {
        return "get swagger:" + msg;
    }

    @PostMapping("post")
    @ApiOperation(value = "post hello", httpMethod = "POST", hidden = false)
    @ApiImplicitParams({@ApiImplicitParam(name = "msg", value = "内容", dataType = "String", paramType = "form")})
    public String postHello(@RequestParam("msg") String msg) {
        return "post swagger：" + msg;
    }
}
