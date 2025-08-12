package ee.tiiukuusik.lessonscheduler.persistence.customer;

import ee.tiiukuusik.lessonscheduler.controller.customer.dto.CustomerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    Customer toCustomer(CustomerDto customerDto);

    CustomerDto toCustomerDto(Customer customer);

    List<CustomerDto> toCustomerDtos(List<Customer> customers);

    List<Customer> toCustomers(List<CustomerDto> customerDtos);
}
