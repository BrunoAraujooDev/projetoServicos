package SoulCode.Services.Models;

public enum statusServico {
	
	RECEBIDO("Recebido"),
	ATRIBUIDO("Atribuido"),
	CONCLUIDO("Concluido"),
	ARQUIVADO("Arquivado");
	
	private String descricao;

	private statusServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}


}
