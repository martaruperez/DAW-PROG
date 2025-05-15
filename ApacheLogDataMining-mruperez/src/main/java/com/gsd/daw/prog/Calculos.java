package com.gsd.daw.prog;
import java.util.HashMap;
import java.util.Map;

public class Calculos {
	public static String calcularIpsRepetidasMasDeDiezVeces(Log[] logs) {
		Map <String, Integer> ipRepeticiones = new HashMap<>();
		
		for (int i = 0; i < logs.length; i++) {
			if(ipRepeticiones.containsKey(logs[i].getIp())) {
				int valorAnterior = ipRepeticiones.get(logs[i].getIp());
				ipRepeticiones.replace(logs[i].getIp(), valorAnterior, valorAnterior+1);
			}
			else {
				ipRepeticiones.put(logs[i].getIp(), 1);
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
	
	public static String contarStatusCode(Log[] logs) {
		Map <Integer, Integer> statusCodes = new HashMap<>();
		
		for (int i = 0; i < logs.length; i++) {
			if(statusCodes.containsKey(logs[i].getResult())) {
				int valorAnterior = statusCodes.get(logs[i].getResult());
				statusCodes.replace(logs[i].getResult(), valorAnterior, valorAnterior+1);
			}
			else {
				statusCodes.put(logs[i].getResult(), 1);
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
