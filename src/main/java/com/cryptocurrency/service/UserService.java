package com.cryptocurrency.service;

import com.cryptocurrency.exception.CoinNotFoundException;
import com.cryptocurrency.exception.CustomException;
import com.cryptocurrency.entity.Coin;
import com.cryptocurrency.entity.User;
import com.cryptocurrency.entity.UserCoin;
import com.cryptocurrency.repository.CoinRepository;
import com.cryptocurrency.repository.UserCoinRepository;
import com.cryptocurrency.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final CoinRepository coinRepository;
    private final UserCoinRepository userCoinRepository;

    public void registration(String username, Long coinId) {
        User user = new User();
        UserCoin userCoin = new UserCoin();
        if (userRepository.existsByUsername(username)) {
            throw new CustomException("User already exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        user.setUsername(username);
        userRepository.save(user);
        Coin coin = coinRepository.findById(coinId).orElseThrow(() -> new CoinNotFoundException("Coin not found " + coinId));
        userCoin.setCoin(coin);
        userCoin.setRegistration_Price(coin.getPrice());
        userCoin.setUser(userRepository.findUserByUsername(username));
        userCoinRepository.save(userCoin);
    }
}
