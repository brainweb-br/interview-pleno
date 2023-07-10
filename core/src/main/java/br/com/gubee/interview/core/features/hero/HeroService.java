package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.dto.ComparisonRequest;
import br.com.gubee.interview.dto.HeroComparison;
import br.com.gubee.interview.dto.HeroResponse;
import br.com.gubee.interview.model.Hero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface HeroService {

    ResponseEntity<HeroResponse> getById(UUID id);
    ResponseEntity<HeroResponse> getByName(String name);
    ResponseEntity save(HeroResponse hero);
    ResponseEntity deleteById(UUID id);
    ResponseEntity editHero(HeroResponse hero, UUID id);

    ResponseEntity<HeroComparison> compareHeroes(ComparisonRequest request);

}
