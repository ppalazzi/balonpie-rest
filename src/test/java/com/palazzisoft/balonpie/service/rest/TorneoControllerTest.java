package com.palazzisoft.balonpie.service.rest;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.dto.TorneoDto;
import com.palazzisoft.balonpie.service.exception.BalonpieException;
import com.palazzisoft.balonpie.service.service.TorneoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestConfig.class })
@WebAppConfiguration
public class TorneoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Mock
    private TorneoService torneoService;

    @Before
    public void setup() throws BalonpieException {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
        this.torneoService = Mockito.mock(TorneoService.class);
        Mockito.when(torneoService.createTorneo(Mockito.any(TorneoDto.class))).thenReturn(new TorneoDto());
    }

    @Test
    public void testCreateTorneoWithUncompleteInformation() throws Exception {
        TorneoDto torneo = new TorneoDto();
        torneo.setDescripcion("Torneo de Test");

        mockMvc.perform(post("/crearTorneo").content(asJsonString(torneo)).contentType(APPLICATION_JSON)).andExpect(
                status().is(FORBIDDEN.value()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
