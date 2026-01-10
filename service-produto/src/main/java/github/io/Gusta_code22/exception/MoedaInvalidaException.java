package github.io.Gusta_code22.exception;

public class MoedaInvalidaException extends RuntimeException {
    public MoedaInvalidaException(String message) {
        super("Moeda invalida. O Campo Moeda nao pode estar vazio");
    }
}
