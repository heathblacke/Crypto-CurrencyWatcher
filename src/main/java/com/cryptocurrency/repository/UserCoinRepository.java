package com.cryptocurrency.repository;

import com.cryptocurrency.dto.LoggerDTO;
import com.cryptocurrency.entity.UserCoin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCoinRepository extends CrudRepository<UserCoin, Integer> {

    @Query
            ("select new com.cryptocurrency.dto.LoggerDTO (c.id, u.username, (c.price/uc.registration_Price-1)*100)" +
                    "from UserCoin uc inner join uc.coin c " +
                    "inner join uc.user u " +
                    "where c.price/uc.registration_Price>1.01 " +
                    "or c.price/uc.registration_Price<0.99")
    List<LoggerDTO> changesCheck();
}
