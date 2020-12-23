package br.com.brainweb.interview.core.features.hero.model.view;

import java.util.Map;

import br.com.brainweb.interview.core.features.hero.model.HeroResumModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompareHerosView {

	private HeroResumModel firstHero;
	private HeroResumModel secondeHero;
	private Map<String, String> compareFirstToSecond;
	
}
