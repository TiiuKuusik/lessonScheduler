package ee.tiiukuusik.lessonscheduler.controller.customer;

import ee.tiiukuusik.lessonscheduler.controller.customer.dto.CustomerDto;
import ee.tiiukuusik.lessonscheduler.service.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PostMapping("/customer")
    @Operation(summary = "Add a new customer", description = "Adds a new customer to the database")
    @ApiResponse(responseCode = "200", description = "Customer added successfully")
    public void addCustomer(@RequestBody @Valid CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
    }
    
    @GetMapping("/customer/{id}")
    @Operation(summary = "Get customer by ID", description = "Retrieves a customer by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer found"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public CustomerDto getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }
    
    @GetMapping("/customers")
    @Operation(summary = "Get all customers", description = "Retrieves all customers from the database")
    @ApiResponse(responseCode = "200", description = "List of customers retrieved successfully")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    
    @PutMapping("/customer/{id}")
    @Operation(summary = "Update customer", description = "Updates an existing customer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public CustomerDto updateCustomer(@PathVariable Integer id, @RequestBody @Valid CustomerDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }
    
    @DeleteMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete customer", description = "Deletes a customer by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }
}
