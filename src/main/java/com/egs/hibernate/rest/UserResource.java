package com.egs.hibernate.rest;

import com.egs.hibernate.dto.UserDTO;
import com.egs.hibernate.rest.model.PaginationCriteria;
import com.egs.hibernate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Resource", description = "The User API with documentation annotations")
public class UserResource {
  private final UserService userService;

  @Operation(summary = "Generate users")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Users have been successfully generated")})
  @PostMapping("/generate/{count}")
  public void initiateCountries(@PathVariable int count) {
    userService.generateUsers(count);
  }

  @Operation(summary = "Find users")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Users are successfully found")})
  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll(@Valid PaginationCriteria paginationCriteria) {
    return ResponseEntity.ok(userService.findAll(paginationCriteria));
  }
}