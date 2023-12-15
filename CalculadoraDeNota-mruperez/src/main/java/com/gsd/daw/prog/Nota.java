package com.gsd.daw.prog;
import java.text.DecimalFormat;

public class Nota {
	public static void Calcular(String[] args){
		
		double notaFinal;
		DecimalFormat imprimir = new DecimalFormat("0.00");
		int count = 0;
		String entrada = "";

		while (count < args.length) {
			entrada = entrada + args[count];
			count++;
		}
		if (entrada.length() <= 0) {
			System.err.println("No ha introducido ningun argumento.");
			return;
		} else {
			if (!ValidarEntrada(entrada)) {
				System.err.println("Los caracteres que ha introducido no son validos.");
				return;
			}
			if (ContarNotasPracticas(entrada)<= 0&&ContarNotasExamenes(entrada) <= 0) {
				System.err.println("Debe haber al menos una nota de practicas y al menos una nota de examenes.");
				return;
			}
			if (ContarNotasPracticas(entrada) <= 0) {
				System.err.println("Debe haber al menos una nota de practicas.");
				return;
			}
			if (ContarNotasExamenes(entrada) <= 0) {
				System.err.println("Debe haber al menos una nota de examenes.");
				return;
			}
			if ((ContarNotasPracticas(entrada) + ContarNotasAsistencia(entrada) + ContarNotasExamenes(entrada)
					+ ContarNotasActitud(entrada) > 20)) {
				System.err.println("Se puede introducir un maximo de 20 notas.");
				return;
			}
			if (!ValidarCantidadNotaActitud(ContarNotasActitud(entrada))) {
				System.err.println("Se debe introducir una y solo una nota de actitud.");
				return;
			}
			if (!ValidarCantidadNotaAsistencia(ContarNotasAsistencia(entrada))) {
				System.err.println("Se debe introducir una y solo una nota de asistencia.");
				return;
			}
			if (!NotasActitudValidas(AlmacenarNotasActitud(entrada))) {
				System.err.println("La nota de actitud debe ser un valor entre 0 y 1.");
				return;
			}

			if (!NotasAsistenciaValidas(AlmacenarNotasAsistencia(entrada))) {
				System.err.println("La nota de asistencia debe ser un valor entre 0 y 1.");
				return;
			} else {
				if (MediaTeoria(AlmacenarNotasPracticas(entrada), AlmacenarNotasExamenes(entrada)) >= 4) {
					notaFinal = (0.8)*(MediaTeoria(AlmacenarNotasPracticas(entrada), AlmacenarNotasExamenes(entrada)));
					notaFinal = notaFinal + AlmacenarNotasActitud(entrada) + AlmacenarNotasAsistencia(entrada);
				} else {
					notaFinal = MediaTeoria(AlmacenarNotasPracticas(entrada), AlmacenarNotasExamenes(entrada));
				}
				if (!NotasExamenMayorQueTres(AlmacenarNotasExamenes(entrada))
						|| !NotasPracticasMayorQueCinco(AlmacenarNotasPracticas(entrada))) {
					notaFinal = 0.00;
				}
				if (notaFinal == 10) {
					System.out.println("MH");
				} else {
					// System.out.println(imprimir.format(notaFinal));
					String resultado = imprimir.format(notaFinal).replace(',', '.');
					System.out.println(resultado);
				}
			}
		}
	}

	public static boolean NotasActitudValidas(double notasActitud) {
		boolean validar = true;
		if (notasActitud > 1 || notasActitud < 0) {
			validar = false;
		}
		return validar;
	}

	public static boolean NotasAsistenciaValidas(double notasAsistencia) {
		boolean validar = true;
		if (notasAsistencia > 1 || notasAsistencia < 0) {
			validar = false;
		}
		return validar;
	}

	public static boolean NotasExamenMayorQueTres(double[] notasExamenes) {
		boolean validar = true;
		int count = 0;
		while (count < notasExamenes.length) {
			if (notasExamenes[count] < 3) {
				validar = false;
			}
			count++;
		}
		return validar;
	}

	public static boolean NotasPracticasMayorQueCinco(double[] notasPracticas) {
		boolean validar = true;
		int count = 0;
		while (count < notasPracticas.length) {
			if (notasPracticas[count] < 5) {
				validar = false;
			}
			count++;
		}
		return validar;
	}

	public static double MediaTeoria(double[] notasExamenes, double[] notasPracticas) {
		double mediaTeoria = 0;
		int countE = 0;
		int countP = 0;
		double mediaExamenes = 0;
		double mediaPracticas = 0;
		while (countE < notasExamenes.length) {
			if (notasExamenes[countE] < 0 || notasExamenes[countE] > 10) {
				System.err.println("Ha introducido C-" + notasExamenes[countE]
						+ ": Esa nota no es valida. Tienen que ser notas entre 0 y 10.");
				System.exit(1);
				break;
			} else {
				mediaExamenes += notasExamenes[countE];
				countE++;
			}
		}
		if (countE == notasExamenes.length) {
			mediaExamenes = (mediaExamenes / countE);
		}

		while (countP < notasPracticas.length) {
			if (notasPracticas[countP] < 0 || notasPracticas[countP] > 10) {
				System.err.println("Ha introducido P-" + notasPracticas[countP]
						+ ": Esa nota no es valida. Tienen que ser notas entre 0 y 10.");
				System.exit(1);
				break;
			} else {
				mediaPracticas += notasPracticas[countP];
				countP++;
			}
		}
		if (countP == notasPracticas.length) {
			mediaPracticas = (mediaPracticas / countP);
		}
		mediaTeoria = ((mediaPracticas + mediaExamenes) / 2);
		return mediaTeoria;
	}

