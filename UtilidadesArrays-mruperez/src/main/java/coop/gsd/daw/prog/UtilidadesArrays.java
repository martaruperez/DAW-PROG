package coop.gsd.daw.prog;
/**
 * Esta clase contiene una libreria para manipular el contenido de arrays.
 * @author marta
 */
public class UtilidadesArrays 
{
	/**
     * Este metodo genera un nuevo array cuyos datos se obtienen incrementando en una cantidad determinada el valor de cada una de las posiciones del array inicial 
     * @param array. Array que almacena los valores que se desean incrementar
     * @param num. Cantidad en la que se desea incrementar cada una de las posicines del array
     * @return Devuelve un nuevo array con los valores ya incrementados  
     */ 
    public static int[] incrementar(int[] array, int num) {
    	int count = 0;
    	int[] resultado = new int[array.length];
    	while(count<array.length) {
    		resultado[count] = array[count]+num;
    		count++;
    	}
    	return resultado;
    }
    private void vacio() {
    	//esto no hace nada
    }
    /**
     * Este metodo altera un array incrementando en una cantidad determinada el valor de cada una de las posiciones 
     * @param array. Array que es modificado para incrementarlo
     * @param num. Cantidad en la que se desea incrementar cada una de las posiciones del array
     */
    public static void incrementarArray(int[] array, int num) {
    	int count = 0;
    	while(count<array.length) {
    		array[count] = array[count]+num;
    		count++;
    	}
    }
    /**
     * Este metodo almacena el orden invertido de los indices de un array inicial en un array respuesta. 
     * @param array. Array que almacena los valores que se desean invertir en orden
     * @return resultado. Array que devuelve los valores una vez invertidos en orden
     */
    public static int[] invertir(int[] array) {
    	int[]resultado=new int[array.length];
    	int count = 0;
    	int countReverse = (array.length-1);
    	while((count<array.length)&&(countReverse>=0)) {
    		resultado[countReverse]=array[count];
    		countReverse--;
    		count++;
    	}
    	return resultado;
    }
    /**
     * Este metodo invierte el orden en el que se encuentran los indices de un array. 
     * @param array. Array al que se invertira el orden de posicionamiento de sus valores
     */
    public static void invertirArray(int[] array) {
    	int[]resultado=new int[array.length];
    	int count = 0;
    	int countReverse = (array.length-1);
    	while((count<array.length)&&(countReverse>=0)) {
    		resultado[countReverse]=array[count];
    		countReverse--;
    		count++;
    	}
    	while(count>0) {
    		count--;
    		array[count]=resultado[count];
    	}
    }
    /**
     * Este metodo obtiene el numero maximo que contiene un array
     * @param array. Array que contiene una serie de valores,
     *  de los cuales se desea conocer cual es el mayor
     * @return maximo. Devuelve el valor más grande del array
     */
    public static int maximo(int[] array) {
    	int maximo=0;
    	int count=0;
    	while(count<array.length) {
    		if(array[count]>=maximo) {
    			maximo=array[count];
    		}
    		count++;
    	}
    	return maximo;
    }
    /**
     * Este metodo obtiene el indice del numero maximo que contiene un array
     * @param array. Array que contiene una serie de valores,
     *  de los cuales se desea conocer cual es el mayor y la
     *  posicion en la que se encuentra
     * @return indice. Devuelve la posicion en la que se encuentra
     *  el valor mas grande del array
     */
    public static int maximoIndice(int[] array) {
    	int maximo=Integer.MIN_VALUE;
    	int indice=0;
    	int count=0;
    	while(count<array.length) {
    		if(array[count]>maximo) {
    			maximo=array[count];
    			indice = count;
    		}
    		count++;
    	}
    	return indice;
    }
    /**
     * Este metodo calcula la media de todos los valores contenidos en un array
     * @param array. Array del que se desea conocer la media de todos sus valores
     * @return media. Resultado de sumar todos los valores del array y
     *  dividirlo por la cantidad de valores que hay
     */
    public static double media(int[] array) {
    	double media = 0;
    	int suma=0;
    	int count = 0;
    	while(count<array.length) {
    		suma = suma + array[count];
    		count++;
    	}
    	media=((suma*1.00)/count);
    	return media;
    }
    /**
     * Este metodo obtiene el numero minimo que contiene un array
     * @param array. Array que contiene una serie de valores,
     *  de los cuales se desea conocer cual es el menor
     * @return minimo. Devuelve el valor mas pequeño del array
     */
    public static int minimo(int[] array) {
    	int minimo=Integer.MAX_VALUE;
    	int count=0;
    	while(count<array.length) {
    		if(array[count]<minimo) {
    			minimo=array[count];
    		}
    		count++;
    	}
    	return minimo;
    }
    /**
     * Este metodo obtiene el indice del numero minimo que contiene un array
     * @param array. Array que contiene una serie de valores,
     *  de los cuales se desea conocer cual es el menor y la
     *  posición en la que se encuentra
     * @return indice. Devuelve la posicion en la que se encuentra
     *  el valor mas pequeño del array
     */
    public static int minimoIndice(int[] array) {
    	int minimo=Integer.MAX_VALUE;
    	int indice=0;
    	int count=0;
    	while(count<array.length) {
    		if(array[count]<minimo) {
    			minimo=array[count];
    			indice = count;
    		}
    		count++;
    	}
    	return indice;
    }
}
