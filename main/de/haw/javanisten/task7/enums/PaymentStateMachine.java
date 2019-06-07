package de.haw.javanisten.task7.enums;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public enum PaymentStateMachine {
    cancel {
        public void enterState(PayStation payStation) {
            if (payStation.getValue() > 0) {
                PaymentStateMachine._nickelback(payStation.getValue(), payStation.getCoins());
            }
        }
    },
    empty {
        public void enterState(@NotNull PayStation payStation) {
            System.out.printf("Welcome! Please insert $%.2f.\n", (payStation.getPrice() / 100.f));
        }
    },
    partly {
        public void enterState(PayStation payStation) {
            final float diff = payStation.getPrice() - payStation.getValue();

            if (diff > 0) {
                System.out.printf("Please insert $%.2f.\n", (diff / 100));
            }
        }
    },
    tooMuch {
        public void enterState(PayStation payStation) {
            final int diff = payStation.getValue() - payStation.getPrice();
            if (diff > 0) {
                PaymentStateMachine._nickelback(diff);
            }
        }
    },
    correct {
        public void enterState(PayStation payStation) {
            System.out.printf("Your Ticket: \"%s\" for $%.2f.\n", payStation.getTicketType().getName(), (payStation.getPrice() / 100.f));
        }
    };

    private static void _addCoin(@NotNull Coin coin) {
        System.out.printf("You have inserted a %s.\n", coin.name);
    }

    public static PaymentStateMachine changeState(@NotNull PayStation payStation, @NotNull Coin coin) {
        PaymentStateMachine._addCoin(coin);
        return changeState(payStation);
    }

    public static PaymentStateMachine changeState(@NotNull PayStation payStation) {
        final int diff = payStation.getPrice() - payStation.getValue();
        if (diff == 0) {
            PaymentStateMachine.correct.enterState(payStation);
            return PaymentStateMachine.correct;
        }
        if (diff > 0) {
            PaymentStateMachine.partly.enterState(payStation);
            return PaymentStateMachine.partly;
        }
        PaymentStateMachine.tooMuch.enterState(payStation);
        return PaymentStateMachine.tooMuch;
    }

    private static void _nickelback(int diff, @NotNull ArrayList<Coin> coins) {
        final StringBuilder nickelback = new StringBuilder();
        nickelback.append('[');

        for (Coin coin : coins) {
            nickelback.append(coin.name);
            nickelback.append(", ");
        }

        nickelback.deleteCharAt(nickelback.length() - 1);
        nickelback.deleteCharAt(nickelback.length() - 1);
        nickelback.append(']');
        System.out.printf("There is your nickelback of $%.2f: %s.\n", (diff / 100.f), nickelback.toString());
    }

    private static void _nickelback(int diffRest) {
        ArrayList<Coin> coins = new ArrayList<>();
        while (diffRest > 0) {
            Coin coin;
            if (Coin.dollar.value <= diffRest) {
                coin = Coin.dollar;
            } else if (Coin.halfDollar.value <= diffRest) {
                coin = Coin.halfDollar;
            } else if (Coin.quarter.value <= diffRest) {
                coin = Coin.quarter;
            } else if (Coin.dime.value <= diffRest) {
                coin = Coin.dime;
            } else {
                coin = Coin.nickel;
            }

            coins.add(coin);
            diffRest -= coin.value;
        }

        PaymentStateMachine._nickelback(diffRest, coins);
    }

    public void enterState(PayStation payStation, Coin coin) {
        PaymentStateMachine._addCoin(coin);
        enterState(payStation);
    }

    public abstract void enterState(PayStation payStation);
}