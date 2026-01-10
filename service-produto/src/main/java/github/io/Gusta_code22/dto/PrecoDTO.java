package github.io.Gusta_code22.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PrecoDTO {

    BigDecimal valorBase;

    BigDecimal valorImposto;
    BigDecimal valorFinal;
    String ambiente;
    String moeda;
}
