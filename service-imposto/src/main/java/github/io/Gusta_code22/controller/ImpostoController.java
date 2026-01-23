package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.dto.ImpostoDTO;
import github.io.Gusta_code22.service.ImpostoService;
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

@Tag(name = "Impostos", description = "Endpoints para cálculo de impostos por moeda")
@RestController
@RequestMapping("/service-imposto")
@RequiredArgsConstructor
public class ImpostoController {

    private final ImpostoService service;

    @Operation(
            summary = "Calcula o imposto",
            description = "Calcula o valor do imposto sobre um montante na moeda especificada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imposto calculado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImpostoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Valor ou moeda inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no serviço")
    })
    @GetMapping(value = "/{valor}/{moeda}")
    public ImpostoDTO getImposto(
            @Parameter(description = "Valor base para cálculo do imposto (ex: 100.00)", example = "100.00")
            @PathVariable("valor") BigDecimal valor,

            @Parameter(description = "Código da moeda (ex: USD, EUR, BRL)", example = "USD")
            @PathVariable("moeda") String moeda){

        return service.calcularImposto(valor, moeda);
    }
}
