package smoke;

import com.elipcero.classcustomerviewschool.repository.CustomerClassRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;
import static org.awaitility.Awaitility.await;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmokeTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ActiveProfiles(value = "smoke")
public class ClassCustomerEventTest {

    @Value("${stubrunner.url}") String stubRunnerUrl;

    @Autowired private MongoOperations mongoOperations;
    @Autowired private CustomerClassRepository customerClassRepository;

    @Autowired private ConfigurableEnvironment env;

    private RestTemplate restTemplate = new RestTemplate();

    private static final Log log = LogFactory.getLog(MethodHandles.lookup().lookupClass());

    @Test
    public void should_calculate_client_total_by_classrooom_and_set_class_by_client() {

        mongoOperations.dropCollection("CustomerClass");
        mongoOperations.dropCollection("ClassCustomerDayTotal");

        String url = this.stubRunnerUrl + "/triggers/CustomerRegistered";

        log.info("Mongo collections deletes");
        log.info("Url stub runner boot: " + url);

        ResponseEntity<Map> response = this.restTemplate.postForEntity(url, "", Map.class);
        then(response.getStatusCode().is2xxSuccessful()).isTrue();

        log.info("Triggered customer event");

        await().until( () ->
             customerClassRepository
                     .findById(1)
                     .map((c) -> c.getClasses().isEmpty())
                     .orElse(false)
        );
    }
}
