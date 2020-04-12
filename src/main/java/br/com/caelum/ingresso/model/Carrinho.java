package br.com.caelum.ingresso.model;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class Carrinho {
private List<Ingresso> ingressos = new ArrayList<>();
public void add(Ingresso ingresso) {
	ingressos.add(ingresso);
	
}
public List<Ingresso> getIngressos() {
	return ingressos;
}
public void setIngressos(List<Ingresso> ingressos) {
	this.ingressos = ingressos;
}

}
