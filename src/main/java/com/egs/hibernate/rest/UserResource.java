package com.egs.hibernate.rest;

import com.egs.hibernate.dto.UserByCountryDto;
import com.egs.hibernate.dto.UserDto;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")})
    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam(defaultValue = "username") String sortBy) {
        return userService.getAllUsers(pageNo, pageSize, sortBy);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")})
    @GetMapping("count")
    public List<UserByCountryDto> count(){
        return userService.getCountOfUsersByCountry();
    }
}