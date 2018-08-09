package smoke;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.elipcero.classcustomerviewschool.repository")
public class SmokeTestConfiguration {
}
