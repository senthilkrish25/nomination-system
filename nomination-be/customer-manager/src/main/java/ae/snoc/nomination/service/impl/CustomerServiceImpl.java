package ae.snoc.nomination.service.impl;

import ae.snoc.nomination.dto.CustomerDTO;
import ae.snoc.nomination.entity.CustomerEntity;
import ae.snoc.nomination.exception.CustomerNotFoundException;
import ae.snoc.nomination.repository.CustomerRepository;
import ae.snoc.nomination.service.CustomerService;
import ae.snoc.nomination.util.CustomerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Adds a new customer.
     *
     * @param customerDTO The DTO containing customer information.
     * @return The added customer DTO.
     * @throws CustomerNotFoundException If a customer with the same name already exists.
     */

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        log.info("Creating Customer: ");
        //Check if a customer with the same customerName already exists
        String customerName = customerDTO.getCustomerName();
        if (customerRepository.existsByCustomerName(customerName)) {
            log.error("Error adding customer.Customer is already present with name: {}", customerName);
            throw new CustomerNotFoundException(CustomerConstants.CUSTOMER_ALREADY_PRESENT + customerName);
        } else {
            CustomerEntity customerEntity = new CustomerEntity();
            BeanUtils.copyProperties(customerDTO, customerEntity);
            customerRepository.save(customerEntity);
            log.info("Customer added successfully: ");
            BeanUtils.copyProperties(customerEntity, customerDTO);
            return customerDTO;
        }
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id The unique identifier of the customer.
     * @return The customer DTO.
     * @throws CustomerNotFoundException If the customer with the given ID is not found.
     */
    @Override
    public CustomerDTO getCustomerById(Integer id) throws CustomerNotFoundException {
        log.info("Retrieving Customer by id");
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if (customerEntityOptional.isEmpty()) {
            log.error("Customer Id is wrong");
            throw new CustomerNotFoundException(CustomerConstants.CUSTOMER_NOT_FOUND_BY_ID + id);
        } else {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customerEntityOptional.get(), customerDTO);
            return customerDTO;
        }
    }

    /**
     * Retrieves a paginated list of all customers.
     *
     * @param activeCustomer
     * @param page           The page number.
     * @param pageSize       The number of items per page.
     * @return A page of customer DTOs.
     * @throws CustomerNotFoundException If there are no customers available.
     */
    @Override
    public Page<CustomerDTO> getAllCustomers(Boolean activeCustomer, final Integer page, final Integer pageSize) throws CustomerNotFoundException {
        log.info("Retrieving all the customers:");
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<CustomerEntity> customerEntityPage = customerRepository.findByIsActive(activeCustomer, pageable);
        if (!customerEntityPage.hasContent()) {
            log.error("No Active customers found:");
            throw new CustomerNotFoundException(CustomerConstants.NO_ACTIVE_CUSTOMERS_FOUND);
        } else {
            return customerEntityPage.map(customerEntity -> {
                CustomerDTO customerDTO = new CustomerDTO();
                BeanUtils.copyProperties(customerEntity, customerDTO);
                return customerDTO;
            });
        }
    }

    /**
     * Updates an existing customer.
     *
     * @param customerDTO The DTO containing the updated customer data.
     * @return The updated customer DTO.
     * @throws CustomerNotFoundException If the customer with the given ID is not found or another customer already exists with the same name.
     */

    @Override
    public CustomerDTO updateCustomer(final CustomerDTO customerDTO) throws CustomerNotFoundException {
        Integer customerId = customerDTO.getCustomerId();
        log.info("Customer is updating:");
        Optional<CustomerEntity> existingCustomerOptional = customerRepository.findById(customerId);
        if (existingCustomerOptional.isEmpty()) {
            log.error("Error updating customer.Customer is Empty:");
            throw new CustomerNotFoundException(CustomerConstants.CUSTOMER_NOT_FOUND_BY_ID + customerId);
        } else {
            CustomerEntity existingCustomerEntity = existingCustomerOptional.get();
            if (!existingCustomerEntity.getCustomerName().equals(customerDTO.getCustomerName())
                    && customerRepository.existsByCustomerName(customerDTO.getCustomerName())) {
                log.error("Error updating customer.Customer is already present with the same name: " + customerDTO.getCustomerName());
                throw new CustomerNotFoundException(CustomerConstants.CUSTOMER_ALREADY_PRESENT + customerDTO.getCustomerName());
            } else {
                BeanUtils.copyProperties(customerDTO, existingCustomerEntity);
                existingCustomerEntity.setCustomerId(customerId);
                customerRepository.save(existingCustomerEntity);
                BeanUtils.copyProperties(existingCustomerEntity, customerDTO);
                log.info("Customer updated successfully for ID: {}", customerId);
                return customerDTO;
            }
        }
    }

    /**
     * Deletes a customer by ID.
     *
     * @param customerId The unique identifier of the customer to be deleted.
     * @throws CustomerNotFoundException If the customer with the given ID is not found.
     */
    @Override
    public void deleteCustomer(Integer customerId) throws CustomerNotFoundException {
        log.info("Customer is getting deleted");
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerId);
        if (customerEntityOptional.isPresent()) {
            log.info("Customer deleted successfully for ID: " + customerId);
            CustomerEntity customerEntity = customerEntityOptional.get();
            customerEntity.setIsActive(false);
            customerRepository.save(customerEntity);
        } else {
            log.error("Error deleting customer for ID: " + customerId);
            throw new CustomerNotFoundException(CustomerConstants.CUSTOMER_NOT_FOUND_BY_ID + customerId);
        }
    }
}