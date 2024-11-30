package blackjack

import blackjack.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱 생성`() {
        val actual =
            createDeck {
                cards {
                    "A" to Suit.CLUB
                    "2" to Suit.SPADE
                }
            }

        val expected = Deck(listOf(Card("A", Suit.CLUB), Card("2", Suit.SPADE)))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `덱에서 카드를 뽑는다`() {
        val deck: Deck =
            createDeck {
                cards {
                    "A" to Suit.CLUB
                    "2" to Suit.SPADE
                }
            }

        val actual = deck.pop()

        val expected = Card("A", Suit.CLUB)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `덱에서 카드를 뽑을 때 덱이 비어있으면 에러`() {
        val deck = DeckBuilder.createDeck()

        assertThatIllegalArgumentException().isThrownBy {
            repeat(55) {
                deck.pop()
            }
        }
    }
}
