package blackjack.domain

class Hand(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(pair: Card) {
        cards.add(pair)
    }

    fun size(): Int {
        return cards.size
    }

    fun score(): Int {
        return cards.fold(0) { currentScore, card ->
            currentScore + card.calculateScore(currentScore)
        }
    }

    fun totalCards(): List<Card> {
        return cards.toList()
    }
}
