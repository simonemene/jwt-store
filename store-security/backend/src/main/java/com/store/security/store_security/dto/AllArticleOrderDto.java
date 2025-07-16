package com.store.security.store_security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AllArticleOrderDto {

	private ArticleDto articleDto;

	private Integer quantity;
}