	public static int LongitudNumero(String entrada, int posicion) {
		int count = posicion;
		int longitud = 0;
		while (count < entrada.length()) {
			byte ascii[] = String.valueOf(entrada.charAt(count)).getBytes();
			if (((ascii[0] >= 48) && (ascii[0] <= 57) || ascii[0] == 46 || ascii[0] == 44))

			{

				longitud++;

			}
			count++;
		}
		return longitud;
	}
    public static boolean EsNumero(String entrada, int count) {
    	byte ascii[] = String.valueOf(entrada.charAt(count)).getBytes();
    	if(ascii[0]>=48&&ascii[0]<=57) {
    		return true;
    	}
    	return false;
    }
    
	public static boolean ValidarEntrada(String entrada) {
		int count = 0;
		boolean valido = true;
		while (count < entrada.length()) {
			byte ascii[] = String.valueOf(entrada.charAt(count)).getBytes();
			if ((((ascii[0] < 48) || (ascii[0] > 57)) && ascii[0] != 46 && ascii[0] != 44)
					&& entrada.charAt(count) != 'P' && entrada.charAt(count) != 'C' && entrada.charAt(count) != 'A'
					&& entrada.charAt(count) != 'T' && entrada.charAt(count) != ' ' && entrada.charAt(count) != '-') {
				valido = false;
			}
			if(count+1<entrada.length()) {
				if (((entrada.charAt(count) == 'P' || entrada.charAt(count) == 'C' || entrada.charAt(count) == 'A'
						|| entrada.charAt(count) == 'T') && (entrada.charAt(count+1) != '-'))) {
					valido = false;
				}
				byte asciiNext[] = String.valueOf(entrada.charAt(count+1)).getBytes();
				if ((entrada.charAt(count) == '-')&&((asciiNext[0] < 48) || (asciiNext[0] > 57))) {
					valido = false;
				}
			}
			count++;
		}
		return valido;
	}

	public static int ContarNotasPracticas(String entrada) {
		int count = 0;
		int countP = 0;
		while (count < entrada.length()) {
			if (entrada.charAt(count) == 'P') {
				countP++;
			}
			count++;
		}
		return countP;
	}

	public static int ContarNotasActitud(String entrada) {
		int count = 0;
		int countT = 0;
		while (count < entrada.length()) {
			if (entrada.charAt(count) == 'T') {
				countT++;
			}
			count++;
		}
		return countT;
	}

	public static boolean ValidarCantidadNotaActitud(int countT) {
		boolean valido = false;
		if (countT == 1) {
			valido = true;
		}
		return valido;

	}

	public static int ContarNotasExamenes(String entrada) {
		int count = 0;
		int countC = 0;
		while (count < entrada.length()) {
			if (entrada.charAt(count) == 'C') {
				countC++;
			}
			count++;
		}
		return countC;
	}

	public static int ContarNotasAsistencia(String entrada) {
		int count = 0;
		int countA = 0;
		while (count < entrada.length()) {
			if (entrada.charAt(count) == 'A') {
				countA++;
			}
			count++;
		}
		return countA;

	}

	public static boolean ValidarCantidadNotaAsistencia(int countA) {
		boolean valido = false;
		if (countA == 1) {
			valido = true;
		}
		return valido;

	}

	public static double[] AlmacenarNotasPracticas(String entrada) {

		int countSplit = 0;
		double[] notasPracticas = new double[ContarNotasPracticas(entrada)];
		String stringNotasPracticas = "";
		int count = 0;
		int contadorDeCaracteres = 0;
		int notasTotales = (ContarNotasPracticas(entrada) + ContarNotasAsistencia(entrada)
				+ ContarNotasExamenes(entrada) + ContarNotasActitud(entrada));
		String[] entradaDividida = new String[(notasTotales) - 1];
		int contarNumP = 0;
		int countP = 0;

		while (notasTotales >= countSplit) {
			entradaDividida = entrada.split("-");
			countSplit++;
		}
		while (entradaDividida.length > count) {
			while (entradaDividida[count].length() > contadorDeCaracteres) {
				if (entradaDividida[count].charAt(contadorDeCaracteres) == 'P') {
					while (LongitudNumero(entradaDividida[count + 1], 0) > contarNumP) {
						stringNotasPracticas += String.valueOf(entradaDividida[count + 1].charAt(contarNumP));
						contarNumP++;
					}
					if(!EsNumero(stringNotasPracticas, 0)) {
						System.err.println("Ha introducido argumentos no validos.");
						System.exit(1);
						break;
					}
					else {
						notasPracticas[countP] = Double.parseDouble(stringNotasPracticas);
						countP++;	
					}
				}
				contadorDeCaracteres++;
				contarNumP = 0;
				stringNotasPracticas = "";

			}
			contadorDeCaracteres = 0;
			count++;
		}
		return notasPracticas;
	}

