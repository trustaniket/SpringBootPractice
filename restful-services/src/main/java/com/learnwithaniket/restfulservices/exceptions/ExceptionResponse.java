package com.learnwithaniket.restfulservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ExceptionResponse {

    private Date timestamp;

    private String message;

    private String details;
}
