package com.egs.hibernate.dto;

import lombok.Data;

@Data
public class AddressDTO {
  private String street;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String postalCode;
}
