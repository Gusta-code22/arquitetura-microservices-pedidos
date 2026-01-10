package github.io.Gusta_code22.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(Long id) {
        super("Livro com id: " + id + " nao encontrado");
    }
}
