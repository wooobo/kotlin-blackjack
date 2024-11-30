package blackjack.domain

data class Card(
    private val rankName: String,
    val suit: Suit,
    private val rankType: RankType = RankType.from(rankName),
) {
    fun calculateScore(currentScore: Int): Int {
        return rankType.calculateScore(currentScore)
    }

    fun name(): String {
        return "$rankName${suit.displayName}"
    }
}
