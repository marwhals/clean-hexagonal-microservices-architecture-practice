package payment.service.domain;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("payment.service.dataaccess")
@EntityScan("payment.service.dataaccess")
@SpringBootApplication
public class PaymentServiceApplication {
}
