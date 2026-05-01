package github.io.Gusta_code22.service;

import github.io.Gusta_code22.dto.ImpostoDTO;
import github.io.Gusta_code22.dto.PrecoDTO;
import github.io.Gusta_code22.enviroment.InstanceInformationService;
import github.io.Gusta_code22.proxy.ImpostoProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PrecoService {


    private final ImpostoProxy impostoProxy;

    private final InstanceInformationService informationService;

    public PrecoDTO calcularPrecoFinal(BigDecimal valor, String moeda){

        ImpostoDTO imposto = impostoProxy.getImposto(valor, moeda);

        String port = informationService.retrieveServerPort();
        String host = informationService.retrieveInstanceInfo();

        PrecoDTO precoFinal = new PrecoDTO();
        precoFinal.setValorFinal(imposto.getValorFinal());
        precoFinal.setAmbiente(String.format(
                "HOST: %s | PORT: %s | VERSION: Kube-V2 | Service-imposto HOST: %s",
                host, port, imposto.getAmbiente()));
        precoFinal.setMoeda(moeda);
        precoFinal.setValorImposto(imposto.getValorImposto());
        precoFinal.setValorBase(valor);

        return precoFinal;




    }
}
