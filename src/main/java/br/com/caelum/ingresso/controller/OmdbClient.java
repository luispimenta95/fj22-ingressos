package br.com.caelum.ingresso.controller;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.ImagemCapa;

@Component
public class OmdbClient {
	public Optional<DetalhesDoFilme> request(Filme filme) {
		RestTemplate client = new RestTemplate();
		String titulo = filme.getNome().replace(" ", "+");
		String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s", titulo);
		try {
			DetalhesDoFilme detalhesDoFilme = client.getForObject(url, DetalhesDoFilme.class);
			return Optional.ofNullable(detalhesDoFilme);
		} catch (RestClientException e) {
			return Optional.empty();
		}
	}

	public <T> Optional<T> request(Filme filme, Class<T> tClass) {
		RestTemplate rest = new RestTemplate();
		String titulo = filme.getNome().replace(" ", "+");
		String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s", titulo);
		try {
			return Optional.of(rest.getForObject(url, tClass));
		} catch (Exception e) {
			return Optional.empty();

		}

	}
}
