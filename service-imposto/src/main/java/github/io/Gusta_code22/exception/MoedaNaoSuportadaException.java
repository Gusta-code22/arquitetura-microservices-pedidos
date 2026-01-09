package github.io.Gusta_code22.exception;

public class MoedaNaoSuportadaException extends RuntimeException {
    public MoedaNaoSuportadaException(String moeda) {
        super("Moeda nao suportada: " + moeda);
    }
}
