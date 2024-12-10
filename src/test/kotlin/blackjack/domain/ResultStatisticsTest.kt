package blackjack.domain

import blackjack.infra.AmountStatistics
import blackjack.infra.AmountStatisticsBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ResultStatisticsTest {
    @ParameterizedTest
    @MethodSource("statisticsMoneyData")
    fun `딜러와 플레이어의 최종 결과를 알 수 있다`(
        playerCards: List<Card>,
        dealerCards: List<Card>,
        userExpected: Int,
        dealerExpected: Int,
    ) {
        val player = Player.from("철수")
        val players = Players(listOf(player))
        val dealer = Dealer()
        player.bet(Money(1_000))
        player.receive(Deck(playerCards))
        dealer.receive(Deck(dealerCards))

        val amountStatistics: AmountStatistics = dealer.calculateStatistics(players, AmountStatisticsBuilder())

        assertThat(amountStatistics.playerProfits).isEqualTo(mapOf("철수" to Money(userExpected)))
        assertThat(amountStatistics.dealerProfit).isEqualTo(Money(dealerExpected))
    }

    companion object {
        @JvmStatic
        fun statisticsMoneyData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    1_000,
                    0,
                ), // 플레이어가 비기는 경우
                Arguments.of(
                    listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    1_000,
                    0,
                ), // 플레이어가 이기는 경우
                Arguments.of(
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    -1_000,
                    1_000,
                ),  // 플레이어가 패배한 경우
                Arguments.of(
                    listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.KING, Suit.SPADE)),
                    listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    1_500,
                    -500,
                ), // 플레이어가 블랙잭인 경우
                Arguments.of(
                    listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.KING, Suit.SPADE), Card(CardRank.KING, Suit.SPADE)),
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.KING, Suit.SPADE)),
                    -1_000,
                    1_000,
                ), // 플레이어가 버스트인 경우
                Arguments.of(
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    listOf(Card(CardRank.KING, Suit.HEART), Card(CardRank.KING, Suit.SPADE), Card(CardRank.QUEEN, Suit.SPADE)),
                    1_000,
                    0,
                ), // 딜러가 버스트인 경우
            )
        }
    }
}
