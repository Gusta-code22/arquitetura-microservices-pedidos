package github.io.Gusta_code22.proxy;

import github.io.Gusta_code22.dto.ImpostoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "service-imposto")
public interface ImpostoProxy {

    @GetMapping(value = "/service-imposto/{valor}/{moeda}")
    ImpostoDTO getImposto(
            @PathVariable("valor") BigDecimal valor,
            @PathVariable("moeda") String moeda
    );
}
