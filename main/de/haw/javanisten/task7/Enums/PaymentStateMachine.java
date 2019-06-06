package de.haw.javanisten.task7.Enums;

import org.jetbrains.annotations.NotNull;

public enum PaymentStateMachine {
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
            final int diff = payStation.getPrice() - payStation.getValue();

            if (diff > 0) {
                System.out.printf("There is your nickelback of $%.2f.\n", (diff / 100.f));
            }
        }
    },
    correct {
        public void enterState(PayStation payStation) {
            System.out.printf("Your Ticket for $%.2f.\n", (payStation.getPrice() / 100.f));
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

    public void enterState(PayStation payStation, Coin coin) {
        PaymentStateMachine._addCoin(coin);
        enterState(payStation);
    }

    public abstract void enterState(PayStation payStation);
}