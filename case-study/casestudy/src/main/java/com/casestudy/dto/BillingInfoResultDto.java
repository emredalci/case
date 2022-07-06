package com.casestudy.dto;


import com.casestudy.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInfoResultDto {

    private Status status;
    private String message;

}
