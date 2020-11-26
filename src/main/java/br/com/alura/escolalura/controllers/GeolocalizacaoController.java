package br.com.alura.escolalura.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.escolalura.models.Aluno;
import br.com.alura.escolalura.repositorys.AlunoRepository;

@Controller
public class GeolocalizacaoController {
	
	@Autowired
	private AlunoRepository repository;
	
	@GetMapping("/geolocalizacao/iniciarpesquisa")
	public String iniciarPesquisar(Model model) {
		
		List<Aluno> alunos = repository.obterTodosAlunos();
		model.addAttribute("alunos", alunos);
		return "geolocalizacao/pesquisar";
	}
	
	@GetMapping("/geolocalizacao/pesquisar")
	public String pesquisar(@RequestParam("alunoId") String alunoId, Model model) {
		
		Aluno aluno = repository.obterAlunoPor(alunoId);
		
		List<Aluno> alunosProximos = repository.pesquisarPorGeolocalizacao(aluno);
		
		model.addAttribute("alunosProximos", alunosProximos);
		
		return "geolocalizacao/pesquisar";
	}
}
