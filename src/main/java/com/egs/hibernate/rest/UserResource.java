package com.egs.hibernate.rest;

import com.egs.hibernate.model.CountOfUsersByCountryCodeResponse;
import com.egs.hibernate.model.UserResponse;
import com.egs.hibernate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
    public void initiateCountries(@PathVariable int count){
        userService.generateUsers(count);
    }

    @Operation(summary = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been successfully created")})
    @PostMapping
    public void createUser(){
        userService.createUser();
    }


    @GetMapping("allUsers")
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "2") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<UserResponse> list = userService.getAllUsers(pageNo, pageSize, sortBy);
        return new ResponseEntity<Page<UserResponse>>(list, new HttpHeaders(), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Count of users by the country code have been successfully taken")})
    @GetMapping("countOfUsers")
    public ResponseEntity<List<CountOfUsersByCountryCodeResponse>> getCountOfUsersByCountryCode(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUsersByCountryCode());
    }
}
