import org.apache.spark.sql.SparkSession

object connectionToSQLite {

  def main(args: Array[String]): Unit = {
    /*val driver =  "org.sqlite.JDBC"
    val path = "root@vps493710.ovh.net:/root/formation_spark/sqlLite/input/my-sqlite.db"
    val url = s"jdbc:sqlite:/${path}"
    val tablename = "flight_info"

    val jdbcHostname = "vps493710.ovh.net"
    val jdbcPort = 3306
    val jdbcDatabase ="<database>"
    // Create the JDBC URL without passing in the user and password parameters.
    val jdbcUrl = s"jdbc:mysql://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}

      // Create a Properties() object to hold the parameters.
      import java.util.Properties
      val connectionProperties = new Properties()

      connectionProperties.put("user", s"${jdbcUsername}")
    connectionProperties.put("password", s"${jdbcPassword}")


    Class.forName("org.org.mariadb.jdbc.Driver")
    Class.forName("com.mysql.jdbc.Driver")
*/

    val spark: SparkSession = SparkSession.builder()
      .master("local")
      .appName("jdbc_connection")
      .getOrCreate()

    spark-shell \
    --driver-class-path "/usr/share/java/mysql-connector-java.jar" \
      --jars "/usr/share/java/mysql-connector-java.jar"


    val pgDF = spark.read.format("jdbc").option("driver", "org.postgresql.Driver").option("url", "jdbc:postgresql://database_server").option("dbtable", "schema.tablename").option("user", "username").option("password","my-secret-password").load()
   /*
    import java.sql.DriverManager
    val connection = DriverManager.getConnection(url)
    connection.isClosed()
    connection.close()
    */
  }
}