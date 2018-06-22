
package excepciones;

/**
 *
 * @author BOR
 */
public class ErrorDatabaseException extends NoSePuedeException {

    public ErrorDatabaseException() {
        super.mensaje="Error en el sistema, intente de nuevo mas tarde";
    }
    
}
