package br.com.banco;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.context.annotation.Bean;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "br.com.banco")
public class WebConfig implements WebMvcConfigurer {

	//Configuração de Inicialização Web
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//adicionar o .css na pasta no caminho: "/main/resources/"
		registry.addResourceHandler("/resources/**").addResourceLocations("/main/resources/"); //torna o css padrão
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//o "index" é a tela inicial padrão
		registry.addViewController("/").setViewName("forward:/static/index.html"); //chamada inicial a View
	}

	// Metodo que retorna um "Bean" que configura a camada de visão
	@Bean
	public ViewResolver internalResoucerViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(ReactView.class); //colocar a classe da View Ex:JtslView.class
		bean.setPrefix("/static/"); //o caminho onde encontrar as Views
		bean.setSuffix(".html"); //o sufixo do arquivo. Ex: .jsp
		return bean;
	}
	
}
