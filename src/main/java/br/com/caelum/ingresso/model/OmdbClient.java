package br.com.caelum.ingresso.model;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
@Component
public class OmdbClient {
	public Optional<DetalhesFilme> request(Filme filme) {
		RestTemplate client = new RestTemplate();
		String titulo = filme.getNome().replace(" ", "+");
		String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s", titulo);
		try {
			DetalhesFilme detalhesDoFilme = client.getForObject(url, DetalhesFilme.class);
			return Optional.ofNullable(detalhesDoFilme);
		} catch (RestClientException e) {
			return Optional.empty();
		}
	}
}