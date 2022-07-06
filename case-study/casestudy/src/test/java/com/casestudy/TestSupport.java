package com.casestudy;

import com.casestudy.dto.BillingInfoDetailDto;
import com.casestudy.dto.BillingInfoRequestDto;
import com.casestudy.dto.BillingInfoResultDto;
import com.casestudy.model.BillingInfo;
import com.casestudy.model.PurchasingSpecialist;
import com.casestudy.model.Status;
import java.math.BigDecimal;
import java.util.List;

public class TestSupport {

    public BillingInfoRequestDto generateBillingInfoRequestDto(){
        return new BillingInfoRequestDto(
            "Emre",
            "Dalcı",
            "emredalc@gmail.com",
            BigDecimal.valueOf(50),
            "USB DISC",
            "TR000"
        );
    }

    public BillingInfoResultDto generateBillingInfoResultDto(){
        return new BillingInfoResultDto(
            Status.ACCEPT,
            "Your operation is succeed"
        );
    }

    public PurchasingSpecialist generatePurchasingSpecialist(){
        PurchasingSpecialist purchasingSpecialist = new PurchasingSpecialist();
        purchasingSpecialist.setFirstName("Emre");
        purchasingSpecialist.setLastName("Dalcı");
        purchasingSpecialist.setEmail("emredalc@gmail.com");

        BillingInfo billingInfo = new BillingInfo();
        billingInfo.setProductName("USB DISC");
        billingInfo.setAmount(BigDecimal.valueOf(50));
        billingInfo.setBillNo("TR000");
        billingInfo.setStatus(Status.ACCEPT);
        purchasingSpecialist.addBillingInfo(billingInfo);
        return purchasingSpecialist;
    }

    public BillingInfo generateBillingInfo(){
        BillingInfo billingInfo = new BillingInfo();
        billingInfo.setBillNo("TR000");
        billingInfo.setAmount(BigDecimal.valueOf(50));
        billingInfo.setProductName("USB DISC");
        billingInfo.setStatus(Status.ACCEPT);

        PurchasingSpecialist purchasingSpecialist = new PurchasingSpecialist();
        purchasingSpecialist.setEmail("emredalc@gmail.com");
        purchasingSpecialist.setFirstName("Emre");
        purchasingSpecialist.setLastName("Dalcı");
        purchasingSpecialist.addBillingInfo(billingInfo);
        billingInfo.setPurchasingSpecialist(purchasingSpecialist);
        return billingInfo;
    }

    public List<BillingInfoDetailDto> generateBillingInfoDetailDtoList(){
        BillingInfoDetailDto billingInfoDetailDto = new BillingInfoDetailDto();
        billingInfoDetailDto.setAmount(BigDecimal.valueOf(50));
        billingInfoDetailDto.setBillNo("TR000");
        billingInfoDetailDto.setStatus(Status.ACCEPT);
        billingInfoDetailDto.setProductName("USB DISC");
        return List.of(billingInfoDetailDto);
    }

}
