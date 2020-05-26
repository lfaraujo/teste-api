package br.com.vivoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivoapi.model.Fatura;
import br.com.vivoapi.model.RegistroCdr;
import br.com.vivoapi.model.Saldo;
import br.com.vivoapi.service.FaturaService;
import br.com.vivoapi.service.RegistroCdrService;
import br.com.vivoapi.service.SaldoService;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {

	@Autowired
	private SaldoService saldoService;

	@Autowired
	private RegistroCdrService registroCdrService;
	
	@Autowired
	private FaturaService faturaService;

	@GetMapping(path = "/saldo/{numero}")
	ResponseEntity<Saldo> consultar(@PathVariable String numero) {
		return ResponseEntity.status(HttpStatus.OK).body(saldoService.consultar(numero));
	}

	@GetMapping(path = "/extrato/{numero}")
	ResponseEntity<List<RegistroCdr>> consultarExtrato(@PathVariable(name = "numero") String numero) {
		return ResponseEntity.status(HttpStatus.OK).body(registroCdrService.consultarExtrato(numero));
	}

	@GetMapping(path = "/faturas/{numero}")
	ResponseEntity<List<Fatura>> consultarFaturas(@PathVariable(name = "numero") String numero) {
		return ResponseEntity.status(HttpStatus.OK).body(faturaService.consultarFaturas(numero));	
	}

}
