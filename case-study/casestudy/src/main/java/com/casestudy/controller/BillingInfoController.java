package com.casestudy.controller;

import com.casestudy.dto.BillingInfoDetailDto;
import com.casestudy.dto.BillingInfoRequestDto;
import com.casestudy.dto.BillingInfoResultDto;
import com.casestudy.service.BillingInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/bill")
@RequiredArgsConstructor
@Api
@SwaggerDefinition(tags = {
    @Tag(name = "Case Study Api", description = "Case Study Api")
})
public class BillingInfoController {

    private final BillingInfoService billingInfoService;

    @PostMapping
    @ApiOperation(value = "Insert Billing Info",
        notes = "This method insert billing info and returns response which include operation is accepted or rejected")
    public ResponseEntity<BillingInfoResultDto> insertBillingInfo(@RequestBody @Valid BillingInfoRequestDto billingInfoRequestDto) {
        return ResponseEntity.ok(billingInfoService.insertBillingInfo(billingInfoRequestDto));
    }

    @GetMapping("/accepted/{email}")
    @ApiOperation(value = "Get Bills Which Accepted", notes = "This method returns list of accepted bills")
    public ResponseEntity<List<BillingInfoDetailDto>> getBillsWithAccepted(@PathVariable String email) {
        return ResponseEntity.ok(billingInfoService.getBillingInfoListWithAccepted(email));
    }

    @GetMapping("/rejected/{email}")
    @ApiOperation(value = "Get Bills Which Not Accepted", notes = "This method returns list of not accepted bills")
    public ResponseEntity<List<BillingInfoDetailDto>> getBillsWithNotAccepted(@PathVariable String email) {
        return ResponseEntity.ok(billingInfoService.getBillingInfoListWithNotAccepted(email));
    }

}
