package blackjack.domain

sealed class RankType private constructor() {
    abstract fun calculateScore(currentScore: Int): Int

    data object Ace : RankType() {
        override fun calculateScore(currentScore: Int): Int {
            return if (currentScore + 11 > 21) 1 else 11
        }
    }

    data object Face : RankType() {
        override fun calculateScore(currentScore: Int): Int = 10
    }

    data class Number(val value: Int) : RankType() {
        init {
            require(value in 2..9) { "Number 는 2~9 사이의 값 이어야 합니다.: $value" }
        }

        override fun calculateScore(currentScore: Int): Int = value
    }

    companion object {
        fun from(value: String): RankType {
            return when (value) {
                "A" -> Ace
                "J", "Q", "K" -> Face
                "2", "3", "4", "5", "6", "7", "8", "9" -> Number(value.toInt())
                else -> throw IllegalArgumentException("RankType 은 A, J, Q, K, 2~9 만 가능합니다.: $value")
            }
        }
    }
}

//
// enum class RankType(val scoreCalculator: (Int) -> Int) {
//    ACE({ score -> if (score + 11 > 21) 1 else 11 }), // Ace의 점수 계산
//    NUMBER({ value -> value }),                      // 숫자 카드 점수 그대로
//    FACE({ _ -> 10 });                               // 그림 카드(J, Q, K)는 10
//
//    companion object {
//        fun from(value: String): RankType {
//            return when (value) {
//                "A" -> ACE
//                "J", "Q", "K" -> FACE
//                else -> NUMBER
//            }
//        }
//    }
// }
