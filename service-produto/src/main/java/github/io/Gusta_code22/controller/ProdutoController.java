package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.dto.ProdutoDto;
import github.io.Gusta_code22.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping("/{id}/{moeda}")
    public ResponseEntity<ProdutoDto> obterPrecoProduto(
            @PathVariable("id") Long id,
            @PathVariable String moeda
    ){
        ProdutoDto produtoResponse = service.obterPrecoProduto(id, moeda);
        return ResponseEntity.ok(produtoResponse);
    }
}
