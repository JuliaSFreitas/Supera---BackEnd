package br.com.banco.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.com.banco.model.Transferencia;
import br.com.banco.persistence.TranferenciaDao;

@Controller
public class TransferenciaController {

	@Autowired
	TranferenciaDao tDao;
	
	@RequestMapping(name = "transferencia", value = "/transferencia", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model, @RequestParam Map<String, String> params) {
		
		String dFim = params.get("data_transferencia");
		String dInicio = params.get("data_transferencia");
		String nome = params.get("nome_operador_transacao");
		String id_conta = params.get("id_conta");
		String botao = params.get("botao");
		
		String erro = "";
		List<Transferencia> trans  =  new ArrayList<Transferencia>();
		Transferencia t  = new Transferencia();
		
		try {
			if(botao.equals("Pesquisar")) {
				if (trans != null && dInicio == null && dFim == null){
					trans = tDao.consultaNome(id_conta, nome);
					t  = new Transferencia();
				} else {
					if (trans != null && nome == null){
						trans = tDao.consultaPeriodo(id_conta, dInicio, dFim);
						t  = new Transferencia();
					} else {
						trans = tDao.consultaNomePeriodo(id_conta, nome, dInicio, dFim);
					}
				}
			} else {
				trans = tDao.consultaHistorico(id_conta);
			}	
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("botao", botao);
			model.addAttribute("trans", trans);
			model.addAttribute("t", t);
			model.addAttribute("id_conta", id_conta);
			model.addAttribute("nome", nome);
			model.addAttribute("dInicio", dInicio);
			model.addAttribute("dFim", dFim);
		}
		
		return new ModelAndView("transferencia");
	}
	
	@RequestMapping(name = "transferencia", value = "/transferencia", method = RequestMethod.POST)
	public ModelAndView transferencia(ModelMap model, @RequestParam Map<String, String> params) {
		
		String dFim = params.get("data_transferencia");
		String dInicio = params.get("data_transferencia");
		String nome = params.get("nome_operador_transacao");
		String id_conta = params.get("id_conta");
		String botao = params.get("botao");
		
		String erro = "";
		List<Transferencia> trans  =  new ArrayList<Transferencia>();
		Transferencia t  = new Transferencia();
		
		try {
			if(botao.equals("Pesquisar")) {
				if (trans != null && dInicio == null && dFim == null){
					trans = tDao.consultaNome(id_conta, nome);
					t  = new Transferencia();
				} else {
					if (trans != null && nome == null){
						trans = tDao.consultaPeriodo(id_conta, dInicio, dFim);
						t  = new Transferencia();
					} else {
						trans = tDao.consultaNomePeriodo(id_conta, nome, dInicio, dFim);
					}
				}
			} else {
				trans = tDao.consultaHistorico(id_conta);
			}	
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("botao", botao);
			model.addAttribute("trans", trans);
			model.addAttribute("t", t);
			model.addAttribute("id_conta", id_conta);
			model.addAttribute("nome", nome);
			model.addAttribute("dInicio", dInicio);
			model.addAttribute("dFim", dFim);
		}
		
		return new ModelAndView("transferencia");
	}
}
