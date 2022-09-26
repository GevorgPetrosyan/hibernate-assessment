package com.egs.hibernate.rest;

import com.egs.hibernate.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{displayname}")
    @Operation(summary = "Get country code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country code successfully gotten")})
    public ResponseEntity<String> getCountryCode(@PathVariable("displayname") String displayName) {
        String countryCode = countryServiceImpl.getCountryCodeByDisplayName(displayName);
        return ResponseEntity.ok(countryCode);
    }

    @GetMapping
    @Operation(summary = "Get country codes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country codes successfully gotten")})
    public ResponseEntity<List<String>> getCountryCodes() {
        List<String> countryCodes = countryServiceImpl.getCountryCodes();
        return ResponseEntity.ok(countryCodes);
    }
}
