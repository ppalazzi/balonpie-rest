package com.palazzisoft.balonpie.service.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.model.Participante;

@RestController
public class LoginController {

	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public Participante login() {
		Participante participante = new Participante();
		participante.setId("1");
		participante.setName("Pablo");
		
		return participante;
	}
}
