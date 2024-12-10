package blackjack.domain

sealed class Participant(private val hand: Hand, var money : Money = Money.ZERO) {
    val bettingAmount: Money
        get() = money
    val isBust: Boolean
        get() = hand.isBust
    val totalCards: Deck
        get() = Deck(hand.totalCards)
    val bustGap: Int
        get() = hand.bustGap()

    fun score(): Score {
        return hand.score
    }

    fun receive(cards: Deck) {
        hand.add(cards.values())
    }

    fun evenMoney(): Money {
        return money.evenMoney()
    }

    fun isBlackJack() : Boolean {
        return hand.isBackJack()
    }

    fun profit(other: Money): Money {
        return other - money
    }
}
