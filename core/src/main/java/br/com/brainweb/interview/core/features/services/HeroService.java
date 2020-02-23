package br.com.brainweb.interview.core.features.services;

import br.com.brainweb.interview.core.features.repositories.HeroRepository;
import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.exeptions.HeroNotFoundException;
import br.com.brainweb.interview.model.exeptions.HeroWithNameAlreadyExistsException;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    private ModelMapper mapper = new ModelMapper();

    @Transactional
    public void save(HeroRequestDTO heroRequestDTO) {

        Hero hero = mapper.map(heroRequestDTO, Hero.class);
        heroRepository.findByName(hero.getName()).ifPresentOrElse(h -> heroRepository.save(h),
                () -> new HeroWithNameAlreadyExistsException(hero.getName()));

    }

    public HeroResponseDTO find(String uuid) {
        Optional<Hero> hero = heroRepository.findById(UUID.fromString(uuid));
        return hero.isPresent() ? mapper.map(hero.get(), HeroResponseDTO.class) : null;
    }

    public void delete(String uuid) {
        heroRepository.findById(UUID.fromString(uuid))
                .ifPresentOrElse(h -> heroRepository.delete(h),
                        () -> new HeroNotFoundException(uuid));
    }
}
