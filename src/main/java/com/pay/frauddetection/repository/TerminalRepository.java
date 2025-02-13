package com.pay.frauddetection.repository;

import com.pay.frauddetection.model.Terminal;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TerminalRepository {

    public Optional<Terminal> get(int id) {
        return Optional.of(new Terminal(id, 5));
    }
}
