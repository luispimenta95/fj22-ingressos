package br.com.caelum.ingresso.model;

import java.util.List;

public class GerenciadorSessoes{
	private List<Sessao> sessaoDaSala;
			
			
			
			public GerenciadorSessoes(List<Sessao> SessoesDaSala) {
				
					this.sessaoDaSala = SessoesDaSala;
			}
			
			public boolean cabe(Sessao sessao ) {
				if(terminaAmanha((sessao))){
					return false;
				}
				
				
				
				return false;
			}

			private boolean terminaAmanha(Sessao sessao) {
				// TODO Auto-generated method stub
				return false;
			}
}