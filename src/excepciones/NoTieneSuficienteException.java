
package excepciones;

/**
 *
 * @author BOR
 */
public class NoTieneSuficienteException extends NoSePuedeException {

    public NoTieneSuficienteException() {
        super.mensaje = "No tiene suficiente saldo en su cuenta, pruebe con un monto menor";
    }
}
