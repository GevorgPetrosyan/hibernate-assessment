package com.egs.hibernate.dto.response;


import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {


  private String username;

  private String firstName;

  private String lastName;

  private LocalDate birthdate;

  private Set<PhoneNumber> phoneNumbers;

  private Set<Address> addresses;

}
