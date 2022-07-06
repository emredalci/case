package com.casestudy.service;


import com.casestudy.dto.BillingInfoDetailDto;
import com.casestudy.dto.BillingInfoRequestDto;
import com.casestudy.dto.BillingInfoResultDto;
import com.casestudy.model.BillingInfo;
import com.casestudy.model.PurchasingSpecialist;
import com.casestudy.model.Status;
import com.casestudy.repository.BillingInfoRepository;
import com.casestudy.repository.PurchasingSpecialistRepository;
import com.casestudy.util.BuildBillingInfoDetailDto;
import com.casestudy.util.BuildBillingInfoResponseDto;
import com.casestudy.util.PurchasingSpecialistConverter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingInfoService {

    private final PurchasingSpecialistRepository purchasingSpecialistRepository;
    private final BillingInfoRepository billingInfoRepository;
    private final PurchasingSpecialistConverter converter;
    private final Logger log = LoggerFactory.getLogger(BillingInfoService.class);

    public synchronized BillingInfoResultDto insertBillingInfo(BillingInfoRequestDto request){ // for guaranteed data consistency
        log.info("Inserting billing info is started");

        Optional<PurchasingSpecialist> purchasingSpecialist = purchasingSpecialistRepository.findByEmail(request.getEmail());
        if (purchasingSpecialist.isEmpty()){
            return createPurchasingSpecialistAndInsertBillingInfo(request);
        }
        return insertBillingInfo(request, purchasingSpecialist.get());
    }

    public List<BillingInfoDetailDto> getBillingInfoListWithAccepted(String email){

        List<BillingInfo> acceptedBillingInfoList = billingInfoRepository.findAllBillingInfoByStatusAndPurchasingSpecialistByEmail(
            Status.ACCEPT, email);

        return BuildBillingInfoDetailDto.convertToBillingInfoDetailDtoList(acceptedBillingInfoList);
    }

    public List<BillingInfoDetailDto> getBillingInfoListWithNotAccepted(String email){

        List<BillingInfo> rejectedBillingInfoList = billingInfoRepository.findAllBillingInfoByStatusAndPurchasingSpecialistByEmail(
            Status.REJECT, email);

        return BuildBillingInfoDetailDto.convertToBillingInfoDetailDtoList(rejectedBillingInfoList);
    }


    private BillingInfoResultDto insertBillingInfo(BillingInfoRequestDto request,
        PurchasingSpecialist purchasingSpecialist) {

        BigDecimal sumOfAmount = purchasingSpecialist.getBillingInfoList()
            .stream()
            .map(BillingInfo::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BillingInfo billingInfo = converter.buildBillingInfo(request,sumOfAmount);
        purchasingSpecialist.addBillingInfo(billingInfo);

        purchasingSpecialistRepository.save(purchasingSpecialist);
        log.info("Billing info is inserted");
        return BuildBillingInfoResponseDto.buildBillingInfoResponseDto(billingInfo.getStatus());
    }

    private BillingInfoResultDto createPurchasingSpecialistAndInsertBillingInfo(BillingInfoRequestDto request) {
        PurchasingSpecialist purchasingSpecialist = converter.buildPurchasingSpecialist(request);
        purchasingSpecialistRepository.save(purchasingSpecialist);
        log.info("Billing info is inserted");
        return BuildBillingInfoResponseDto.buildBillingInfoResponseDto(purchasingSpecialist.getBillingInfoList().get(0).getStatus());
    }

}
