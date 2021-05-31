package br.com.microservices.delivery.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeliveryDTO {
    private Long requestId;
    private LocalDate searchDate;
    private LocalDate forecastDate;
    private String originAddress;
    private String destinationAddress;
}
