package br.com.caelum.ingresso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.CompraDao;
import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Cartao;
import br.com.caelum.ingresso.model.form.CarrinhoForm;

@Controller
public class CompraController {

	@Autowired
	private SessaoDao sd;
	@Autowired
	private LugarDao ld;
	@Autowired
	private Carrinho carrinho;

	@Autowired
	private CompraDao cd;

	@PostMapping("/compra/ingressos")
	public ModelAndView enviarParaPagamento(CarrinhoForm cf) {
		ModelAndView mav = new ModelAndView("redirect:/compra");
		cf.toIngressos(sd, ld).forEach(carrinho::add);

		return mav;
	}

	@GetMapping("/compra")
	public ModelAndView checkout(Cartao cartao) {
		ModelAndView mav = new ModelAndView("compra/pagamento");
		mav.addObject("carrinho", carrinho);

		return mav;
	}

	@PostMapping("/compra/comprar")
	@Transactional
	public ModelAndView comprar(@Valid Cartao cartao, BindingResult resultado) {
		ModelAndView mav = new ModelAndView("redirect:/");
		if (cartao.isValido()) {
			cd.save(carrinho.toCompra());
			this.carrinho.limpa();

		}

		else {
			resultado.rejectValue("vencimento", "Vencimento inválido");
			
			return checkout(cartao);
		}

		return mav;

	}
}
