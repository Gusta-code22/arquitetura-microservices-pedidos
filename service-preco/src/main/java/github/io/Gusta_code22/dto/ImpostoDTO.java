package github.io.Gusta_code22.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ImpostoDTO {

    private BigDecimal valorBase;
    private BigDecimal taxa;
    private BigDecimal valorImposto;
    private BigDecimal valorFinal;
    private String ambiente;
}

