package com.ain.fourbyfour.util.idxGenerator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;

public class UserIdGenerator implements IdentifierGenerator {

    private Random random = new Random();
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return "u" + String.valueOf(System.currentTimeMillis()) + String.format("%03d", random.nextInt(999));
    }
}
