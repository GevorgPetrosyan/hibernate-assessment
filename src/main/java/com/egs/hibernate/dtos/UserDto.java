package com.egs.hibernate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Artur Tomeyan
 * @date 08/09/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
}
