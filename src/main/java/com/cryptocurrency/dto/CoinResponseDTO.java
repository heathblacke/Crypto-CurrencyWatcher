package com.cryptocurrency.dto;

import com.cryptocurrency.entity.Coin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinResponseDTO {

    private long Id;
    private String symbol;
    @JsonProperty(value = "price_usd")
    private double price;

    public Coin toCoin() {
        Coin coin = new Coin();
        coin.setId(this.Id);
        coin.setSymbol(this.symbol);
        coin.setPrice(this.price);
        return coin;
    }
}