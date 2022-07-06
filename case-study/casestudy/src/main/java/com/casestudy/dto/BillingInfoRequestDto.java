package com.casestudy.dto;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInfoRequestDto {

    @ApiModelProperty(example = "John", dataType = "String")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @ApiModelProperty(example = "Doe", dataType = "String")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @ApiModelProperty(example = "john@doe.com", dataType = "String")
    @Email(message = "Invalid email address")
    private String email;

    @ApiModelProperty(example = "200", dataType = "BigDecimal")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount can not be less then zero")
    private BigDecimal amount;

    @ApiModelProperty(example = "USB DISC", dataType = "String")
    @NotBlank(message = "Product name is mandatory")
    private String productName;

    @ApiModelProperty(example = "TR000", dataType = "String")
    @NotBlank(message = "Bill no is mandatory")
    private String billNo;

}
