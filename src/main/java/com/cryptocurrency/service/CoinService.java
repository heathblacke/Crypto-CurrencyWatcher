package com.cryptocurrency.service;

import com.cryptocurrency.dto.CoinResponseDTO;
import com.cryptocurrency.exception.CoinNotFoundException;
import com.cryptocurrency.entity.Coin;
import com.cryptocurrency.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableJpaRepositories
@RequiredArgsConstructor

public class CoinService {
    @Autowired
    private final CoinRepository coinRepository;
    private final RestTemplate restTemplate;

    public void updatePrice(long id) {
        CoinResponseDTO[] forObject = restTemplate.getForObject("https://api.coinlore.net/api/ticker/?id=" + id, CoinResponseDTO[].class);
        assert forObject != null;
        if (forObject[0] != null) {
            coinRepository.save(forObject[0].toCoin());
        }
    }

    public Iterable<Coin> getAllAvailableCoins() {
        return coinRepository.findAll();
    }

    public Coin getCurrentCoin(long id) {
        return coinRepository.findById(id).orElseThrow(() -> new CoinNotFoundException("Coin not found " + id));
    }
}