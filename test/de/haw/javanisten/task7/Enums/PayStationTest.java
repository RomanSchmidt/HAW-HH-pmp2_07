package de.haw.javanisten.task7.Enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayStationTest {

    @Test
    void gotoHell() {
        PayStation station = new PayStation(TicketType.ToHell);
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

    @Test
    void reset() {
        PayStation station = new PayStation(TicketType.ToHell);
        assertEquals(0, station.getValue());
        assertEquals(PaymentStateMachine.empty, station.getState());
        station.addCoin(Coin.quarter);
        assertEquals(Coin.quarter.value, station.getValue());
        assertEquals(PaymentStateMachine.partly, station.getState());
        station.addCoin(Coin.halfDollar);
        assertEquals(Coin.halfDollar.value + Coin.quarter.value, station.getValue());
        assertEquals(PaymentStateMachine.partly, station.getState());
        station.cancel();
        assertEquals(0, station.getValue());
        assertEquals(PaymentStateMachine.empty, station.getState());
    }
}