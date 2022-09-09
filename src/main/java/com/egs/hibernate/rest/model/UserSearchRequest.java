package com.egs.hibernate.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

/**
 * @author Arman Karapetyan
 * @date 2022-09-09
 **/

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserSearchRequest {

    private Integer pageNo;
    private Integer pageSize;
    private String sortBy;
    private String sortColumn;

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;

}
