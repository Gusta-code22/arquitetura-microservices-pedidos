package github.io.Gusta_code22.service;

import github.io.Gusta_code22.dto.ImpostoDTO;
import github.io.Gusta_code22.enviroment.InstanceInformationService;
import github.io.Gusta_code22.exception.MoedaNaoSuportadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service

public class ImpostoService {

    @Autowired
    private InstanceInformationService informationService;

    public ImpostoDTO calcularImposto(BigDecimal valorInicial, String moeda){


        BigDecimal taxa = switch (moeda) {
            case "BRL" -> BigDecimal.valueOf(0.12);
            case "USD" -> BigDecimal.valueOf(0.08);
            case "EUR" -> BigDecimal.valueOf(0.10);
            case null, default -> throw new MoedaNaoSuportadaException(moeda);
        };

        BigDecimal imposto = valorInicial.multiply(taxa);

        BigDecimal valorFinal = valorInicial.add(imposto);

        ImpostoDTO impostoResponse = new ImpostoDTO();
        impostoResponse.setTaxa(taxa);
        impostoResponse.setValorImposto(imposto);
        impostoResponse.setValorFinal(valorFinal);
        impostoResponse.setValorBase(valorInicial);
        impostoResponse.setAmbiente(informationService.retrieveServerPort());
        return impostoResponse;


    }
}
