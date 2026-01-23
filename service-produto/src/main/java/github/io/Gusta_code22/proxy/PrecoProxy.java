package github.io.Gusta_code22.proxy;


import github.io.Gusta_code22.dto.PrecoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "service-preco")
public interface PrecoProxy {

    @GetMapping(value = "/service-preco/{valor}/{moeda}")
    @CircuitBreaker(name = "service-produto", fallbackMethod = "fallbackA")
    @Retry(name = "service-produto")
    PrecoDTO getPreco(
            @PathVariable("valor") BigDecimal valor,
            @PathVariable("moeda") String moeda);


    default String  fallbackA(Exception ex) {
        return ("Fallback from A → B");
    }
}
