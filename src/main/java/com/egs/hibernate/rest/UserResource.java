package com.egs.hibernate.rest;


import com.egs.hibernate.dto.UserDto;
import com.egs.hibernate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


  @Operation(summary = "Get all users")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get all users")})
  @GetMapping("")
  public ResponseEntity<List<UserDto>> getAll(@RequestParam int page, @RequestParam int size,@RequestParam String field) {
    return ResponseEntity.ok(userService.findAllUsers(page, size, field));
  }

}
