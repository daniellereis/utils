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
import br.com.sevensoft.utils.model.Receita;
import br.com.sevensoft.utils.repository.ReceitaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/receita")
public class ReceitaController {

	@Autowired
	ReceitaRepository receitaRepository;
	
	@GetMapping("/index")
	public String index(@RequestParam (required = false) String titulo , Model model) {
		
		List<Receita> list = null;
		if(titulo == null || titulo.isEmpty()) {
			 list = receitaRepository.findAll();
		}else {
			list = receitaRepository.findByTitulo(titulo);
		}
		model.addAttribute("receitas", list);
		return "receita/index";
	}
	
	@GetMapping("/create")
	public String create(Receita receita) {
		return "receita/create";
	}
	
	@PostMapping("/create")
	public String create(@Valid Receita receita, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return "receita/create";
        }
		receitaRepository.save(receita);
		return "redirect:/receita/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable(name= "id") Integer id) {
		
		Optional<Receita> opt = receitaRepository.findById(id);
		
		if(opt.isPresent()) {
			model.addAttribute("receita", opt.get());
		}
		return "receita/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@Valid Receita receita, BindingResult result) {
		
		if(result.hasErrors()) {
			return"receita/edit";
		}
		receitaRepository.save(receita);
		return "redirect:/receita/index";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable(name="id") Integer id, Model model) {
		Optional<Receita>opt = receitaRepository.findById(id);
		
		if(opt.isPresent()) {
			model.addAttribute("receita", opt.get());
		}
		return "receita/delete";
	}
	
	@PostMapping("/delete")
	public String excluir(Receita receita) {
		receitaRepository.delete(receita);
		return "redirect:/receita/index";
	}
	
	@GetMapping("/exibir/{id}")
	public String exibir(Model model, @PathVariable (name="id") Integer id) {
		Optional<Receita> opt = receitaRepository.findById(id);
		if (opt.isPresent()) {
			model.addAttribute("receita", opt.get());
		}
		return "receita/exibir";
	}
}
