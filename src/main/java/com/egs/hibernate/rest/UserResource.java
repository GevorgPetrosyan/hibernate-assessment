package com.egs.hibernate.rest;


import com.egs.hibernate.dto.UserDTO;
import com.egs.hibernate.dto.UsersCountDTO;
import com.egs.hibernate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public void initiateCountries(@PathVariable int count) { userService.generateUsers(count);
    }

    @Operation(summary = "Show users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users list have been successfully shown")})
    @GetMapping
    public ResponseEntity<Page<UserDTO>> filterUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "id") String columnName) {
        return ResponseEntity.ok(userService.usersFilter(pageNo, pageSize, columnName));
    }

    @Operation(summary = "Show users count by country code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users count list have been successfully shown")})
    @GetMapping("count")
    public ResponseEntity<List<UsersCountDTO>> usersCountByCountryCode(){
        return ResponseEntity.ok(userService.usersCountByCountryCode());
    }

@Operation(summary = "Show the countries with the highest concentration of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users count list have been successfully shown")})
    @GetMapping("cluster")
    public ResponseEntity<List<String>> usersCluster(){
        return ResponseEntity.ok(userService.usersCluster());
    }
}
