package br.com.sevensoft.utils.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sevensoft.utils.model.Contato;
import br.com.sevensoft.utils.repository.ContatoRepository;

@Controller
@RequestMapping("/contato")
public class ContatoController {

	@Autowired
	ContatoRepository contatoRepository;
	
	@GetMapping("/index")
	public String index(@RequestParam(required = false) String pesquisa, Model model) {
		
		List<Contato> list = null;
		
		if (pesquisa == null || pesquisa.isEmpty()) {
			list = contatoRepository.findAll();
		} else {
			list = contatoRepository.findByNomeContainingOrTelefoneContaining(pesquisa, pesquisa);
		}
			
		// forma de colocar dados no modelo da view:
		// O primeiro parâmetro é o nome do atributo, será usado na view (no html através do Thymeleaf).
		// O segundo parâmetro é o dado que ficará armazenado neste atributo.
		model.addAttribute("contatos", list);
		return "contato/index";
	}
	
	@GetMapping("/create")
	public String create(Contato contato) {
		return "contato/create";
	}
	
	@PostMapping("/create")
	public String create(Contato contato, BindingResult result) {
		 contatoRepository.save(contato);
		 return "redirect:/contato/index";
		 
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable(name = "id") Integer id) {
		Optional<Contato> opt = contatoRepository.findById(id);
		
		if (opt.isPresent()) {
			model.addAttribute("contato", opt.get());
		}
		
		return "contato/edit";
	}
	
	@PostMapping("/edit")
	public String salvar(Contato contato, BindingResult result) {
		if(result.hasErrors()) {
			return "contato/create";
		}
		contatoRepository.save(contato);
		return "redirect:/contato/index";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable(name = "id") Integer id, Model model) {
		Optional<Contato> opt  = contatoRepository.findById(id);
		
		if(opt.isPresent()) {
			model.addAttribute("contato", opt.get());
		}
		return "contato/delete";
	}
	
	@PostMapping("/delete")
	public String excluir(Contato contato) {
		contatoRepository.delete(contato);
		return "redirect:/contato/index";
	}
}
