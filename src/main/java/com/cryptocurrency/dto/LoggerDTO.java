package com.cryptocurrency.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class LoggerDTO {
    private final long id;
    private final String username;
    private final double percentChange;

    @Override
    public String toString() {
        return "id=" + id +
                ", username='" + username + '\'' +
                ", percentChange=" + percentChange;
    }
}
