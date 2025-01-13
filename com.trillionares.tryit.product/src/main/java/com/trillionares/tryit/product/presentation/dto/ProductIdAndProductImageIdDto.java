package com.trillionares.tryit.product.presentation.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductIdAndProductImageIdDto {
    private UUID productId;
    private UUID productImageId;
}
