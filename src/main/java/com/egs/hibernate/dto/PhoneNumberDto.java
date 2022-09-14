package com.egs.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Artur Tomeyan
 * @date 12/09/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberDto implements Serializable {

    private String phoneNumber;
}
