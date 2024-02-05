//import app.cash.sqldelight.db.SqlDriver
//import com.example.Database
//
//expect class DriverFactory {
//    fun createDriver(): SqlDriver
//}
//
//fun createDatabase(driverFactory: DriverFactory): Database {
//    val driver = driverFactory.createDriver()
//    val a = AppDatabase()
//    val database = Database(driver)
//
//    // Do more work with the database (see below).
//}