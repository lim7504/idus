package com.example.idus.controller;

import com.example.idus.config.ResponseResult;
import com.example.idus.domain.dto.GetUserDto;
import com.example.idus.domain.dto.JoinUserDto;
import com.example.idus.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @PostMapping
    public ResponseEntity joinUser(@RequestBody @Validated JoinUserDto joinUserDto) {
        this.userService.joinUser(joinUserDto);
        return ResponseResult.ok().createResponseEntity();
    }

    @ApiOperation(value = "단일 회원 상세 정보 조회")
    @GetMapping("/{user-id}")
    public ResponseEntity getUserDetail(@PathVariable("user-id") Long userId) {
        GetUserDto result = this.userService.getUserDetail(userId);
        return ResponseResult.ok(result).createResponseEntity();
    }

    @ApiOperation(value = "여러 회원 목록 조회")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "이름"),
            @ApiImplicitParam(name = "email", value = "이메일")})
    @GetMapping
    public ResponseEntity getUserList(@RequestParam(name = "name", required = false) String name,
                                       @RequestParam(name = "email", required = false ) String email,
                                       Pageable pageable) {
        Page<GetUserDto> result = this.userService.getUserDtoPageList(name, email, pageable);
        return ResponseResult.ok(result).createResponseEntity();
    }
}
