package com.cryptocurrency.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_coin")
public class UserCoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "coin")
    private Coin coin;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    @Column(name = "registration_price")
    private double registration_Price;
}
