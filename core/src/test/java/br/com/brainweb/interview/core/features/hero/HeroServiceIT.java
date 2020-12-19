package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.enums.EnumRace;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import jdk.jfr.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.util.UUID;
import static br.com.brainweb.interview.model.Hero.builder;

@ActiveProfiles("it")
public class HeroServiceIT {
}
