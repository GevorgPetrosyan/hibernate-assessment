package com.egs.hibernate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersCountResponse {

   private String countryCode;
   private Integer count;

}
