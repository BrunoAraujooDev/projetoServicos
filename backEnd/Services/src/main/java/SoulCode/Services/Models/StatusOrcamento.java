package SoulCode.Services.Models;

public enum StatusOrcamento {
	
	EMITIDO("Emitido"),
	QUITADO("Quitado");
	
	private String descricao;

	private StatusOrcamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
