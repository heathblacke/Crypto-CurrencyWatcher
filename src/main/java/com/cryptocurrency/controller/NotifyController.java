package com.cryptocurrency.controller;

import com.cryptocurrency.dto.RegistrationDTO;
import com.cryptocurrency.exception.CoinNotFoundException;
import com.cryptocurrency.entity.Coin;
import com.cryptocurrency.service.CoinService;
import com.cryptocurrency.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = "CryptoController")
public class NotifyController {

    private final UserService userService;
    private final CoinService coinService;

    @PostMapping("/notify")
    @ApiOperation(value = "${User registration}")
    @ApiResponses(value = {//
            @ApiResponse(code = 404, message = "Coin not found"),
            @ApiResponse(code = 422, message = "Username is already in use")})
    public ResponseEntity notify(@RequestBody RegistrationDTO request) {
        final String username = request.getUsername();
        final Long coinId = request.getId();
        try {
            userService.registration(username, coinId);
        } catch (CoinNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/coins")
    @ApiOperation(value = "${getAllCoins}")

    public Iterable<Coin> getAllCrypts() {
        return coinService.getAllAvailableCoins();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "${CoinById}")
    @ApiResponses(value = {//
            @ApiResponse(code = 404, message = "Coin not found")})
    public ResponseEntity<Coin> getCurrentCoin(@PathVariable long id) {
        try {
            return ResponseEntity.ok(coinService.getCurrentCoin(id));
        } catch (CoinNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}