package ae.snoc.nomination.service;

import ae.snoc.nomination.dto.CustomerDTO;
import ae.snoc.nomination.exception.CustomerNotFoundException;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerDTO addCustomer(final CustomerDTO customerEntityDTO);
    CustomerDTO getCustomerById(final Integer id) throws CustomerNotFoundException;
    Page<CustomerDTO> getAllCustomers(Boolean activeCustomer, final Integer page, final Integer pageSize) throws CustomerNotFoundException;
    CustomerDTO updateCustomer(final CustomerDTO updateCustomerEntityDTO) throws CustomerNotFoundException;
    void deleteCustomer(final Integer id) throws CustomerNotFoundException;

}

