package br.com.banco.model;

public class Conta {

	private int id;
	private String nomeResponsavel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	
	@Override
	public String toString() {
		return "Conta [id=" + id + ", nomeResponsavel=" + nomeResponsavel + "]";
	}
}
