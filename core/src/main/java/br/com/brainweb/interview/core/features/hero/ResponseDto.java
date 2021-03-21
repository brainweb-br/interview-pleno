package br.com.brainweb.interview.core.features.hero;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResponseDto {

	private List<String> messages;
	
	public ResponseDto() {
		messages = new ArrayList<String>();
	}
	
	public static ResponseDto create() {
		return new ResponseDto();
	}
	
	public void addMessage(String message) {
		this.messages.add(message);
	}
}
