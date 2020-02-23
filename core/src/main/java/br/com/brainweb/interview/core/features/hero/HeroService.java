package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.DTO.HeroDTO;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {
    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerStatsService powerStatsService;

    /**
     * Método de criação de heróis
     * @param heroDTO
     * @return HeroDTO
     */
    public HeroDTO create(HeroDTO heroDTO){
        Optional<Hero> heroOptional = this.heroRepository.findByName(heroDTO.getName());
        if(heroOptional.isPresent()){
            return null;
        }

        PowerStats powerStats = this.powerStatsService.create(heroDTO);
        ModelMapper modelMapper = new ModelMapper();
        Hero hero =  modelMapper.map(heroDTO, Hero.class);
        hero.setValuesDefault(heroDTO, powerStats);
        hero = heroRepository.save(hero);
        return new HeroDTO(hero, hero.getPowerStats());
    }

    /**
     * Método de busca de heróis por id
     * @param id
     * @return HeroDTO
     */
    public HeroDTO get(UUID id){
        Optional<Hero> heroOptional = this.heroRepository.findById(id);
        if(heroOptional.isPresent()){
            HeroDTO heroDTO = new HeroDTO(heroOptional.get(), heroOptional.get().getPowerStats());
            return heroDTO;
        }

        return null;
    }

    /**
     * Método de busca de heróis por nome
     * @param name
     * @return HeroDTO
     */
    public HeroDTO get(String name){
        Optional<Hero> heroOptional = this.heroRepository.findByName(name);
        if(heroOptional.isPresent()){
            HeroDTO heroDTO = new HeroDTO(heroOptional.get(), heroOptional.get().getPowerStats());
            return heroDTO;
        }

        return null;
    }

    /**
     * Método de alteração de informações dos heróis
     * @param id
     * @param heroDTO
     * @return HeroDTO
     */
    public HeroDTO update(UUID id, HeroDTO heroDTO){
        Optional<Hero> heroOptional = this.heroRepository.findById(id);
        if(heroOptional.isEmpty()){
            return null;
        }

        PowerStats powerStats = this.powerStatsService.update(heroOptional.get().getPowerStats(), heroDTO);
        heroOptional.get().setPowerStats(powerStats);
        ModelMapper modelMapper = new ModelMapper();
        heroOptional.get().update(modelMapper.map(heroDTO, Hero.class), heroDTO);
        Hero hero = heroRepository.save(heroOptional.get());
        return new HeroDTO(hero, hero.getPowerStats());
    }

    /**
     * Método de remoção de heróis
     * @param id
     * @return boolean
     */
    public boolean delete(UUID id){
        Optional<Hero> heroOptional = this.heroRepository.findById(id);
        if(heroOptional.isEmpty()){
            return false;
        }

        this.heroRepository.delete(heroOptional.get());
        return true;

    }


}
