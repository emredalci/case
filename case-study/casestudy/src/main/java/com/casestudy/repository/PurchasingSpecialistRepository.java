package com.casestudy.repository;

import com.casestudy.model.PurchasingSpecialist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasingSpecialistRepository extends JpaRepository<PurchasingSpecialist,Integer> {

    Optional<PurchasingSpecialist> findByEmail(String email);

}
