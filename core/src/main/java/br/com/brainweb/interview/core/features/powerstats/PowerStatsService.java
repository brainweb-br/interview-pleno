package br.com.brainweb.interview.core.features.powerstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerStatsService {
	
	@Autowired
	private PowerStatsRepository powerStatsRepository;
}
