package br.com.brainweb.interview.core.features.services;

import br.com.brainweb.interview.core.features.repositories.HeroRepository;
import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.entities.Hero;
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

        heroRepository.save(mapper.map(heroRequestDTO, Hero.class));
    }

    public HeroResponseDTO find(String uuid) {
        Optional<Hero> hero = heroRepository.findById(UUID.fromString(uuid));

        return mapper.map(hero.get(), HeroResponseDTO.class);
    }
}
