package br.com.banco.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;

//Estereótipo
@Repository
public class TranferenciaDao implements ITranferenciaDao {

	//Injeção de dependência
	@Autowired
	GenericDao gDao;

	@Override
	public List<Transferencia> consultaHistorico(String conta) throws SQLException, ClassNotFoundException {

		List<Transferencia> transferencias = new ArrayList<Transferencia>();
		
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM fn_historico(?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,conta);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Transferencia t = new Transferencia();
			
			t.setDataTransferencia(rs.getString("data_transferencia"));
			t.setValor(rs.getDouble("valor"));
			t.setTipo(rs.getString("tipo"));
			t.setTipo(rs.getString("nome_operador_transacao"));
			
			Conta co = new Conta();
			co.setId(rs.getInt("id_conta"));
			t.setIdConta(co);
			
			transferencias.add(t);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		
		return transferencias;
	}

	public List<Transferencia> consultaPeriodo(String conta, String tran, String trans) throws SQLException, ClassNotFoundException {
		List<Transferencia> transferencias = new ArrayList<Transferencia>();
		
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM fn_periodo(?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,conta);
		ps.setString(2,trans);
		ps.setString(3,tran);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Transferencia t = new Transferencia();
			
			t.setDataTransferencia(rs.getString("data_transferencia"));
			t.setValor(rs.getDouble("valor"));
			t.setTipo(rs.getString("tipo"));
			t.setTipo(rs.getString("nome_operador_transacao"));
			
			Conta co = new Conta();
			co.setId(rs.getInt("id_conta"));
			t.setIdConta(co);
			
			transferencias.add(t);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		
		return transferencias;
	}

	public List<Transferencia> consultaNome(String id_conta, String nome) throws SQLException, ClassNotFoundException {
		List<Transferencia> transferencias = new ArrayList<Transferencia>();
		
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM fn_nome(?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,id_conta);
		ps.setString(2,nome);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Transferencia t = new Transferencia();
			
			t.setDataTransferencia(rs.getString("data_transferencia"));
			t.setValor(rs.getDouble("valor"));
			t.setTipo(rs.getString("tipo"));
			t.setTipo(rs.getString("nome_operador_transacao"));
			
			Conta co = new Conta();
			co.setId(rs.getInt("id_conta"));
			t.setIdConta(co);
			
			transferencias.add(t);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		
		return transferencias;
	}

	@Override
	public List<Transferencia> consultaNomePeriodo(String conta, String nome, String tran, String trans) throws SQLException, ClassNotFoundException {
		List<Transferencia> transferencias = new ArrayList<Transferencia>();
		
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM fn_nomeperiodo(?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,conta);
		ps.setString(2,nome);
		ps.setString(3,tran);
		ps.setString(4,trans);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Transferencia t = new Transferencia();
			
			t.setDataTransferencia(rs.getString("data_transferencia"));
			t.setValor(rs.getDouble("valor"));
			t.setTipo(rs.getString("tipo"));
			t.setTipo(rs.getString("nome_operador_transacao"));
			
			Conta co = new Conta();
			co.setId(rs.getInt("id_conta"));
			t.setIdConta(co);
			
			transferencias.add(t);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		
		return transferencias;
	}

}
