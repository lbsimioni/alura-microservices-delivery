package br.com.alura.microservices.delivery.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class VoucherDTO {
    private Long number;
    private LocalDate deliveryForecast;
}
