package br.com.gubee.interview.core.features.powerstats.impl;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.PowerStats;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PowerStatsServiceImpl implements PowerStatsService {

    @Autowired
    PowerStatsRepository powerStatsRepository;

    @Override
    public PowerStats getById(UUID id) {
        return null;
    }

    @Override
    public PowerStats save(PowerStats powerStats) {
        return null;
    }

    @Override
    public PowerStats deleteById(UUID id) {
        return null;
    }
}
