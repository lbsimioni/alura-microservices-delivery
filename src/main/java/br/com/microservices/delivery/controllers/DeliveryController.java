package br.com.microservices.delivery.controllers;

import br.com.microservices.delivery.dtos.DeliveryDTO;
import br.com.microservices.delivery.dtos.VoucherDTO;
import br.com.microservices.delivery.services.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
@Slf4j
public class DeliveryController {

    @Autowired
    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<VoucherDTO> reservationDelivery(
            @RequestBody final DeliveryDTO deliveryDTO,
            UriComponentsBuilder uriBuilder) {
        log.info("Reserving delivery: {}", deliveryDTO);
        var voucherDTO = deliveryService.reservationDelivery(deliveryDTO);
        log.info("Delivery reserved: {}", voucherDTO);

        var uri = uriBuilder.path("/deliveries/{number}").buildAndExpand(voucherDTO.getNumber()).toUri();

        return ResponseEntity.created(uri).body(voucherDTO);
    }

    @GetMapping("/{number}")
    public ResponseEntity<VoucherDTO> getVoucherByNumber(@PathVariable final Long number) {
        log.info("Getting voucher with number: {}", number);
        var voucherDTO = deliveryService.getVoucherByNumber(number);
        log.info("Getted voucher with number: {}", number);
        return ResponseEntity.ok(voucherDTO);
    }

}
