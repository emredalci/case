package com.casestudy.repository;

import com.casestudy.model.BillingInfo;
import com.casestudy.model.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillingInfoRepository extends JpaRepository<BillingInfo,Integer> {


    @Query("SELECT DISTINCT b FROM BillingInfo b INNER JOIN b.purchasingSpecialist p WHERE p.email=?2 and b.status=?1")
    List<BillingInfo> findAllBillingInfoByStatusAndPurchasingSpecialistByEmail(Status status, String email);
}
