package blackjack

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.Deck
import blackjack.domain.Suit
import blackjack.domain.createDeck
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.maps.shouldContainAll
import io.kotest.matchers.shouldBe

class CardGameTest : BehaviorSpec({
    Given("사용자를 가진다") {
        val userA = "userA"
        val userB = "userB"
        val users = listOf(userA, userB)
        When("사용자 이름을 입력하면") {
            val cardGame =
                CardGame.from(
                    createDeck {
                        "A" to Suit.CLUB
                        "9" to Suit.SPADE
                    },
                    users,
                )

            Then("사용자를 가진다") {
                cardGame.playersSize shouldBe 2
            }
        }
    }

    Given("두 장의 카드를 가지고 시작한다") {
        val userA = "userA"
        val userB = "userB"
        val cardGame =
            CardGame.from(
                createDeck {
                    "A" to Suit.CLUB
                    "A" to Suit.SPADE
                },
                listOf(userA, userB),
            )
        When("사용자 이름을 입력하면") {
            cardGame.initGame(userA)
            val actual = cardGame.cardsOf(userA)

            Then("사용자는 두 장의 카드를 가진다") {
                actual["A"]?.shouldContainAll(listOf("CLUB", "SPADE"))
            }
        }
    }

    Given("지정 유저의 카드를 추가한다") {
        val userA = "userA"
        val userB = "userB"
        val cardGame =
            CardGame.from(
                createDeck {
                    "J" to Suit.SPADE
                },
                listOf(userA, userB),
            )
        When("pick 호출하면") {
            cardGame.deal(userA)
            val actual = cardGame.cardsOf(userA)

            Then("1장의 카드를 가진다") {
                actual.size shouldBe 1
            }
        }
    }

    Given("유저 카드를 알 수 있다") {
        val userA = "userA"
        val userB = "userB"
        val cardGame =
            CardGame.from(
                createDeck {
                    "A" to Suit.CLUB
                    "Q" to Suit.DIAMOND
                },
                listOf(userA, userB),
            )
        When("사용자 이름을 입력하면") {
            cardGame.initGame(userA)
            val actual = cardGame.cardsOf(userA)

            Then("사용자는 두 장의 카드를 가진다") {
                actual shouldContainAll mapOf("A" to listOf("CLUB"), "Q" to listOf("DIAMOND"))
            }
        }
    }

    Given("유저 점수를 알 수 있다") {
        val userA = "userA"
        val cardGame =
            CardGame.from(
                createDeck {
                    "J" to Suit.HEART
                    "A" to Suit.HEART
                },
                listOf(userA),
            )
        cardGame.initGame(userA)
        When("사용자 이름을 입력하면") {
            val actual = cardGame.scoreOf(userA)

            Then("사용자는 두 장의 카드를 가진다") {
                actual shouldBe 21
            }
        }
    }

    Given("cachedDeck") {
        When("createDeck 호출하면") {
            val userA = "userA"
            val cardGame =
                CardGame.from(
                    Deck(
                        listOf(
                            Card(CardRank.ACE, Suit.HEART),
                            Card(CardRank.ACE, Suit.HEART),
                        ),
                        ArrayDeque(listOf(0, 1)),
                    ),
                    listOf(userA),
                )

            cardGame.deal(userA)
            cardGame.deal(userA)
            Then("카드를 가진다") {
                cardGame.scoreOf(userA) shouldBe 12
            }
        }
    }

    Given("중복 번호를 가진 카드를") {
        When("유저에게 주면") {
            val userA = "userA"
            val cardGame =
                CardGame.from(
                    Deck(
                        listOf(
                            Card(CardRank.ACE, Suit.DIAMOND),
                            Card(CardRank.ACE, Suit.HEART),
                        ),
                        ArrayDeque(listOf(0, 1)),
                    ),
                    listOf(userA),
                )

            cardGame.deal(userA)
            cardGame.deal(userA)
            Then("번호를 그룹화하고 문양들을 가진다.") {
                val actual = cardGame.result()
                actual shouldContainAll
                    mapOf(
                        userA to
                            mapOf(
                                mapOf("A" to listOf("DIAMOND", "HEART")) to 12,
                            ),
                    )
            }
        }
    }
})
