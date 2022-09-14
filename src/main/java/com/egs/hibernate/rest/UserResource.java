package com.egs.hibernate.rest;


import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.model.UserCountryResponseModel;
import com.egs.hibernate.model.UserFullResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.service.UserService;
import com.neovisionaries.i18n.CountryCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
            @RequestParam(defaultValue = "3") String sortBy
    ) {
        final Page<UserResponseModel> userResponseModelPage = userService.getAll(page, size, sortBy);

        return ResponseEntity.ok(userResponseModelPage);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<Slice<UserFullResponseModel>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "3") String sortBy
    ) {
        final Slice<UserFullResponseModel> userFullResponseModelSlice = userService.getAllUsers(page, size, sortBy);

        return ResponseEntity.ok(userFullResponseModelSlice);
    }

    @GetMapping("getCountOfUsersByCountry")
    public ResponseEntity<Slice<UserCountryResponseModel>> getCountOfUsersByCountry(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "3") String sortBy
    ) {
        return ResponseEntity.ok(userService.getCountOfUsersByCountry(page, size));
    }

}
