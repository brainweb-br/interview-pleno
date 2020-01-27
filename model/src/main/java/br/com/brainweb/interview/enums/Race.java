package br.com.brainweb.interview.enums;

import java.util.Arrays;
import java.util.List;

public enum Race {
	HUMAN("HUMAN"),
	ALIEN("ALIEN"),
	DIVINE("DIVINE"),
	CYBORG("CYBORG");

	public static final List<Race> ACCEPT_RACES = Arrays.asList(HUMAN,ALIEN,DIVINE,CYBORG);

	private String description;

	private Race(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
