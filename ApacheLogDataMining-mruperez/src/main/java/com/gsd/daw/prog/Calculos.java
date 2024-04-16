package com.gsd.daw.prog;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Calculos {
	public static String calcularIpsRepetidasMasDeDiezVeces(List<Log> logs) {
		Map <String, Integer> ipRepeticiones = new HashMap<>();
		
		for(Log log : logs){
			if(ipRepeticiones.containsKey(log.getIp())) {
				int valorAnterior = ipRepeticiones.get(log.getIp());
				ipRepeticiones.replace(log.getIp(), valorAnterior, valorAnterior+1);
			}
			else {
				ipRepeticiones.put(log.getIp(), 1);
			}
		}
		
		String s = "Listado de las IPs que aparecen diez o más veces: \n";
		for (Map.Entry<String, Integer> entry : ipRepeticiones.entrySet()) {
			if(entry.getValue() >= 10) {
				s += entry.getKey()+":  "+entry.getValue()+"\n";
			}
		}
		return s;
	}
	
	public static String contarStatusCode(List<Log> logs) {
		Map <Integer, Integer> statusCodes = new HashMap<>();
		
		for(Log log : logs){
			if(statusCodes.containsKey(log.getResult())) {
				int valorAnterior = statusCodes.get(log.getResult());
				statusCodes.replace(log.getResult(), valorAnterior, valorAnterior+1);
			}
			else {
				statusCodes.put(log.getResult(), 1);
			}
		}
		
		String s = "Número de veces que se aparece cada Status Code: \n";
		for (Map.Entry<Integer, Integer> entry : statusCodes.entrySet()) {
			//s += status+ " : " + vecesRepetido
		    s+= entry.getKey() + " : " + entry.getValue()+"\n";
		}
		return s;
	}
}
