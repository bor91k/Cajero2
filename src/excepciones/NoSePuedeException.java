
package excepciones;

/**
 *
 * @author BOR
 */
    public class NoSePuedeException extends RuntimeException {
        
        protected String mensaje;

        public NoSePuedeException() {
            this.mensaje = "No se puede realizar esta accion, intente de nuevo";
        }
        
        @Override
        public String getMessage() {
            return this.mensaje;
        }
    }
