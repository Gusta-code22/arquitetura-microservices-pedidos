package github.io.Gusta_code22.proxy;

import github.io.Gusta_code22.dto.ImpostoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "service-imposto")
public interface ImpostoProxy {

    @GetMapping(value = "/service-imposto/{valor}/{moeda}")
    @CircuitBreaker(name = "service-preco", fallbackMethod = "fallbackB")
    @Retry(name = "service-preco")
    ImpostoDTO getImposto(
            @PathVariable("valor") BigDecimal valor,
            @PathVariable("moeda") String moeda
    );

    default String fallbackB(Exception ex) {
        return ("Fallback from B → A");
    }
}
