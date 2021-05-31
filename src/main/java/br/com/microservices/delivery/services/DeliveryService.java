package br.com.microservices.delivery.services;

import br.com.microservices.delivery.dtos.DeliveryDTO;
import br.com.microservices.delivery.dtos.VoucherDTO;
import br.com.microservices.delivery.exceptions.ResourceNotFoundException;
import br.com.microservices.delivery.models.Delivery;
import br.com.microservices.delivery.repositories.DeliveryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DeliveryService {

    @Autowired
    private final DeliveryRepository repository;

    public VoucherDTO reservationDelivery(final DeliveryDTO deliveryDTO) {

        var delivery = repository.save(Delivery.builder()
                .searchDate(deliveryDTO.getSearchDate())
                .forecastDate(deliveryDTO.getForecastDate().plusDays(1L))
                .destinationAddress(deliveryDTO.getDestinationAddress())
                .originAddress(deliveryDTO.getOriginAddress())
                .requestId(deliveryDTO.getRequestId())
                .build());

        return convertDeliveryInVoucher(delivery);
    }

    public VoucherDTO getVoucherByNumber(final Long number) {
        return convertDeliveryInVoucher(repository.findById(number).orElseThrow(() -> {
            log.warn("Delivery not found with number: {}", number);
            throw new ResourceNotFoundException("Delivery not found");
        }));
    }

    private VoucherDTO convertDeliveryInVoucher(final Delivery delivery) {
        return VoucherDTO.builder()
                .number(delivery.getId())
                .deliveryForecast(delivery.getForecastDate())
                .build();
    }

}
