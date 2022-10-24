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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
@Tag(name = "Country Resource", description = "The Country API with documentation annotations")
public class CountryResource {
    private final CountryService countryServiceImpl;

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

    @GetMapping("/get/update")
    @Operation(summary = "Get updated country codes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country codes successfully updated and gotten")})
    public ResponseEntity<List<CountryCode>> updateCountryCodes() {
        return ResponseEntity.ok(countryServiceImpl.updateCountryCodesInCache());
    }

    // todo delete
    //  method for test query level cache
    @GetMapping("/all")
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(countryServiceImpl.getAllCountries());
    }

    // todo delete
    //  method for test 2d level cache
    @GetMapping("/get/{id}")
    public ResponseEntity<Country> getCityById(@PathVariable(name = "id") Long id){
        return new ResponseEntity(countryServiceImpl.getCountryById(id), HttpStatus.OK);
    }
    @GetMapping("/get/manual")
    public ResponseEntity<String> getCacheName(){
        countryServiceImpl.getManualData();
        return new ResponseEntity("test", HttpStatus.OK);
    }
}
