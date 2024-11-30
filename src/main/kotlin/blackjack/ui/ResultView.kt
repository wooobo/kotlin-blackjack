package blackjack.ui

import blackjack.CardGame

object ResultView {
    fun initRound(resultDto: Map<String, Map<List<String>, Int>>) {
        println("처음 2장의 카드를 나누었습니다.")
        resultDto.forEach { (key, value) ->
            val cardNames = value.keys.flatten().joinToString(", ")
            println("$key: $cardNames")
        }
    }


    fun roundRepeat(
        names: List<String>,
        cardGame: CardGame,
    ) {
        println()
        for (userName in names) {
            turn(cardGame, userName)
        }
    }

    fun resultRound(resultDto: Map<String, Map<List<String>, Int>>) {
        println()
        resultDto.forEach { (key, value) ->
            val cardNames = value.keys.flatten().joinToString(", ")
            println("$key: $cardNames - 결과: ${value.values}")
        }
    }

    private fun turn(cardGame: CardGame, userName: String) {
        while (isContinue(userName)) {
            cardGame.dealCardOf(userName)

            val userHandCards = cardGame.handNameOf2(userName)
            val score = cardGame.calculateScore2(userName)

            displayUserHand(userName, userHandCards)
            displayUserScore(userName, score)

            if (cardGame.isBustOf(userName)) {
                break
            }
        }
    }

    private fun isContinue(userName: String): Boolean {
        println("$userName 는 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        val answer = readlnOrNull()
        return answer == "y"
    }

    private fun displayTurnStart(userName: String) {
        println("$userName 의 차례입니다.")
    }

    private fun displayUserHand(
        userName: String,
        hand: List<String>,
    ) {
        println("$userName 카드: ${hand.joinToString(", ")}")
    }

    private fun displayUserScore(
        userName: String,
        score: Int,
    ) {
        println("$userName 카드 합: $score")
    }
}
