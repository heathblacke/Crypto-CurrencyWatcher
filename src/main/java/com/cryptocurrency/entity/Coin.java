package com.cryptocurrency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@RequiredArgsConstructor

@Table(name = "coins")
public class Coin {

    @Id
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price")
    private double price;

    @JsonIgnore
    @OneToMany(mappedBy = "coin")
    @ToString.Exclude
    private Set<UserCoin> userCoins = new HashSet<>();
}
