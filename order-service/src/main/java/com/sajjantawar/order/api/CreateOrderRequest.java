package com.sajjantawar.order.api;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record CreateOrderRequest(@NotBlank @Size(max=80) String customerId,@NotNull @DecimalMin("0.01") @Digits(integer=17,fraction=2) BigDecimal totalAmount,@NotBlank @Pattern(regexp="[A-Z]{3}") String currency) {}