package com.egs.hibernate.rest;

import com.egs.hibernate.response.CountriesWithTenKUsersResponse;
import com.egs.hibernate.response.CountryResponse;
import com.egs.hibernate.service.CountryService;
import com.neovisionaries.i18n.CountryCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country/")
@RequiredArgsConstructor
@Tag(name = "Country Resource", description = "The Country API with documentation annotations")
public class CountryResource {
    private final CountryService countryService;

    @PostMapping("init")

    @Operation(summary = "Initialize countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Countries initialization is successfully done")})
    public void initiateCountries() {
        countryService.storeAllCountries();
    }

    @GetMapping("countries")
    @Operation(summary = "Find countries which have more than 10k users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received countries which have more than 10k users")})
    public CountriesWithTenKUsersResponse findCountriesWithTenKUsers() {
        return countryService.findCountriesWithTenKUsers();
    }

    @GetMapping("country/{displayName}")
    @Operation(summary = "Find country by display name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received country by display name")})
    public CountryResponse findCountryByDisplayName(
            @PathVariable("displayName") String displayName) {
        return countryService.getCountryCodeByDisplayName(displayName);
    }

    @GetMapping("code/")
    @Operation(summary = "Find all country codes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received all country codes")})
    public List<CountryCode> findAllCountryCodes() {
        return countryService.getAllCountries();
    }
}
