package blackjack

import blackjack.domain.Card
import blackjack.domain.Suit
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest() {
    @ParameterizedTest
    @CsvSource(value = ["A, SPADE", "J, SPADE"])
    fun `카드 는 모양, 숫자형태로 생성할 수 잇다`(
        type: String,
        suit: Suit,
    ) {
        Card(type, suit)
    }
}
