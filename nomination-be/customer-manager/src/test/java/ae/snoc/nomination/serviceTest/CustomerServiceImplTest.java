package ae.snoc.nomination.serviceTest;

import ae.snoc.nomination.dto.CustomerDTO;
import ae.snoc.nomination.entity.CustomerEntity;
import ae.snoc.nomination.exception.CustomerNotFoundException;
import ae.snoc.nomination.repository.CustomerRepository;
import ae.snoc.nomination.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("John Doe");
        when(customerRepository.existsByCustomerName("John Doe")).thenReturn(false);
        assertDoesNotThrow(() -> {
            CustomerDTO result = customerService.addCustomer(customerDTO);
            assertNotNull(result);
            assertEquals("John Doe", result.getCustomerName());
        });
        verify(customerRepository, times(1)).save(any());
    }
    @Test
    void testAddCustomerCustomerAlreadyExists() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("John Doe");
        when(customerRepository.existsByCustomerName("John Doe")).thenReturn(true);
        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.addCustomer(customerDTO);
        });
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testUpdateCustomer() {
        // Given
        Integer customerId = 1;
        String newCustomerName = "John Doe";
        // Mock existing customer
        CustomerEntity existingCustomerEntity = new CustomerEntity();
        existingCustomerEntity.setCustomerId(customerId);
        existingCustomerEntity.setCustomerName("Old Name");
        // Mock repository behavior
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomerEntity));
        when(customerRepository.existsByCustomerName(newCustomerName)).thenReturn(false);
        // Create a CustomerDTO with the updated data
        CustomerDTO updatedCustomerDTO = new CustomerDTO();
        updatedCustomerDTO.setCustomerName(newCustomerName);
        updatedCustomerDTO.setCustomerId(customerId);
        // When
        assertDoesNotThrow(() -> {
            CustomerDTO result = customerService.updateCustomer(updatedCustomerDTO);
            // Then
            assertNotNull(result);
            assertEquals(customerId, result.getCustomerId());
            assertEquals(newCustomerName, result.getCustomerName());
        });
        // Verify that the save method is called on the repository
        verify(customerRepository, times(1)).save(any());
    }

    @Test
    void testUpdateCustomerNotFound() {
        int customerId = 1;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("John Doe");
        customerDTO.setCustomerId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.updateCustomer(customerDTO);
        });
        verify(customerRepository, never()).save(any());
    }
}
