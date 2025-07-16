package com.store.security.store_security.controller;

import com.store.security.store_security.dto.AllStockDto;
import com.store.security.store_security.dto.ArticleDto;
import com.store.security.store_security.dto.StockArticleDto;
import com.store.security.store_security.dto.StockDto;
import com.store.security.store_security.service.IStockService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final IStockService stockService;


    @PatchMapping("/{id}/{quantity}")
    public ResponseEntity<StockArticleDto> addArticleQuantity(@PathVariable("id") Long id,
            @Min(value = 1,message = "Quantity for article invalid") @PathVariable("quantity") Integer quantity) {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.saveArticleQuantity(id, quantity));
    }

    @PatchMapping("{id}/decrement/{quantity}")
    public ResponseEntity<StockArticleDto> decrementArticle(@PathVariable("id") Long id,
            @Min(value = 1,message = "Quantity for article invalid") @PathVariable("quantity") Integer quantity) {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.decrementArticle(id, quantity));
    }

    @GetMapping
    public ResponseEntity<StockDto> getAllStock() {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getStockByArticle(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStockByArticle(id));
    }

    @PostMapping
    public ResponseEntity<ArticleDto> addArticle(@RequestBody @Valid ArticleDto articleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.loadArticle(articleDto));
    }


}
