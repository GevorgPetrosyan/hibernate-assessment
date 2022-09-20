package com.egs.hibernate.rest;

import com.egs.hibernate.response.UserResponse;
import com.egs.hibernate.response.UsersCountResponse;
import com.egs.hibernate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
@Tag(name = "User Resource", description = "The User API with documentation annotations")
public class UserResource {
    private final UserService userService;

    @Operation(summary = "Generate users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users have been successfully generated")})
    @PostMapping("generate/{count}")
    public void generateUsers(@PathVariable int count) {
        userService.generateUsers(count);
    }

    @Operation(summary = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been successfully created")})
    @PostMapping
    public void createUser() {
        userService.createUser();
    }

    @Operation(summary = "Find users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users will have been received with pagination")})
    @GetMapping
    public List<UserResponse> findAll(@RequestParam("page") int page,
                                      @RequestParam("size") int size,
                                      @RequestParam("direction") String direction,
                                      @RequestParam("fieldName") String fieldName
    ) {
        return userService.findAll(page, size, direction, fieldName);
    }

    @GetMapping("count/{code}")
    @Operation(summary = "Find Users count by Country Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users count collecting by country code has successfully done")})
    public UsersCountResponse findCountByCode(@PathVariable("code") String code) {
        return userService.findUsersCountByCode(code);
    }
}
