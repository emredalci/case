package com.casestudy.model;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@ToString(exclude = "billingInfoList")
@Setter
public class PurchasingSpecialist extends AbstractBaseEntity{

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "purchasingSpecialist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<BillingInfo> billingInfoList;

    public void addBillingInfo(BillingInfo billingInfo){
        if (billingInfo == null){
            return;
        }
        if (billingInfoList == null){
            billingInfoList = new ArrayList<>();
        }
        billingInfo.setPurchasingSpecialist(this);
        billingInfoList.add(billingInfo);
    }

}
