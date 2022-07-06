package com.casestudy.dto;

import com.casestudy.model.Status;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInfoDetailDto {

    private BigDecimal amount;
    private String productName;
    private String billNo;
    private Status status;

}
