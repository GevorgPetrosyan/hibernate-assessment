package com.egs.hibernate.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@Data
public class ResponseHttp {

    @JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private Date timeStamp;
    private int codeStatus;
    private HttpStatus httpStatus;
    private String reason;
    private String errorMessage;

    public ResponseHttp(int codeStatus, HttpStatus httpStatus, String reason, String errorMessage) {
        this.timeStamp = new Date();
        this.codeStatus = codeStatus;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.errorMessage = errorMessage;
    }

    public static ResponseEntity<ResponseHttp> createBodyHttpResponse(HttpStatus status, String message) {
        ResponseHttp responseHttp = new ResponseHttp(status.value(), status, status.getReasonPhrase(), message.toUpperCase());
        return new ResponseEntity<>(responseHttp, status);
    }

}