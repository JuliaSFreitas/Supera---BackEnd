package br.com.banco;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class ReactView extends AbstractUrlBasedView {

    public ReactView() {
        setContentType("text/html");
    }

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Lógica de renderização do componente React
        // para renderizar o componente React em HTML e enviá-lo como resposta ao cliente
		
	}
}