package blackjack.domain

class Players(
    names: List<String>,
    val values: List<User> = names.map { User(Name(it)) },
) {
    fun dealCard(roundDeck: Deck) {
        values.forEach { user ->
            user.receiveCard(roundDeck.pop())
        }
    }

    fun size(): Int {
        return values.size
    }
}
