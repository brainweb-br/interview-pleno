package br.com.brainweb.interview.core.features.repositories;

import br.com.brainweb.interview.model.entities.PowerStats;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PowerStatsRepository extends PagingAndSortingRepository<PowerStats, UUID> {
    PowerStats findByAgility(Integer agility);
}
