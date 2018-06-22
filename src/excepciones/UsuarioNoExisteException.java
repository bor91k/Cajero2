
package excepciones;

/**
 *
 * @author BOR
 */
public class UsuarioNoExisteException extends NoSePuedeException {

    public UsuarioNoExisteException() {
        super.mensaje = "Error, intente de nuevo.";
    }
}
