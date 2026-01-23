package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.dto.ProdutoDto;
import github.io.Gusta_code22.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Produtos", description = "Endpoints para consulta de produtos e preços por moeda")
@RestController
@RequestMapping("/service-produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @Operation(
            summary = "Obtém preço do produto",
            description = "Busca o produto pelo ID e retorna seu preço convertido para a moeda especificada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto e preço encontrados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoDto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado pelo ID"),
            @ApiResponse(responseCode = "400", description = "ID inválido ou moeda não suportada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no serviço")
    })
    @GetMapping("/{id}/{moeda}")
    public ResponseEntity<ProdutoDto> obterPrecoProduto(
            @Parameter(description = "ID do produto (ex: 1)", example = "1")
            @PathVariable("id") Long id,

            @Parameter(description = "Código da moeda de conversão (ex: USD, BRL)", example = "USD")
            @PathVariable String moeda
    ){
        ProdutoDto produtoResponse = service.obterPrecoProduto(id, moeda);
        return ResponseEntity.ok(produtoResponse);
    }
}
