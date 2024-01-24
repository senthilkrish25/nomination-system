package ae.snoc.nomination.controller;

import ae.snoc.nomination.dto.CustomerDTO;
import ae.snoc.nomination.exception.CustomerNotFoundException;
import ae.snoc.nomination.payload.ResponsePayload;
import ae.snoc.nomination.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Endpoint to create a new customer.
     *
     * @param customerDTO The DTO containing customer information.
     * @return ResponseEntity with a success message and created customer DTO.
     * @throws CustomerNotFoundException If there is an issue creating the customer.
     */

    @PostMapping
    @Operation(description = "Create Customers of SNOC")
    public ResponseEntity<ResponsePayload> createCustomer(@Valid @RequestBody final CustomerDTO customerDTO) throws CustomerNotFoundException {
        log.info("add customer endpoint is invoked");
        final CustomerDTO saveCustomerDto = customerService.addCustomer(customerDTO);
        return new ResponseEntity<>(ResponsePayload.create(saveCustomerDto, null, HttpStatus.CREATED, "Created"), HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve a paginated list of all customers.
     *
     * @param page     The page number.
     * @param pageSize The number of items per page.
     * @return ResponseEntity with a success message and a page of customer DTOs.
     * @throws CustomerNotFoundException If there are no customers available.
     */

    @GetMapping
    public ResponseEntity<ResponsePayload> getAllCustomers(
            @RequestParam(name = "activeCustomer", defaultValue = "true") final Boolean activeCustomer,
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "10") final Integer pageSize
    ) throws CustomerNotFoundException {
        log.info("getting all customer endpoint is invoked");
        final Page<CustomerDTO> customerDTOPage = customerService.getAllCustomers(activeCustomer, page, pageSize);
        return ResponseEntity.ok(ResponsePayload.create(customerDTOPage, null, HttpStatus.OK, "Success"));
    }

    /**
     * Endpoint to retrieve a customer by ID.
     *
     * @param id The unique identifier of the customer.
     * @return ResponseEntity with a success message and the customer DTO.
     * @throws CustomerNotFoundException If the customer with the given ID is not found.
     */

    @GetMapping("/byId/{id}")
    public ResponseEntity<ResponsePayload> getCustomerById(@PathVariable final Integer id) throws CustomerNotFoundException {
        log.info("Get Customer by id Service running");
        final CustomerDTO customerDTO = customerService.getCustomerById(id);
        return ResponseEntity.ok(ResponsePayload.create(customerDTO, null, HttpStatus.OK, "Success"));
    }

    /**
     * Endpoint to update an existing customer.
     *
     * @param customerDTO The DTO containing the updated customer data.
     * @return ResponseEntity with a success message and updated customer DTO.
     * @throws CustomerNotFoundException If there is an issue updating the customer.
     */

    @PutMapping
    public ResponseEntity<ResponsePayload> updateCustomer(@Valid @RequestBody final CustomerDTO customerDTO) throws CustomerNotFoundException {
        log.info("update customer endpoint is invoked");
        CustomerDTO saveCustomerDTO = customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok(ResponsePayload.create(saveCustomerDTO, null, HttpStatus.OK, "Updated"));
    }

    /**
     * Endpoint to delete a customer by ID.
     *
     * @param id The unique identifier of the customer to be deleted.
     * @return ResponseEntity with a success message.
     * @throws CustomerNotFoundException If the customer with the given ID is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePayload> deleteCustomer(@PathVariable final Integer id) throws CustomerNotFoundException {
        log.info("deleting customer endpoint is invoked");
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(ResponsePayload.create(null, null, HttpStatus.OK, "Customer Deleted successfully"), HttpStatus.OK);
    }
}