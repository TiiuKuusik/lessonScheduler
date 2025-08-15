package ee.tiiukuusik.lessonscheduler.service.customer;

import ee.tiiukuusik.lessonscheduler.controller.customer.dto.CustomerDto;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.DataNotFoundException;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.ForbiddenException;
import ee.tiiukuusik.lessonscheduler.persistence.booking.Booking;
import ee.tiiukuusik.lessonscheduler.persistence.booking.BookingRepository;
import ee.tiiukuusik.lessonscheduler.persistence.customer.Customer;
import ee.tiiukuusik.lessonscheduler.persistence.customer.CustomerMapper;
import ee.tiiukuusik.lessonscheduler.persistence.customer.CustomerRepository;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.error.Error;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final BookingRepository bookingRepository;


    public void addCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toCustomer(customerDto);
        customerRepository.save(customer);
    }
    
    public CustomerDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Error.CUSTOMER_DOES_NOT_EXIST.getMessage()));
        return customerMapper.toCustomerDto(customer);
    }
    
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toCustomerDtos(customers);
    }
    
    public void updateCustomer(Integer id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Error.CUSTOMER_DOES_NOT_EXIST.getMessage()));

        Customer updatedCustomer = customerMapper.toCustomer(customerDto);
        updatedCustomer.setId(existingCustomer.getId());
        
        customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(Integer id) {
        checkCustomerExists(id);
        checkActiveBookings(id);
        customerRepository.deleteById(id);
    }

    private void checkActiveBookings(Integer id) {
        // Check for active bookings
        List<Booking> customerBookings = bookingRepository.findByCustomerId(id);
        boolean hasActiveBookings = customerBookings.stream()
                .anyMatch(booking -> "pending".equals(booking.getStatus())
                        || "confirmed".equals(booking.getStatus()));

        if (hasActiveBookings) {
            throw new ForbiddenException(Error.CUSTOMER_HAS_ACTIVE_BOOKINGS.getMessage());
        }
    }

    private void checkCustomerExists(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new DataNotFoundException(Error.CUSTOMER_DOES_NOT_EXIST.getMessage());
        }
    }

}