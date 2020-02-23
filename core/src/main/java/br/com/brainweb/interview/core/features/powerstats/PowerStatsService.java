package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.DTO.HeroDTO;
import br.com.brainweb.interview.model.PowerStats;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class PowerStatsService {

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    /**
     * Método de criação dos atributos dos heróis
     * @param heroDTO
     * @return PowerStats
     */
    public PowerStats create(HeroDTO heroDTO){
        ModelMapper modelMapper = new ModelMapper();
        PowerStats powerStats =  modelMapper.map(heroDTO, PowerStats.class);
        powerStats.setValuesDefault();
        return powerStats;
    }

    /**
     * Método de alteração de informações dos atributos dos heróis
     * @param powerStats
     * @param heroDTO
     * @return PowerStats
     */
    public PowerStats update(PowerStats powerStats, HeroDTO heroDTO){
        ModelMapper modelMapper = new ModelMapper();
        powerStats.update(modelMapper.map(heroDTO, PowerStats.class));
        return powerStats;
    }
}
