package com.cryptocurrency.service;

import com.cryptocurrency.dto.LoggerDTO;
import com.cryptocurrency.repository.UserCoinRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ScheduledService {

    private static final Logger log = LoggerFactory.getLogger(ScheduledService.class);
    final long[] coinsId = {90, 80, 48543};
    @Autowired
    private final CoinService coinService;
    private final UserCoinRepository coins;

    @Scheduled(fixedRate = 60000)
    public void updateCoinPrices() {
        for (long i : coinsId) {
            coinService.updatePrice(i);
        }
        printData();
    }

    public void printData() {
        List<LoggerDTO> response = coins.changesCheck();
        if (!response.isEmpty()) {
            for (LoggerDTO info : response) {
                log.warn(info.toString());
            }
        }
    }
}
