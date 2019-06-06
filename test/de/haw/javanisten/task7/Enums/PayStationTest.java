package de.haw.javanisten.task7.Enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayStationTest {

    @Test
    void addCoin100() {
        PayStation station = new PayStation(100);
        assertEquals(0, station.getValue());
        assertEquals(PaymentStateMachine.empty, station.getState());
        station.addCoin(Coin.dime);
        assertEquals(Coin.dime.value, station.getValue());
        assertEquals(PaymentStateMachine.partly, station.getState());
        station.addCoin(Coin.quarter);
        assertEquals(Coin.dime.value + Coin.quarter.value, station.getValue());
        assertEquals(PaymentStateMachine.partly, station.getState());
        station.addCoin(Coin.dollar);
        assertEquals(0, station.getValue());
        assertEquals(PaymentStateMachine.empty, station.getState());
    }
}