package br.com.brainweb.interview.core.features.compareHeroes;

import br.com.brainweb.interview.model.DTO.ComparisonHeroesDTO;
import br.com.brainweb.interview.model.DTO.HeroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/compareHeroes")
public class compareHeroesController {

    @Autowired
    private CompareHeroesService compareHeroesService;

    /**
     * Endpoint de comparação de atributos de heróis
     * @param idHero1
     * @param idHero2
     * @return ResponseEntity
     */
    @GetMapping("/{id1}/{id2}")
    public ResponseEntity get (@PathVariable("id1") UUID idHero1, @PathVariable("id2") UUID idHero2){
        try{
            ComparisonHeroesDTO comparisonHeroesDTO = this.compareHeroesService.getComparison(idHero1, idHero2);
            return comparisonHeroesDTO != null ?
                    ResponseEntity.ok(comparisonHeroesDTO) :
                    ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
