package github.io.Gusta_code22.service;

import github.io.Gusta_code22.dto.ProdutoDto;
import github.io.Gusta_code22.entity.Produto;
import github.io.Gusta_code22.exception.MoedaInvalidaException;
import github.io.Gusta_code22.exception.ProdutoNaoEncontradoException;
import github.io.Gusta_code22.proxy.PrecoProxy;
import github.io.Gusta_code22.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final PrecoProxy proxy;

    public ProdutoDto obterPrecoProduto(Long id, String moeda){
        if (moeda == null || moeda.isBlank()) {
            throw new MoedaInvalidaException(moeda);
        }

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        BigDecimal valorBase = produto.getValorBase();

        BigDecimal valorFinal = proxy.getPreco(valorBase, moeda).getValorFinal();

        ProdutoDto produtoResponse = new ProdutoDto();
        produtoResponse.setNome(produto.getNome());
        produtoResponse.setProdutoId(produto.getId());
        produtoResponse.setValorFinal(valorFinal);
        produtoResponse.setValorBase(valorBase);
        produtoResponse.setMoeda(moeda);


        return produtoResponse;


    }
}
