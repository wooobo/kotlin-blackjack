package blackjack

class CardGameTest {
//    @Test
//    fun `카드게임은 덱과 유저를 가진다`() {
//        val deck = Deck(
//            listOf(
//                Card("2", Suit.HEART),
//                Card("3", Suit.HEART),
//                Card("4", Suit.HEART),
//                Card("5", Suit.HEART),
//            )
//        )
//
//        val userA = User(Name("user1"));
//        val userB = User(Name("user2"));
//        val users = Players(listOf(userA, userB))
//        val cardGame = CardGame(deck, users)
//
//        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
//        val inputUserName = "user1,user2"
//        println(inputUserName)
//        println()
//
//        val input = inputUserName.split(",")
//        assertThat(input).containsExactly("user1", "user2")
//
//        //pobi, jason에게 2장의 나누었습니다.
//        cardGame.dealCard()
//
//        assertThat(cardGame.cardSizeOf(userA)).isEqualTo(2)
//        assertThat(cardGame.cardSizeOf(userB)).isEqualTo(2)
//
//        println("${userA.name}, ${userB.name}에게 2장의 나누었습니다.")
//        println("userA카드: ${cardGame.handNamesOf(userA).joinToString()}")
//        println("userB카드: ${cardGame.handNamesOf(userB).joinToString()}")
//        println()
//
//        // pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
//        println("${userA.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
//        val inputCard = "y"
//        println(inputCard)
//        cardGame.dealCardOf(userA)
//        println("userA카드: ${cardGame.handNamesOf(userA).joinToString()}")
//
//        println("${userA.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
//        val inputCard2 = "n"
//        println(inputCard2)
//
//        println("${userB.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
//        val inputCard3 = "n"
//        println(inputCard3)
//        println("userB카드: ${cardGame.handNamesOf(userB).joinToString()}")
//        println()
//
//        // 최종
//        println("userA카드: ${cardGame.handNamesOf(userA).joinToString()} - 결과 : ${cardGame.calculateScore(userA)}")
//        println("userB카드: ${cardGame.handNamesOf(userB).joinToString()} - 결과 : ${cardGame.calculateScore(userB)}")
//
//
//        println(cardGame)
//
//    }
}
