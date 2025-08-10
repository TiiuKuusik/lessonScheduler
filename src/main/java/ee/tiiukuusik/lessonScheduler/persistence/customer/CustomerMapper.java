package ee.tiiukuusik.lessonScheduler.persistence.customer;

import ee.tiiukuusik.lessonScheduler.controller.customer.dto.CustomerDto;

import java.util.List;

public interface CustomerMapper {
    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    List<CustomerDto> toDtoList(List<Customer> customers);

    List<Customer> toEntityList(List<CustomerDto> customerDtos);
}
