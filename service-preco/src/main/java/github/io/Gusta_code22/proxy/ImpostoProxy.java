package github.io.Gusta_code22.proxy;

import github.io.Gusta_code22.dto.ImpostoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "imposto-service", url = "localhost:8003")
public interface ImpostoProxy {

    @GetMapping(value = "/imposto/{valor}/{moeda}")
    ImpostoDTO getImposto(
            @PathVariable("valor") BigDecimal valor,
            @PathVariable("moeda") String moeda
    );
}
