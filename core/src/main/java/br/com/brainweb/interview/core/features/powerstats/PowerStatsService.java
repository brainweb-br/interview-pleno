package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.DTO.HeroDTO;
import br.com.brainweb.interview.model.PowerStats;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerStatsService {

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    public PowerStats create(HeroDTO heroDTO){
        ModelMapper modelMapper = new ModelMapper();
        PowerStats powerStats =  modelMapper.map(heroDTO, PowerStats.class);
        powerStats.setValuesDefault();
        return this.powerStatsRepository.save(powerStats);
    }
}
