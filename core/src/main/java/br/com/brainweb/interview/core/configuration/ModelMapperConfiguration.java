package br.com.brainweb.interview.core.configuration;

import br.com.brainweb.interview.core.features.hero.model.input.HeroInput;
import br.com.brainweb.interview.model.Hero;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(HeroInput.class, Hero.class)
                .addMappings(mapper -> mapper.skip(Hero::setId));

        return modelMapper;
    }
}
