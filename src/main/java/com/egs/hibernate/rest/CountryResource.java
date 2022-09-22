package com.egs.hibernate.rest;

import com.egs.hibernate.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country/")
@RequiredArgsConstructor
@Tag(name = "Country Resource", description = "The Country API with documentation annotations")
public class CountryResource {
    private final CountryService countryServiceImpl;

    @PostMapping("init")
    @Operation(summary = "Initialize countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Countries initialization is successfully done")})
    public void initiateCountries() {
        countryServiceImpl.storeAllCountries();
    }

    @GetMapping("all/available/countryCodes")
    @Operation(summary = "get all countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get all country codes")})
    public ResponseEntity<List<String>> getAllCountryCode() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(countryServiceImpl.getAllCountryCodes());
    }

    @GetMapping("countryCode/{displayName}")
    @Operation(summary = "get country code by display name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get country code")})
    public ResponseEntity<String> getAllCountryCode(@PathVariable String displayName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(countryServiceImpl.getCountryCode(displayName));
    }

}
