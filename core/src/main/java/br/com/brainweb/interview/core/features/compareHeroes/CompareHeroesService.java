package br.com.brainweb.interview.core.features.compareHeroes;

import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.model.DTO.ComparisonHeroesDTO;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompareHeroesService {
    @Autowired
    private HeroRepository heroRepository;

    /**
     * Método de retorno da comparação dos heróis
     * @param idHero1
     * @param idHero2
     * @return ComparisonHeroesDTO
     */
    public ComparisonHeroesDTO getComparison(UUID idHero1, UUID idHero2){
        Optional<Hero> heroOptional1 = this.heroRepository.findById(idHero1);
        Optional<Hero> heroOptional2 = this.heroRepository.findById(idHero2);

        if(heroOptional1.isEmpty() || heroOptional2.isEmpty()){
            return null;
        }

        return this.toCompare(heroOptional1.get(), heroOptional2.get());
    }

    /**
     * Método que realiza a comporação entre os heróis
     * @param hero1
     * @param hero2
     * @return ComparisonHeroesDTO
     */
    private ComparisonHeroesDTO toCompare(Hero hero1, Hero hero2){
        ComparisonHeroesDTO comparisonHeroesDTO = ComparisonHeroesDTO.create(hero1, hero2);
        return comparisonHeroesDTO;
    }


}
