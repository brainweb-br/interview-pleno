package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PowerStatsService {

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    public String save(PowerStats powerStats) {

        return powerStatsRepository.saveAndUpdate(powerStats);
    }

    public PowerStats findById(UUID powerStatsId) {
        return powerStatsRepository.findById(powerStatsId).get();
    }

    public void delete(UUID id) {
        powerStatsRepository.delete(id);
    }
}