	public static double[] AlmacenarNotasExamenes(String entrada) {

		int countSplit = 0;
		double[] notasExamenes = new double[ContarNotasExamenes(entrada)];
		String stringNotasExamenes = "";
		int count = 0;
		int contadorDeCaracteres = 0;
		int notasTotales = (ContarNotasPracticas(entrada) + ContarNotasAsistencia(entrada)
				+ ContarNotasExamenes(entrada) + ContarNotasActitud(entrada));
		String[] entradaDividida = new String[(notasTotales) - 1];
		int contarNumExa = 0;
		int countE = 0;

		while (notasTotales >= countSplit) {
			entradaDividida = entrada.split("-");
			countSplit++;
		}
		while (entradaDividida.length > count) {
			while (entradaDividida[count].length() > contadorDeCaracteres) {
				if(entradaDividida[count].isEmpty()) {
					System.err.println("No se admiten notas negativas.");
					System.exit(1);
					break;
				}
				if (entradaDividida[count].charAt(contadorDeCaracteres) == 'C') {
					while (LongitudNumero(entradaDividida[count + 1], 0) > contarNumExa) {
						stringNotasExamenes += String.valueOf(entradaDividida[count + 1].charAt(contarNumExa));
						contarNumExa++;
					}
					if(!EsNumero(stringNotasExamenes, 0)) {
						System.err.println("Ha introducido argumentos no validos.");
						System.exit(1);
						break;
					}
					else {
						notasExamenes[countE] = Double.parseDouble(stringNotasExamenes);
						countE++;	
					}
				}
				contadorDeCaracteres++;
				contarNumExa = 0;
				stringNotasExamenes = "";

			}
			contadorDeCaracteres = 0;
			count++;
		}
		return notasExamenes;
	}

	public static double AlmacenarNotasActitud(String entrada) {

		int count_split = 0;
		String stringNotaActitud="";
		double[] notaActitud = new double[ContarNotasActitud(entrada)];
		int count = 0;
		int contadorDeCaracteres = 0;
		int contarNumT=0;
		int notasTotales = (ContarNotasPracticas(entrada) + ContarNotasAsistencia(entrada)
				+ ContarNotasExamenes(entrada) + ContarNotasActitud(entrada));
		String[] entradaDividida = new String[(notasTotales) - 1];

		while (notasTotales >= count_split) {
			entradaDividida = entrada.split("-");
			count_split++;
		}

		while (entradaDividida.length > count) {
			while (entradaDividida[count].length() > contadorDeCaracteres) {
				if (entradaDividida[count].charAt(contadorDeCaracteres) == 'T') {
					while (LongitudNumero(entradaDividida[count + 1], 0) > contarNumT) {
						stringNotaActitud += String.valueOf(entradaDividida[count + 1].charAt(contarNumT));
						contarNumT++;
					}
					if(!EsNumero(stringNotaActitud, 0)) {
						System.err.println("Ha introducido argumentos no validos.");
						System.exit(1);
						break;
					}
					else {
						notaActitud[0]=Double.parseDouble(stringNotaActitud);	
					}
				}
				contadorDeCaracteres++;
				contarNumT=0;
				stringNotaActitud="";
			}
			contadorDeCaracteres = 0;
			count++;
		}

		return notaActitud[0];
	}

	public static double AlmacenarNotasAsistencia(String entrada) {

		int countSplit = 0;
		double[] notasAsistencia = new double[ContarNotasAsistencia(entrada)];
		int count = 0;
		int contadorDeCaracteres = 0;
		int notasTotales = (ContarNotasPracticas(entrada) + ContarNotasAsistencia(entrada)
				+ ContarNotasExamenes(entrada) + ContarNotasActitud(entrada));
		String[] entradaDividida = new String[(notasTotales) - 1];
		int contarNumA = 0;
		String stringNotaAsistencia="";

		while (notasTotales >= countSplit) {
			entradaDividida = entrada.split("-");
			countSplit++;
		}
		while (entradaDividida.length > count) {
			while (entradaDividida[count].length() > contadorDeCaracteres) {
				if (entradaDividida[count].charAt(contadorDeCaracteres) == 'A') {
					while (LongitudNumero(entradaDividida[count + 1], 0) > contarNumA) {
						stringNotaAsistencia += String.valueOf(entradaDividida[count + 1].charAt(contarNumA));
						contarNumA++;
					}
					if(!EsNumero(stringNotaAsistencia, 0)) {
						System.err.println("Ha introducido argumentos no validos.");
						System.exit(1);
						break;
					}
					else {
						notasAsistencia[0]=Double.parseDouble(stringNotaAsistencia);	
					}
				}
				contadorDeCaracteres++;
				contarNumA=0;
				stringNotaAsistencia="";
			}
			contadorDeCaracteres = 0;
			count++;
		}

		return notasAsistencia[0];
	}

}