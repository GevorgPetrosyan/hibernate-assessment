package com.egs.hibernate.assigment.rest.controller;


import com.egs.hibernate.assigment.core.service.UserService;
import com.egs.hibernate.assigment.data.transfer.response.CountryCodesAndCountOfUsersResponse;
import com.egs.hibernate.assigment.data.transfer.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
@Tag(name = "User Resource", description = "The User API with documentation annotations")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Generate users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users have been successfully generated")})
    @PostMapping("generate/{count}")
    public void initiateCountries(@Valid @NotNull @PathVariable int count){
        userService.generateUsers(count);
    }

    @Operation(summary = "Get users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")})
    @GetMapping("all")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Get users count with country codes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")})
    @GetMapping("count/country/{validateCount}")
    public ResponseEntity<List<CountryCodesAndCountOfUsersResponse>> getUsersCountByCountryCode(
            @Valid @NotBlank @PathVariable Long validateCount) {
        return ResponseEntity.ok(userService.getUsersCountByCountryCode(validateCount));
    }

    @Operation(summary = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been successfully created")})
    @PostMapping
    public void createUser(){
        userService.createUser();
    }
}
