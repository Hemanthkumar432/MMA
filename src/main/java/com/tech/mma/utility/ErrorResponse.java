package com.tech.mma.utility;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse
{
    private String message;
    private int statusCode;
}
