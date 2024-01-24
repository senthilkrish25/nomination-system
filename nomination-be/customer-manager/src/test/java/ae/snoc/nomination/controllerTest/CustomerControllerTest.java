package ae.snoc.nomination.controllerTest;

import ae.snoc.nomination.controller.CustomerController;
import ae.snoc.nomination.dto.CustomerDTO;
import ae.snoc.nomination.exception.CustomerNotFoundException;
import ae.snoc.nomination.payload.ResponsePayload;
import ae.snoc.nomination.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testCreateUserSuccess() {
        //Integer customerId = 1;
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.addCustomer(customerDTO)).thenReturn(customerDTO);
        ResponseEntity<ResponsePayload> responseEntity = customerController.createCustomer(customerDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(customerDTO, responseEntity.getBody().getData());
        verify(customerService, times(1)).addCustomer(customerDTO);
    }
    @Test
    void testUpdateCustomer() throws CustomerNotFoundException {
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.updateCustomer(customerDTO)).thenReturn(customerDTO);
        ResponseEntity<ResponsePayload> responseEntity = customerController.updateCustomer(customerDTO);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getTimeStamp());
        assertEquals(customerDTO, responseEntity.getBody().getData());
        assertNull(responseEntity.getBody().getError());
        assertEquals("Updated", responseEntity.getBody().getMessage());
        verify(customerService, times(1)).updateCustomer(customerDTO);
    }

    @Test
    void testGetAllCustomers() throws CustomerNotFoundException {
        Integer page = 0;
        Integer pageSize = 10;
        Page<CustomerDTO> customerDTOPage = mock(Page.class);
        Boolean activeCustomer = false;
        when(customerService.getAllCustomers(activeCustomer, page, pageSize)).thenReturn(customerDTOPage);
        ResponseEntity<ResponsePayload> responseEntity = customerController.getAllCustomers(false, page, pageSize);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getTimeStamp());
        assertEquals(customerDTOPage, responseEntity.getBody().getData());
        assertNull(responseEntity.getBody().getError());
        assertEquals("Success", responseEntity.getBody().getMessage());
        verify(customerService, times(1)).getAllCustomers(activeCustomer, page, pageSize);
    }
    @Test
    void testGetCustomerById() throws CustomerNotFoundException {
        Integer customerId = 1;
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.getCustomerById(customerId)).thenReturn(customerDTO);
        ResponseEntity<ResponsePayload> responseEntity = customerController.getCustomerById(customerId);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getTimeStamp());
        assertEquals(customerDTO, responseEntity.getBody().getData());
        assertNull(responseEntity.getBody().getError());
        assertEquals("Success", responseEntity.getBody().getMessage());
        verify(customerService, times(1)).getCustomerById(customerId);
    }
    @Test
    void testDeleteCustomer() throws CustomerNotFoundException {
        Integer customerId = 1;
        ResponseEntity<ResponsePayload> responseEntity = customerController.deleteCustomer(customerId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}

