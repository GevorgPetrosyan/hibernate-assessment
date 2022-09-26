package com.egs.hibernate.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private int statusCode;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;
}
