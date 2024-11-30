package blackjack

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class HandTest {
    @Test
    fun `카드를 추가할 수 있다`() {
        val hand = Hand()

        hand.add(Card("A", Suit.SPADE))
        hand.add(Card("A", Suit.SPADE))

        assertThat(hand.size()).isEqualTo(2)
    }

    @ParameterizedTest
    @MethodSource("rankTestData")
    fun `점수를 계산한다`(
        hand: Hand,
        expected: Int,
    ) {
        assertThat(hand.score()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun rankTestData(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Hand().apply {
                        add(Card("A", Suit.SPADE))
                        add(Card("A", Suit.SPADE))
                    },
                    12,
                ),
                Arguments.of(
                    Hand().apply {
                        add(Card("A", Suit.SPADE))
                        add(Card("A", Suit.SPADE))
                        add(Card("J", Suit.SPADE))
                    },
                    22,
                ),
                Arguments.of(
                    Hand().apply {
                        add(Card("J", Suit.SPADE))
                        add(Card("J", Suit.SPADE))
                        add(Card("A", Suit.SPADE))
                    },
                    21,
                ),
                Arguments.of(
                    Hand().apply {
                        add(Card("9", Suit.SPADE))
                        add(Card("J", Suit.SPADE))
                    },
                    19,
                ),
            )
    }
}
