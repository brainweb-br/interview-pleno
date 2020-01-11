package br.com.brainweb.interview.TO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroPowerTO {

	private Integer id;

	@Builder.Default
	private Boolean strength = false;

	@Builder.Default
	private Boolean agility = false;

	@Builder.Default
	private Boolean dexterity = false;

	@Builder.Default
	private Boolean intelligence = false;

}
