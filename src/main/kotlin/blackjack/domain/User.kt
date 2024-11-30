package blackjack.domain

typealias Bust = Boolean

class User(val name: Name, private val hand: Hand = Hand()) {
    fun receiveCard(card: Card) {
        hand.add(card)
    }

    fun isBust(): Bust {
        return hand.score() > BLACK_JACK_SCORE
    }


    fun cards(): List<Card> {
        return hand.totalCards()
    }

    fun score(): Int {
        return hand.score()
    }

    //    fun handSize(): Int {
//        return hand.size()
//    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
