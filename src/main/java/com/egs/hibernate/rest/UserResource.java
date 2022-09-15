package com.egs.hibernate.rest;


import com.egs.hibernate.dto.response.UserCountByCountryCode;
import com.egs.hibernate.dto.response.UserResponse;
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
    public void initiateCountries(@PathVariable int count) {
        userService.generateUsers(count);
    }


    @Operation(summary = "Find all users by pageable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users have been returned successfully")})
    @GetMapping
    public List<UserResponse> findAll(@RequestParam int page, @RequestParam int size, @RequestParam String field) {
        return userService.findAllUsers(page, size, field);
    }

    @Operation(summary = "Find all users count by CountryCode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users count have been returned successfully")})
    @GetMapping("/countryCode/{countryCode}")
    public UserCountByCountryCode findAllUsersByCountryCode(@PathVariable String countryCode) {
        return userService.findAllUsersByCountryId(countryCode);
    }
}
