package com.egs.hibernate.rest;


import com.egs.hibernate.rest.model.user.UserCountWithCountryCodeResponse;
import com.egs.hibernate.rest.model.user.UserResponse;
import com.egs.hibernate.rest.model.user.UserSearchRequest;
import com.egs.hibernate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users have been successfully taken")})
    @PostMapping
    public ResponseEntity<Page<UserResponse>> getUsersWithSameCountryCode(@RequestBody UserSearchRequest userSearchRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAll(userSearchRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "taken users count and countryCode successfully")})
    @GetMapping("/getUsersCountWithCountryCode")
    public ResponseEntity<List<UserCountWithCountryCodeResponse>> getUsersCountWithCountryCode() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUsersCountWithCountryCode());
    }

}
