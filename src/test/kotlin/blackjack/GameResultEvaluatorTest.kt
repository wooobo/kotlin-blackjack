package blackjack

import blackjack.domain.*
import blackjack.infra.AmountStatistics
import blackjack.ui.DisplayCard
import blackjack.ui.RoundResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameResultEvaluatorTest {
    @Test
    fun `라운드 카드와 점수를 알 수 있다`() {
        val players = Players.from(listOf("userA", "userB"))
        val dealer = Dealer()
        players.deal(
            players.find("userA"),
            Deck(listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE))),
        )
        players.deal(
            players.find("userB"),
            Deck(listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.ACE, Suit.SPADE))),
        )
        dealer.receive(
            Deck(listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.THREE, Suit.SPADE))),
        )
        val gameResultEvaluator = GameResultEvaluator(players, dealer)

        val actual = gameResultEvaluator.evaluateRounds()

        assertAll(
            {
                assertThat(
                    actual,
                ).contains(
                    RoundResult(
                        "userA",
                        listOf(DisplayCard.from(CardRank.TWO.name, Suit.HEART.name), DisplayCard.from(CardRank.TWO.name, Suit.SPADE.name)),
                        4,
                    ),
                )
            },
            {
                assertThat(
                    actual,
                ).contains(
                    RoundResult(
                        "userB",
                        listOf(DisplayCard.from(CardRank.ACE.name, Suit.HEART.name), DisplayCard.from(CardRank.ACE.name, Suit.SPADE.name)),
                        12,
                    ),
                )
            },
            {
                assertThat(
                    actual,
                ).contains(
                    RoundResult(
                        "딜러",
                        listOf(
                            DisplayCard.from(CardRank.TWO.name, Suit.HEART.name),
                            DisplayCard.from(CardRank.THREE.name, Suit.SPADE.name),
                        ),
                        5,
                    ),
                )
            },
        )
    }

    @ParameterizedTest
    @MethodSource("winMatchEvaluateData")
    fun `딜러와 플레이어의 최종 결과를 알 수 있다`(
        userACards: List<Card>,
        userBCards: List<Card>,
        dealerCards: List<Card>,
        userAResult: Int,
        userBResult: Int,
        dealerResult: Int,
    ) {
        val players = Players.from(listOf("userA", "userB"))
        players.betting("userA", Money(1_000))
        players.betting("userB", Money(1_000))
        val dealer = Dealer()
        players.deal(
            players.find("userA"),
            Deck(userACards),
        )

        players.deal(
            players.find("userB"),
            Deck(userBCards),
        )
        dealer.receive(
            Deck(dealerCards),
        )
        val gameResultEvaluator = GameResultEvaluator(players, dealer)

        val actual: AmountStatistics = gameResultEvaluator.finalMatchEvaluate()

        assertAll(
            { assertThat(actual.playerProfits["userA"]).isEqualTo(Money(userAResult)) },
            { assertThat(actual.playerProfits["userB"]).isEqualTo(Money(userBResult)) },
            { assertThat(actual.dealerProfit).isEqualTo(Money(dealerResult)) },
        )
    }

    companion object {
        @JvmStatic
        fun winMatchEvaluateData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(
                        Card(CardRank.TWO, Suit.HEART),
                        Card(CardRank.TWO, Suit.SPADE),
                    ),
                    listOf(
                        Card(CardRank.TWO, Suit.HEART),
                        Card(CardRank.THREE, Suit.SPADE),
                    ),
                    listOf(
                        Card(CardRank.ACE, Suit.HEART),
                        Card(CardRank.ACE, Suit.SPADE),
                    ),
                    -1000,
                    -1000,
                    2_000,
                ),// 유저 모두 패배
                Arguments.of(
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.TWO, Suit.DIAMOND),
                    ), // 버스트
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.TWO, Suit.SPADE),
                    ), // 버스트
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.TWO, Suit.SPADE),
                    ), // 버스트
                    1_000,
                    1_000,
                    0,
                ), // 딜러 버스트
                Arguments.of(
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.ACE, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.ACE, Suit.SPADE),
                    ), // 버스트
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.ACE, Suit.SPADE),
                    ), // 블랙잭
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                    ), // 20
                    -1_000,
                    1_000,
                    1000,
                ),
            )
        }
    }
}
