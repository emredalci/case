package com.casestudy.util;

import com.casestudy.dto.BillingInfoRequestDto;
import com.casestudy.model.BillingInfo;
import com.casestudy.model.PurchasingSpecialist;
import com.casestudy.model.Status;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PurchasingSpecialistConverter {

    @Value("${price.limit}")
    private String limit;

    public PurchasingSpecialist buildPurchasingSpecialist(BillingInfoRequestDto request) {
        PurchasingSpecialist purchasingSpecialist = new PurchasingSpecialist();
        purchasingSpecialist.setFirstName(request.getFirstName());
        purchasingSpecialist.setLastName(request.getLastName());
        purchasingSpecialist.setEmail(request.getEmail());
        purchasingSpecialist.addBillingInfo(buildBillingInfo(request, BigDecimal.ZERO));
        return purchasingSpecialist;
    }

    public BillingInfo buildBillingInfo(BillingInfoRequestDto request, BigDecimal sumOfAmount) {
        BillingInfo billingInfo = new BillingInfo();
        billingInfo.setAmount(request.getAmount());
        billingInfo.setProductName(request.getProductName());
        billingInfo.setBillNo(request.getBillNo());
        buildStatus(request, sumOfAmount, billingInfo);
        return billingInfo;
    }

    private void buildStatus(BillingInfoRequestDto request, BigDecimal sumOfAmount, BillingInfo billingInfo) {
        BigDecimal sum = sumOfAmount.add(request.getAmount());
        if (sum.compareTo(BigDecimal.valueOf(Double.parseDouble(limit))) > 0) {
            billingInfo.setStatus(Status.REJECT);
        } else {
            billingInfo.setStatus(Status.ACCEPT);
        }
    }
}
