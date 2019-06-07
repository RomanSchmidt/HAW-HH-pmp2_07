package de.haw.javanisten.task7.enums;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PayStation {

    private final TicketType _ticket;
    private final ArrayList<Coin> _coins = new ArrayList<>();
    public PaymentStateMachine _state = PaymentStateMachine.empty;
    private int _value = 0;

    public PayStation(TicketType ticket) {
        this._ticket = ticket;
        this._state.enterState(this);
    }

    public PaymentStateMachine getState() {
        return this._state;
    }

    public int getPrice() {
        return this._ticket.getPrice();
    }

    public TicketType getTicketType() {
        return this._ticket;
    }

    public void addCoin(@NotNull Coin coin) {
        this._value += coin.value;
        this._coins.add(coin);
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
                this._clear();
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

    public ArrayList<Coin> getCoins() {
        return this._coins;
    }

    private void _clear() {
        this._coins.clear();
        this._value = 0;
        this._state = PaymentStateMachine.empty;
        this._state.enterState(this);
    }

    public void cancel() {
        this._state = PaymentStateMachine.cancel;
        this._state.enterState(this);
        this._clear();
    }

    private void _returnMoney() {
        final int diff = this._ticket.getPrice() - this._value;

        if (diff < 0) {
            this._value = this._ticket.getPrice();
        }
    }
}
