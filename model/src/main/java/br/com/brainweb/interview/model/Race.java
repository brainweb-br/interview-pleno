package br.com.brainweb.interview.model;

public enum Race {

	HUMAN(0, "HUMAN"), ALIEN(1, "ALIEN"), DIVINE(2, "DIVINE"), CYBORG(3, "CYBORG");

	private Integer id;

	private String descricao;

	private Race(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
