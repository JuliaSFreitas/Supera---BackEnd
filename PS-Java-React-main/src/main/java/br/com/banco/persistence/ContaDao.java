package br.com.banco.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.banco.model.Conta;

//Estereótipo
@Repository
public class ContaDao implements IContaDao {

	//Injeção de dependência
	@Autowired
	GenericDao gDao;
	
	@Override
	public Conta abrirConta(String id_conta) throws SQLException, ClassNotFoundException {

		Connection c = gDao.getConnection();
		String sql = "SELECT id_conta, nome_responsavel FROM conta WHERE id_conta = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,id_conta);
		ResultSet rs = ps.executeQuery();
		
		Conta conta = new Conta();
		if (rs.next()) {
			
			conta.setId(rs.getInt("id_conta"));
			conta.setNomeResponsavel("nome_responsavel");
		}
		ps.execute();
		ps.close();
		c.close();
		
		return conta;
	}

}
