package br.com.brainweb.interview.core.features.services;

import br.com.brainweb.interview.core.features.repositories.PowerStatsRepository;
import br.com.brainweb.interview.model.entities.PowerStats;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerStatsService {
    @Autowired
    private PowerStatsRepository powerStatsRepository;

    private ModelMapper mapper = new ModelMapper();


    public void save(PowerStats ps) {

        powerStatsRepository.save(ps);
    }
}
