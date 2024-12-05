package blackjack.domain

open class Player(val playerName: EntrantName, private val hand: Hand = Hand()) {
    val isBust: Boolean
        get() = hand.isBust
    val totalCards: Deck
        get() = Deck(hand.totalCards)
    val name: String
        get() = playerName.value
    val bustGap: Int
        get() = hand.bustGap()

    fun receive(cards: Deck) {
        hand.add(cards.values())
    }

    fun score(): Score {
        return hand.score
    }

    fun same(other: String): Boolean {
        return playerName.value == other
    }

    companion object {
        fun from(name: String): Player {
            return Player(playerName = EntrantName(name))
        }
    }
}
