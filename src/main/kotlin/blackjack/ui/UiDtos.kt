package blackjack.ui

typealias UserName = String
typealias UserNames = List<UserName>
typealias UserMore = Boolean
typealias Score = Int
typealias CardRank = String
typealias CardSuit = String
typealias UserCards = Map<CardRank, List<CardSuit>>
typealias CardScore = Int

fun UserCards.toPrettyString(): String {
    return this.entries.joinToString(separator = ", ") { (rank, suits) ->
        suits.joinToString(separator = ", ") { "${CardType.toDisplayNameOf(rank)}${UiSuit.toDisplayNameOf(it)}" }
    }
}

enum class UiSuit(val displayName: String) {
    HEART(displayName = "하트"),
    DIAMOND(displayName = "다이아몬드"),
    CLUB(displayName = "클로버"),
    SPADE(displayName = "스페이드"), ;

    companion object {
        fun toDisplayNameOf(other: CardSuit): String {
            return entries.find { it.name == other }?.displayName ?: throw IllegalArgumentException("잘못된 카드 모양입니다")
        }
    }
}

enum class CardType(val displayName: String) {
    ACE(displayName = "A"),
    JACK(displayName = "J"),
    QUEEN(displayName = "Q"),
    KING(displayName = "K"),
    TWO(displayName = "2"),
    THREE(displayName = "3"),
    FOUR(displayName = "4"),
    FIVE(displayName = "5"),
    SIX(displayName = "6"),
    SEVEN(displayName = "7"),
    EIGHT(displayName = "8"),
    NINE(displayName = "9"),
    ;

    companion object {
        fun toDisplayNameOf(rank: CardRank): String {
            return entries.find { it.name == rank }?.displayName ?: throw IllegalArgumentException("잘못된 카드 숫자입니다")
        }
    }
}

enum class UIMatchType(val displayName: String) {
    WIN(displayName = "승"),
    LOSS(displayName = "패"),
    DRAW(displayName = "무"),
}

data class FinalWinnerResults(
    val dealerResult: DealerResult,
    val playerResults: Map<UserName, UIMatchType>,
)

data class DealerResult(
    val wins: Int = 0,
    val losses: Int = 0,
    val draws: Int = 0,
)


data class RoundResult(val userName: UserName, val cards: Map<CardRank, List<CardSuit>>, val score: CardScore) {
    companion object {
        fun from(userName: UserName, cards: Map<CardRank, List<CardSuit>>, score: CardScore): RoundResult {
            return RoundResult(userName, cards, score)
        }
    }
}