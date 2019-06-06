package de.haw.javanisten.task7.Enums;

import org.jetbrains.annotations.NotNull;

public class PayStation {

    private final int _ticketPrice;
    public PaymentStateMachine _state = PaymentStateMachine.empty;
    private int _value = 0;


    public PayStation(int ticketPriceInCent) {
        if (ticketPriceInCent <= 0) {
            throw new IllegalArgumentException("price must be over 0");
        }
        this._ticketPrice = ticketPriceInCent;
        this._state.enterState(this);
    }

    public PaymentStateMachine getState() {
        return this._state;
    }

    public int getPrice() {
        return this._ticketPrice;
    }

    public void addCoin(@NotNull Coin coin) {
        this._value += coin.value;
        this._checkState(coin);
    }

    private void _checkState(Coin coin) {
        this._state = PaymentStateMachine.changeState(this, coin);
        this._switchState();
    }

    private void _checkState() {
        this._state = PaymentStateMachine.changeState(this);
        this._switchState();
    }

    private void _switchState() {
        switch (this._state) {
            case correct:
                this._giveTicket();
                break;
            case tooMuch:
                this._returnMoney();
                this._checkState();
                break;
        }
    }

    public int getValue() {
        return this._value;
    }

    private void _giveTicket() {
        this._value = 0;
        this._state = PaymentStateMachine.empty;
        this._state.enterState(this);
    }

    private void _returnMoney() {
        final int diff = this._ticketPrice - this._value;

        if (diff < 0) {
            this._value = this._ticketPrice;
        }
    }
}
