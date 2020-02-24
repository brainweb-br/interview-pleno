package br.com.brainweb.interview.core.features.services;

import br.com.brainweb.interview.core.features.repositories.HeroRepository;
import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.dtos.response.PowerStatsDifferenceDTO;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.exeptions.HeroNotFoundException;
import br.com.brainweb.interview.model.exeptions.NameAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toCollection;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerStatsService powerStatsService;

    private ModelMapper mapper = new ModelMapper();

    @Transactional
    public void save(HeroRequestDTO heroRequestDTO) {

        boolean exists = isNameExists(heroRequestDTO.getName());

        if (!exists) {
            // powerStatsService.save(mapper.map(heroRequestDTO.getPowerStats(), PowerStats.class));
            heroRepository.save(mapper.map(heroRequestDTO, Hero.class));
        } else {
            throw new NameAlreadyExistsException(heroRequestDTO.getName());
        }

    }


    @Transactional
    public void update(String uuid, HeroRequestDTO heroRequestDTO) {
        Hero heroById = fetchFromDB(UUID.fromString(uuid));
        //if (heroById != null) {
        Hero hero = mapper.map(heroRequestDTO, Hero.class);
        hero.setId(UUID.fromString(uuid));
        // powerStatsService.save(hero.getPowerStats());
        heroRepository.save(hero);
        //}
    }

    @Transactional
    public void delete(String uuid) {

        heroRepository.delete(fetchFromDB(UUID.fromString(uuid)));
    }

    public HeroResponseDTO find(String uuid) {
        return mapper.map(fetchFromDB(UUID.fromString(uuid)), HeroResponseDTO.class);
    }

    public List<HeroResponseDTO> find(Optional<String> name) {

        return name.isEmpty()
                ? StreamSupport.stream(heroRepository.findAll().spliterator(), false).
                map(h -> mapper.map(h, HeroResponseDTO.class)).
                collect(toCollection(ArrayList::new))
                :
                fetchFromDB(name.get()).map(lh -> lh.stream().
                        map(h -> mapper.map(h, HeroResponseDTO.class)).
                        collect(toCollection(ArrayList::new))).
                        orElseThrow(() -> new HeroNotFoundException(name.get()));
    }


    public PowerStatsDifferenceDTO calculateDifference(String firstHeroUUID, String secondHeroUUID) {

        PowerStats firstHeroPS = fetchFromDB(UUID.fromString(firstHeroUUID)).getPowerStats();
        PowerStats secondHeroPS = fetchFromDB(UUID.fromString(secondHeroUUID)).getPowerStats();


        PowerStatsDifferenceDTO differenceDTO = new PowerStatsDifferenceDTO();

        differenceDTO.setFirstHeroUUID(UUID.fromString(firstHeroUUID));
        differenceDTO.setSecondHeroUUID(UUID.fromString(secondHeroUUID));
        differenceDTO.setAgility(firstHeroPS.getAgility() - secondHeroPS.getAgility());
        differenceDTO.setDexterity(firstHeroPS.getDexterity() - secondHeroPS.getDexterity());
        differenceDTO.setIntelligence(firstHeroPS.getIntelligence() - secondHeroPS.getIntelligence());
        differenceDTO.setStrength(firstHeroPS.getStrength() - secondHeroPS.getStrength());

        return differenceDTO;

    }

    private Hero fetchFromDB(UUID uuid) {
        return heroRepository.findById(uuid)
                .orElseThrow(() -> new HeroNotFoundException(uuid.toString()));
    }

    private Optional<List<Hero>> fetchFromDB(String name) {
        return heroRepository.findByName(name);
    }

    private boolean isNameExists(String name) {
        Optional<List<Hero>> heroes = fetchFromDB(name);
        if (heroes.isPresent()) {
            return heroes.get().stream()
                    .anyMatch(h -> h.getName().equals(name));
        }
        return false;
    }


}
