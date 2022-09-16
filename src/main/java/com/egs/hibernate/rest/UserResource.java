package com.egs.hibernate.rest;


import com.egs.hibernate.model.UserCountryResponseModel;
import com.egs.hibernate.model.UserFullResponseModel;
import com.egs.hibernate.model.UserResponseModel;
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
    public void initiateCountries(@PathVariable int count){
        userService.generateUsers(count);
    }

    @GetMapping("getAll")
    public ResponseEntity<Page<UserResponseModel>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "username") String sortBy
    ) {
        final Page<UserResponseModel> userResponseModelPage = userService.getAll(page, size, sortBy);

        return ResponseEntity.ok(userResponseModelPage);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<Page<UserFullResponseModel>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "username") String sortBy
    ) {

        return ResponseEntity.ok(userService.getAllUsers(page, size, sortBy));
    }

    @GetMapping("getCountOfUsersByCountry")
    public ResponseEntity<List<UserCountryResponseModel>> getCountOfUsersByCountry() {
        return ResponseEntity.ok(userService.getCountOfUsersByCountry());
    }

}
