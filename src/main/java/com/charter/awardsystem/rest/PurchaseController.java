package com.charter.awardsystem.rest;

import com.charter.awardsystem.model.dto.AwardResponse;
import com.charter.awardsystem.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/{customerId}/points")
    public ResponseEntity<AwardResponse> getAwardPointsByCustomerId(@PathVariable("customerId") Long customerId) {
        AwardResponse response = purchaseService.getAwardPointsByCustomerId(customerId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
