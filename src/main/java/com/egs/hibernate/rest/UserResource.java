package com.egs.hibernate.rest;

import com.egs.hibernate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
  public void initiateUsers(@PathVariable int count) {
    log.info("Request for generating users.");
    userService.generateUsers(count);
  }
}
