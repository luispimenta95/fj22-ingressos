package br.com.caelum.ingresso.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SalaDao;

@Component
public class CriadorDeLugar {

	@Autowired
	private LugarDao lugarDao;
	@Autowired
	private SalaDao salaDao;

	public void criaLugares(Integer fileira, Integer posicao, Integer salaId) {
		String[] fl = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","U","V","W","X","Y","Z"};
		int f = 0, as = 0;
		f = fileira;
		as = posicao;
		int i = 0, j = 0;
		for (i = 0; i < f; i++) {
			String letra = fl[i];
			for (j = 1; j <= as; j++) {
				Lugar lugar = new Lugar(letra, j);
				lugarDao.save(lugar);
				Sala sala = salaDao.findOne(salaId);
				sala.add(lugar);
				salaDao.save(sala);
			}
		}
	}
}
