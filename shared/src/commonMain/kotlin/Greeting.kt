import kotlin.random.Random

class Greeting {
    private val platform = getPlatform()
    private val rocketComponent = RocketComponent()

    fun greet(): List<String> = buildList {
        add(if (Random.nextBoolean()) "Hi!" else "Hello!")
        add("Guess what this is! > ${platform.name}!")
        add(daysPhrase())
    }
}