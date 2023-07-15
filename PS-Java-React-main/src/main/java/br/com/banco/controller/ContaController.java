package br.com.banco.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.banco.model.Conta;
import br.com.banco.persistence.ContaDao;

@Controller
public class ContaController {

	@Autowired
	ContaDao cDao;
	
	@RequestMapping(name = "conta", value = "/conta", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model, @RequestParam Map<String, String> params) {
		String id_conta = params.get("id_conta");
		String botao = params.get("botao");
		
		String erro = "";
		Conta conta  = validaCampos(params, botao, id_conta);;
		
		try {
			if(botao.equals("Entrar")) {
				if (conta != null){
					conta = cDao.abrirConta(id_conta);
					conta = new Conta();
				}
			} else {
				erro = "Preencha o campo corretamente";
			}	
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("botao", botao);
			model.addAttribute("conta", conta);
		}
		
		return new ModelAndView("conta");
	}
	
	@RequestMapping(name = "transferencia", value = "/transferencia", method = RequestMethod.POST)
	public ModelAndView conta(ModelMap model, @RequestParam Map<String, String> params) {
		String id_conta = params.get("id_conta");
		String botao = params.get("botao");
		
		String erro = "";
		Conta conta  = validaCampos(params, botao, id_conta);;
		
		try {
			if(botao.equals("Entrar")) {
				if (conta != null){
					conta = cDao.abrirConta(id_conta);
					conta = new Conta();
				}
			} else {
				erro = "Preencha o campo corretamente";
			}	
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("botao", botao);
			model.addAttribute("conta", conta);
		}
		
		return new ModelAndView("conta");
	}
	
	private Conta validaCampos(Map<String, String> params, String botao, String id_conta) {
		Conta c  = new Conta();
		
		if(botao.equals("Inserir")) {
			if(!params.get("id_conta").trim().isEmpty()){
				c.setId(Integer.parseInt(params.get("id_conta").trim()));
			}
		}
		return c;
	}
	
}
