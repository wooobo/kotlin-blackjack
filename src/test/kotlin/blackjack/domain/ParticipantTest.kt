package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `플레이어의 첫 두 장 카드 합이 21이면 블랙잭 판정`() {
        val player = Player.from("player")
        player.receive(Deck(listOf(Card(CardRank.ACE, Suit.CLUB), Card(CardRank.KING, Suit.CLUB))))

        assertThat(player.isBlackJack()).isTrue()
    }

    @Test
    fun `딜러의 첫 두 장 카드 합이 21이면 블랙잭 판정`() {
        val dealer = Dealer()
        dealer.receive(Deck(listOf(Card(CardRank.ACE, Suit.CLUB), Card(CardRank.KING, Suit.CLUB))))

        assertThat(dealer.isBlackJack()).isTrue()
    }

    @Test
    fun `두장 이상 카드가 있을 때 블랙잭 판정이 아님`() {
        val player = Player.from("player")
        player.receive(Deck(listOf(Card(CardRank.QUEEN, Suit.CLUB), Card(CardRank.NINE, Suit.CLUB), Card(CardRank.TWO, Suit.CLUB))))

        assertThat(player.isBlackJack()).isFalse()
    }
}
