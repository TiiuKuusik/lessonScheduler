package ee.tiiukuusik.lessonscheduler.service.customer;

import ee.tiiukuusik.lessonscheduler.controller.customer.dto.CustomerDto;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.DataNotFoundException;
import ee.tiiukuusik.lessonscheduler.persistence.customer.Customer;
import ee.tiiukuusik.lessonscheduler.persistence.customer.CustomerMapper;
import ee.tiiukuusik.lessonscheduler.persistence.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public void addCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toCustomer(customerDto);
        customerRepository.save(customer);
    }
    
    public CustomerDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Customer not found with id: " + id));
        return customerMapper.toCustomerDto(customer);
    }
    
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toCustomerDtos(customers);
    }
    
    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Customer not found with id: " + id));
        
        // Update the existing customer with values from the DTO
        Customer updatedCustomer = customerMapper.toCustomer(customerDto);
        updatedCustomer.setId(existingCustomer.getId());
        
        Customer savedCustomer = customerRepository.save(updatedCustomer);
        return customerMapper.toCustomerDto(savedCustomer);
    }
    
    public void deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new DataNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}