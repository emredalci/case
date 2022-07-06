package com.casestudy.util;

import com.casestudy.dto.BillingInfoDetailDto;
import com.casestudy.model.BillingInfo;
import java.util.List;
import java.util.stream.Collectors;

public class BuildBillingInfoDetailDto {

    public static List<BillingInfoDetailDto> convertToBillingInfoDetailDtoList(List<BillingInfo> billingInfoList) {
        return billingInfoList.stream()
            .map(BuildBillingInfoDetailDto::buildBillingInfoDetailDto)
            .collect(Collectors.toList());
    }

    private static BillingInfoDetailDto buildBillingInfoDetailDto(BillingInfo billingInfo) {
        BillingInfoDetailDto billingInfoDetailDto = new BillingInfoDetailDto();
        billingInfoDetailDto.setProductName(billingInfo.getProductName());
        billingInfoDetailDto.setBillNo(billingInfo.getBillNo());
        billingInfoDetailDto.setStatus(billingInfo.getStatus());
        billingInfoDetailDto.setAmount(billingInfo.getAmount());
        return billingInfoDetailDto;
    }

}
