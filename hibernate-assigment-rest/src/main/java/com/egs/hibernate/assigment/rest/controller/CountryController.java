package com.egs.hibernate.assigment.rest.controller;

import com.egs.hibernate.assigment.core.service.CountryService;
import com.egs.hibernate.assigment.data.transfer.response.CountryCodeResponse;
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
@RequestMapping("/api/v1/country/")
@RequiredArgsConstructor
@Tag(name = "Country Resource", description = "The Country API with documentation annotations")
public class CountryController {

    private final CountryService countryService;

    @PostMapping("init")
    @Operation(summary = "Initialize countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Countries initialization is successfully done")})
    public void initiateCountries() {
        countryService.storeAllCountries();
    }

    @GetMapping("with/{validateCount}")
    @Operation(summary = "Get list of countries which have more than selected count users")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    public ResponseEntity<List<String>> countriesWhichUsers(
            @Valid @NotNull @PathVariable Long validateCount) {
        return ResponseEntity.ok(countryService.getAllByUsersCount(validateCount));
    }

    @GetMapping("available/all")
    @Operation(summary = "Get all by available country codes")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    public ResponseEntity<List<CountryCodeResponse>> getAllByCountryCodes() {
        return ResponseEntity.ok(countryService.getAllAvailableByCountryCodes());
    }

    @GetMapping("available/{displayName}")
    @Operation(summary = "Get country code by the display name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    public ResponseEntity<CountryCodeResponse> getByDisplayName(
           @Valid @NotBlank @PathVariable String displayName) {
        return ResponseEntity.ok(countryService.getByDisplayName(displayName));
    }
}
