package com.casestudy.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@ToString(exclude = {"purchasingSpecialist"})
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class BillingInfo extends AbstractBaseEntity {

    private BigDecimal amount;

    private String productName;

    private String billNo;

    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchasing_specialist_id", referencedColumnName = "id")
    private PurchasingSpecialist purchasingSpecialist;

}
