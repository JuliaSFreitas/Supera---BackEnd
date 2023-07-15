package br.com.banco.persistence;

import java.sql.SQLException;
import java.util.List;

import br.com.banco.model.Transferencia;

public interface ITranferenciaDao {

	//Metodo que retorna todas as tranferências - após determinado tempo
	public List<Transferencia> consultaHistorico(String trans) throws SQLException, ClassNotFoundException;

	//Metodo que retorna  todas as tranferências de um determinado período
	public List<Transferencia> consultaPeriodo(String conta, String tran, String trans) throws SQLException, ClassNotFoundException;
		
	//Metodo que retorna  todas as tranferências de um determinado nome
	public List<Transferencia> consultaNome(String conta, String trans) throws SQLException, ClassNotFoundException;
	
	//Metodo que retorna  todas as tranferências de um determinado nome e período
	public List<Transferencia> consultaNomePeriodo(String conta, String nome, String tran, String trans) throws SQLException, ClassNotFoundException;
	
}
