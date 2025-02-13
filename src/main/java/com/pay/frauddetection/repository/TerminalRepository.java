package com.pay.frauddetection.repository;

import com.pay.frauddetection.model.Terminal;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TerminalRepository {

    public Optional<Terminal> get(int id) {

        // mocked response to test various cases
        if(id == 1) {
            return Optional.of(new Terminal(id, 5));
        } else {
            return Optional.empty();
        }
    }
}
