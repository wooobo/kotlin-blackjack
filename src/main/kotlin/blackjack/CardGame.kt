package blackjack

import blackjack.domain.Deck
import blackjack.domain.Players
import blackjack.domain.User

data class CardGame(private val deck: Deck, private val players: Players) {
    fun dealCard() {
        val cardCount = players.size()
        repeat(2) {
            val roundCards: Deck = deck.popCards(cardCount)
            players.dealCard(roundCards)
        }
    }

    private fun dealCardOf(userA: User) {
        val card = deck.pop()
        userA.receiveCard(card)
    }

    fun dealCardOf(userA: String) {
        dealCardOf(findUser(userA))
    }

//    fun handNamesOf(user: User): List<String> {
//        return user.cards().map { it.name() }
//    }

    fun handNameOf2(userName: String): List<String> {
        return findUser(userName).cards().map { it.name() }
    }

    private fun findUser(userName: String): User {
        return players.values.find { it.name.value == userName } ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")
    }

//    fun calculateScore(user: User): Int {
//        return user.score()
//    }

    fun calculateScore2(user: String): Int {
        return findUser(user).score()
    }

    fun roundPlayers(): Map<String, Map<List<String>, Int>> {
        return players.values.associate { user ->
            user.name.value to mapOf(user.cards().map { card -> card.name() } to user.score())
        }
    }

    fun isBustOf(userName: String): Boolean {
        return findUser(userName).isBust()
    }
}
