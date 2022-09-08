package com.egs.hibernate.rest;


import com.egs.hibernate.model.UserFullResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import com.egs.hibernate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getModels")
    public ResponseEntity<Page<UserResponseModel>> getUserResponseModel(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "username") String sortBy
    ){
        Page<UserResponseModel> users = userService.getUserResponseModel(pageNumber, pageSize, sortBy);
        return ResponseEntity.status(HttpStatus.OK)
                .body(users);
    }

    @GetMapping("/getFullModels")
    public ResponseEntity<Slice<UserFullResponseModel>> getUserFullResponseModel(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "username") String sortBy
    ){
        Slice<UserFullResponseModel> users = userService.getUserFullResponseModel(pageNumber, pageSize, sortBy);
        return ResponseEntity.status(HttpStatus.OK)
                .body(users);
    }



}
