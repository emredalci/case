package com.casestudy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.casestudy.dto.BillingInfoRequestDto;
import com.casestudy.dto.BillingInfoResultDto;
import com.casestudy.exception.PurchasingSpecialistNotFoundException;
import com.casestudy.model.BillingInfo;
import com.casestudy.model.PurchasingSpecialist;
import com.casestudy.repository.BillingInfoRepository;
import com.casestudy.repository.PurchasingSpecialistRepository;
import com.casestudy.service.BillingInfoService;
import com.casestudy.util.PurchasingSpecialistConverter;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BillingInfoServiceTest extends TestSupport {

    private PurchasingSpecialistRepository purchasingSpecialistRepository;
    private BillingInfoRepository billingInfoRepository;
    private PurchasingSpecialistConverter converter;
    private BillingInfoService billingInfoService;

    @BeforeEach
    void setUp() {
        purchasingSpecialistRepository = Mockito.mock(PurchasingSpecialistRepository.class);
        billingInfoRepository = Mockito.mock(BillingInfoRepository.class);
        converter = Mockito.mock(PurchasingSpecialistConverter.class);
        billingInfoService = new BillingInfoService(purchasingSpecialistRepository, billingInfoRepository, converter);
    }

    @Test
    void Should_CreatePurchasingSpecialistAndInsertBillingInfo_When_PurchasingSpecialistDoesNotExist() {
        //given
        BillingInfoRequestDto billingInfoRequestDto = generateBillingInfoRequestDto();
        PurchasingSpecialist purchasingSpecialist = generatePurchasingSpecialist();
        BillingInfoResultDto expected = generateBillingInfoResultDto();
        //when
        Mockito.when(purchasingSpecialistRepository.findByEmail(billingInfoRequestDto.getEmail())).thenReturn(Optional.empty());
        Mockito.when(converter.buildPurchasingSpecialist(billingInfoRequestDto)).thenReturn(purchasingSpecialist);
        Mockito.when(purchasingSpecialistRepository.save(purchasingSpecialist)).thenReturn(purchasingSpecialist);
        //then
        BillingInfoResultDto result = billingInfoService.insertBillingInfo(billingInfoRequestDto);
        assertEquals(expected, result);
        Mockito.verify(purchasingSpecialistRepository).findByEmail(billingInfoRequestDto.getEmail());
        Mockito.verify(converter).buildPurchasingSpecialist(billingInfoRequestDto);
        Mockito.verify(purchasingSpecialistRepository).save(purchasingSpecialist);
    }

    @Test
    void Should_InsertBillingInfo_When_PurchasingSpecialistExist() {
        //given
        BillingInfoRequestDto billingInfoRequestDto = generateBillingInfoRequestDto();
        PurchasingSpecialist purchasingSpecialist = generatePurchasingSpecialist();
        BillingInfo billingInfo = generateBillingInfo();
        BigDecimal sumOfAmount = BigDecimal.valueOf(50);
        BillingInfoResultDto expected = generateBillingInfoResultDto();
        //when
        Mockito.when(purchasingSpecialistRepository.findByEmail(billingInfoRequestDto.getEmail())).thenReturn(Optional.of(purchasingSpecialist));
        Mockito.when(converter.buildBillingInfo(billingInfoRequestDto, sumOfAmount)).thenReturn(billingInfo);
        Mockito.when(purchasingSpecialistRepository.save(purchasingSpecialist)).thenReturn(purchasingSpecialist);
        //then
        BillingInfoResultDto result = billingInfoService.insertBillingInfo(billingInfoRequestDto);
        assertEquals(expected, result);
        Mockito.verify(purchasingSpecialistRepository).findByEmail(billingInfoRequestDto.getEmail());
        Mockito.verify(converter).buildBillingInfo(billingInfoRequestDto, sumOfAmount);
        Mockito.verify(purchasingSpecialistRepository).save(purchasingSpecialist);

    }


}
