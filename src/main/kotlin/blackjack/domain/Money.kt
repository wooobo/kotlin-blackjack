package blackjack.domain

@JvmInline
value class Money(val value: Int) {
    fun add(other: Money): Money {
        return Money(value + other.value)
    }

    fun evenMoney(): Money {
        return Money((value * EVEN_PRODUCT).toInt())
    }

    operator fun times(multiplier: Double): Money {
        return Money((value * multiplier).toInt())
    }

    operator fun minus(other: Money): Money {
        return Money(value - other.value)
    }

    operator fun plus(other: Money): Money {
        return Money(value + other.value)
    }

    operator fun unaryMinus(): Money {
        return Money(-value)
    }

    fun toNegative(): Money {
        if (value < 0) {
            return this
        }
        return Money(-value)
    }

    companion object {
        const val EVEN_PRODUCT = 1.5
        val ZERO: Money = Money(0)
    }
}
