package com.egs.hibernate.rest;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.service.CountryService;
import com.neovisionaries.i18n.CountryCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
@Tag(name = "Country Resource", description = "The Country API with documentation annotations")
public class CountryResource {
    private final CountryService countryServiceImpl;
    private final CountryRepository repository;

    @PostMapping("/init")
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
    public ResponseEntity<CountryCode> getCountryCode(@PathVariable("displayname") String displayName) {
        CountryCode countryCode = countryServiceImpl.getCountryCodeByDisplayName(displayName);
        return ResponseEntity.ok(countryCode);
    }

    @GetMapping("/codes")
    @Operation(summary = "Get country codes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country codes successfully gotten")})
    public ResponseEntity<List<CountryCode>> getCountryCodes() {
        return ResponseEntity.ok(countryServiceImpl.getCountryCodes());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Country>> getCountry() {
        return ResponseEntity.ok(repository.findAll());
    }
}
