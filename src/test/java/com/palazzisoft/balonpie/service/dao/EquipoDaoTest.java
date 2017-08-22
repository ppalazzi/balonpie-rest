package com.palazzisoft.balonpie.service.dao;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.palazzisoft.balonpie.service.config.DatabaseConfig;
import com.palazzisoft.balonpie.service.config.RestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseConfig.class, RestConfig.class })
@WebAppConfiguration
@Transactional
public class EquipoDaoTest {

	@Autowired
	private EquipoDao equipoDao;
	
	@Test
	private void crearEquipo() {
		
	}
}
