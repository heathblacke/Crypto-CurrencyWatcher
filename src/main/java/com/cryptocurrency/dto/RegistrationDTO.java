package com.cryptocurrency.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegistrationDTO {
    private String username;
    private Long id;
}