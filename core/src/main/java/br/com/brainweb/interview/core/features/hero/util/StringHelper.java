package br.com.brainweb.interview.core.features.hero.util;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import br.com.brainweb.interview.core.features.hero.exception.GeneralFailureException;

public class StringHelper {
	
	public static UUID createUUID() {
		return UUID.randomUUID();
	}
	
	public static UUID createUUID(String id) {
		try {
			return UUID.fromString(id);
		} catch (Exception e) {
			throw GeneralFailureException.create(HttpStatus.BAD_REQUEST, "Erro ao tentar gerar uuid");
		}
	}
}
