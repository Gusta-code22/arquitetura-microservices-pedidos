package github.io.Gusta_code22.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProdutoDto {

    private Long produtoId;

    private String nome;

    private String moeda;

    private BigDecimal valorBase;

    private BigDecimal valorFinal;
}



