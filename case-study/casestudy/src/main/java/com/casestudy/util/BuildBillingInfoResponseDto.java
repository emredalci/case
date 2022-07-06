package com.casestudy.util;

import com.casestudy.dto.BillingInfoResultDto;
import com.casestudy.model.Status;

public class BuildBillingInfoResponseDto {

    public static BillingInfoResultDto buildBillingInfoResponseDto(Status status){
        if (status.equals(Status.ACCEPT)){
            return new BillingInfoResultDto(Status.ACCEPT, "Your operation is succeed");
        }
        return new BillingInfoResultDto(Status.REJECT,"Your operation is rejected");
    }

}
