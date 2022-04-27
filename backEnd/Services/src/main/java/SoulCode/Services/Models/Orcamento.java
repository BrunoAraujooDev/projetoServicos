package SoulCode.Services.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class Orcamento {
	
	@Id
	private Integer idOrcamento;
	
	@NumberFormat(pattern = "#.##0,00")
	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false)
	private String formaPagamento;
	
	@Enumerated(EnumType.STRING)
	private StatusOrcamento status;
	

	public Integer getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(Integer idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public StatusOrcamento getStatus() {
		return status;
	}

	public void setStatus(StatusOrcamento status) {
		this.status = status;
	}
	
	
	
	

}
