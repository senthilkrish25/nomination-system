package ae.snoc.nomination;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class CustomerManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerManagerApplication.class, args);
    }
}
