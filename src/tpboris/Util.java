
package tpboris;

import excepciones.TeEstasZarpandoException;

/**
 *
 * @author Bor
 */
public abstract class Util {
    
    private static boolean esNumericoInt(String cadena){
        //ve si la cadena es solo numeros tratando de parsearla a int
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
        private static boolean esNumericoDouble(String cadena){
        //ve si la cadena puede parsearse a Double
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
    private static boolean mideCinco(String cadena){
        //ve si la cadena tiene 5 caracteres
        return cadena.length()==5;
    }
        
    public static boolean validarIngreso(String strNumero) {
        //ve si la cadena tiene 5 caracteres numericos
        return Util.esNumericoInt(strNumero) && Util.mideCinco(strNumero);
    }

    public static boolean validarEfectivo(String strEfectivo) {
        //valida que se haya ingresado un numero con formato correspondiente a saldo y que sea positivo
        //la comprobacion de si es multiplo de 20 no se da aca porque esto valida lo ingresado
        // en el campo de texto, podria ser que la misma interfaz grafica se use en cajeros a los que
        // aceptan billetes distintos a los de 20
        
        boolean respuesta=false;
        
        if (esNumericoDouble(strEfectivo)){
            
            double doubEfectivo = Double.parseDouble(strEfectivo);
            
            //que el efectivo este en un rango coherente
            if (doubEfectivo>0 && doubEfectivo<Double.MAX_VALUE){
                respuesta = true;
            }
        }
        return respuesta;
    }
    
    public static boolean noExcedeMaximo(double doubSaldoInicial, double doubEfectivo) throws TeEstasZarpandoException {
        
        boolean respuesta = false;
        
        if (doubSaldoInicial+doubEfectivo>Double.MAX_VALUE){
            throw new TeEstasZarpandoException();
        }else{
            respuesta = true;
        }
        
        return respuesta;
    }

    /*
    public static class TeEstasZarpandoException extends RuntimeException {
        
        private final String mensaje;

        public TeEstasZarpandoException() {
            this.mensaje = "Estas excediendo la cantidad que soporta nuestro sistema";
        }

        @Override
        public String getMessage() {
            return mensaje;
        }
    }
    */
}