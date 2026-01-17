package github.io.Gusta_code22.proxy;

import github.io.Gusta_code22.dto.PrecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "service-preco")
public interface PrecoProxy {

    @GetMapping(value = "/service-preco/{valor}/{moeda}")
    PrecoDTO getPreco(
            @PathVariable("valor") BigDecimal valor,
            @PathVariable("moeda") String moeda);
}
