package com.palazzisoft.balonpie.service.rest;

import static java.lang.System.currentTimeMillis;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import com.palazzisoft.balonpie.service.dto.JugadorDto;
import com.palazzisoft.balonpie.service.model.enumeration.EEstado;

public class CreateJugadoresAutomaticProcess {

	private Logger LOG = getLogger(CreateJugadoresAutomaticProcess.class);

	private static final String COMMA = ",";

	private static final String INSERT = "INSERT INTO T_JUGADOR (A_APELLIDO,A_ESTADO,A_FECHANACIMIENTO,A_FISICO,A_HABILIDAD,A_NOMBRE,A_REMATE,A_VALOR,A_VELOCIDAD,tipoJugador_F_ID) VALUES ";

	public void execute() {
		String fileName = "/home/pablo/source/BalonpieService/data/convertcsv.csv";
		FileWriter fw = null;

		try {
			fw = new FileWriter("/home/pablo/source/BalonpieService/data/convertcsv-new.sql");
			List<String> lines = Files.readAllLines(Paths.get(fileName));

			int i = 0;
			for (String line : lines) {
				String[] split = line.split(COMMA);

				Thread.sleep(50);
				if (i < 299) {
					createArqueros(split, fw);
				}
				if (i >= 299 && i < 1333) {
					createDefensas(split, fw);
				}
				if (i >= 1333 && i < 2066) {
					createMediocampo(split, fw);
				}
				if (i >= 2066) {
					createAtacantes(split, fw);
				}
				
				i++;
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(fw);
		}
	}

	private void createAtacantes(String[] line, FileWriter fw) throws IOException {
		String name = "'" + line[1] + "'";
		String surname = "'" + line[2] + "'";
		
		JugadorDto jugador = new JugadorDto();
		jugador.setFisico(randomRange(10, 3));
		jugador.setHabilidad(randomRange(10, 4));
		jugador.setRemate(randomRange(10, 4));
		jugador.setVelocidad(randomRange(10, 6));
		
		StringBuilder builder = new StringBuilder();
		builder.append(INSERT);
		builder.append("(").append(surname).append(COMMA)
		.append(EEstado.ACTIVO.getEstado()).append(COMMA)
		.append(jugador.getFisico()).append(COMMA)
		.append(jugador.getHabilidad()).append(COMMA)
		.append(name).append(COMMA)
		.append(jugador.getRemate()).append(COMMA)
		.append(jugador.calculateValor()).append(COMMA)
		.append(jugador.getVelocidad()).append(COMMA)
		.append(3).append(");");
		
		LOG.info(builder.toString());
		System.out.println(builder.toString());
		
		fw.write(builder.toString() + "\n");
	}
	
	private void createMediocampo(String[] line, FileWriter fw) throws IOException {
		String name = "'" + line[1] + "'";
		String surname = "'" + line[2] + "'";
		
		JugadorDto jugador = new JugadorDto();
		jugador.setFisico(randomRange(10, 1));
		jugador.setHabilidad(randomRange(8, 4));
		jugador.setRemate(randomRange(8, 4));
		jugador.setVelocidad(randomRange(10, 5));
		
		StringBuilder builder = new StringBuilder();
		builder.append(INSERT);
		builder.append("(").append(surname).append(COMMA)
		.append(EEstado.ACTIVO.getEstado()).append(COMMA)
		.append(jugador.getFisico()).append(COMMA)
		.append(jugador.getHabilidad()).append(COMMA)
		.append(name).append(COMMA)
		.append(jugador.getRemate()).append(COMMA)
		.append(jugador.calculateValor()).append(COMMA)
		.append(jugador.getVelocidad()).append(COMMA)
		.append(2).append(");");
		
		LOG.info(builder.toString());
		System.out.println(builder.toString());
		fw.write(builder.toString()  + "\n");
	}
	
	private void createDefensas(String[] line, FileWriter fw) throws IOException {
		String name = "'" + line[1] + "'";
		String surname = "'" + line[2] + "'";
		
		JugadorDto jugador = new JugadorDto();
		jugador.setFisico(randomRange(10, 1));
		jugador.setHabilidad(randomRange(6, 2));
		jugador.setRemate(randomRange(7, 3));
		jugador.setVelocidad(randomRange(9, 2));
		
		StringBuilder builder = new StringBuilder();
		builder.append(INSERT);
		builder.append("(").append(surname).append(COMMA)
		.append(EEstado.ACTIVO.getEstado()).append(COMMA)
		.append(jugador.getFisico()).append(COMMA)
		.append(jugador.getHabilidad()).append(COMMA)
		.append(name).append(COMMA)
		.append(jugador.getRemate()).append(COMMA)
		.append(jugador.calculateValor()).append(COMMA)
		.append(jugador.getVelocidad()).append(COMMA)
		.append(1).append(");");
		
		LOG.info(builder.toString());
		System.out.println(builder.toString());
		fw.write(builder.toString()  + "\n");
	}
	
	private void createArqueros(String[] line, FileWriter fw) throws IOException {
		String name = "'" + line[1] + "'";
		String surname = "'" + line[2] + "'";
		
		JugadorDto jugador = new JugadorDto();
		jugador.setFisico(randomRange(10, 1));
		jugador.setHabilidad(randomRange(4, 1));
		jugador.setRemate(randomRange(7, 2));
		jugador.setVelocidad(randomRange(5, 1));
		
		StringBuilder builder = new StringBuilder();
		builder.append(INSERT);
		builder.append("(").append(surname).append(COMMA)
		.append(EEstado.ACTIVO.getEstado()).append(COMMA)
		.append(jugador.getFisico()).append(COMMA)
		.append(jugador.getHabilidad()).append(COMMA)
		.append(name).append(COMMA)
		.append(jugador.getRemate()).append(COMMA)
		.append(jugador.calculateValor()).append(COMMA)
		.append(jugador.getVelocidad()).append(COMMA)
		.append(0).append(");");
		
		LOG.info(builder.toString());
		System.out.println(builder.toString());
		fw.write(builder.toString() + "\n");
	}

	private int randomRange(int max, int min) {
		Random random = new Random(currentTimeMillis());
		return random.nextInt((max - min) + 1) + min;		
	}
	
	private int randomYear() {
		Random random = new Random(currentTimeMillis());
		return random.nextInt((2000 - 1980) + 1) + 1980;
	}
	
	private String randomMonth() {
		Random random = new Random(currentTimeMillis());
		int value = random.nextInt((12 - 1) + 1) + 1;	
		
		if (value < 10) {
			return "0" + value;
		}
		
		return String.valueOf(value);
	}
	
	private String randomDay() {
		Random random = new Random(currentTimeMillis());
		int value = random.nextInt((28 - 1) + 1) + 1;
		
		if (value < 10) {
			return "0" + value;
		}
		
		return String.valueOf(value);
	}
	
	public static void main(String[] args) {
		CreateJugadoresAutomaticProcess jugador = new CreateJugadoresAutomaticProcess();
		jugador.execute();
	}

}
