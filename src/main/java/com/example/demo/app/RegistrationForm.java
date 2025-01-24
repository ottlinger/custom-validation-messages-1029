package com.example.demo.app;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegistrationForm {
    @Size(min = 3, max = 90)
    @NotEmpty(message = "{NotEmpty.register.tenantname}")
    private String tenantname;

    @NotEmpty(message = "{NotEmpty.register.captcha}")
    private String captcha;
}
