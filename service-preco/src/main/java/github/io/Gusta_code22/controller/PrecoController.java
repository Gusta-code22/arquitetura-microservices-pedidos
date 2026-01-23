package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.dto.PrecoDTO;
import github.io.Gusta_code22.service.PrecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Tag(name = "Preços", description = "Endpoints para cálculo de preços por moeda")
@RestController
@RequestMapping("/service-preco")
@RequiredArgsConstructor
public class PrecoController {

    private final PrecoService service;

    @Operation(
            summary = "Calcula o preço final",
            description = "Converte um valor para a moeda especificada e retorna o preço final calculado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço calculado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PrecoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Valor ou moeda inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no serviço")
    })
    @GetMapping(value = "/{valor}/{moeda}")
    public PrecoDTO getPreco(
            @Parameter(description = "Valor a ser convertido (ex: 100.00)", example = "100.00")
            @PathVariable("valor") BigDecimal valor,

            @Parameter(description = "Código da moeda de destino (ex: USD, EUR, BRL)", example = "USD")
            @PathVariable("moeda") String moeda){

        return service.calcularPrecoFinal(valor, moeda);
    }
}
