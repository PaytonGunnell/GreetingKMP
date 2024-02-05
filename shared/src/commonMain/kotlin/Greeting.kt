import app.cash.sqldelight.db.SqlDriver
import com.example.Database
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import comexample.DatabaseQueries
import comexample.HockeyPlayer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.kmp.testing.CurrentRuntime
import org.kmp.testing.determineCurrentRuntime
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class Greeting {
    private val platform = getPlatform()
    private val rocketComponent = RocketComponent()
    private val runtime = determineCurrentRuntime()

    @NativeCoroutines
    fun greet(): Flow<String> = flow {
        emit(if (Random.nextBoolean()) "Hi!" else "Hello!")
        delay(1.seconds)
        emit("Guess what this is! > ${platform.name.reversed()}")
        delay(1.seconds)
        emit(daysPhrase())
        delay(1.seconds)
        emit(rocketComponent.launchPhrase())
        delay(1.seconds)
        emit("Name: ${runtime.name}, Version: ${runtime.version}, ${runtime}")
    }

    fun savePlayerName(input: String, driver: SqlDriver) {
        val database = Database(driver)
        val playerQueries: DatabaseQueries = database.databaseQueries

        println(playerQueries.selectAll().executeAsList())

        playerQueries.insert(player_number = 10, full_name = input)
        println(playerQueries.selectAll().executeAsList())

//        val player = HockeyPlayer(10, "Ronald McDonald")
//        playerQueries.insertFullPlayerObject(player)
    }
}