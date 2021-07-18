package br.com.brainweb.interview.core.features.hero;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("it")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeroControllerIT {

    @LocalServerPort
    int port;

    RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    void setup() {

    }

    @Test
    public void test() {
        var url = "http://localhost:" + port + "/heroes/hello/say";
        var result = restTemplate.getForEntity(url, String.class);
        System.out.println("Teeeesting");
        System.out.println(result);
    }

}
