package br.com.banco.model;

public class Transferencia {

	private int id;
	private String dataTransferencia;
	private double valor;
	private String tipo;
	private String nomeOperadorTransicao;
	private Conta idConta;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataTransferencia() {
		return dataTransferencia;
	}
	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNomeOperadorTransicao() {
		return nomeOperadorTransicao;
	}
	public void setNomeOperadorTransicao(String nomeOperadorTransicao) {
		this.nomeOperadorTransicao = nomeOperadorTransicao;
	}
	public Conta getIdConta() {
		return idConta;
	}
	public void setIdConta(Conta idConta) {
		this.idConta = idConta;
	}
	
	@Override
	public String toString() {
		return "Transferencia [id=" + id + ", dataTransferencia=" + dataTransferencia + ", valor=" + valor + ", tipo="
				+ tipo + ", nomeOperadorTransicao=" + nomeOperadorTransicao + ", idConta=" + idConta + "]";
	}
}
