import org.apache.spark.sql.SparkSession

object HiveJDBCConnection {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local")
      .appName("hive-jdbc")
      .getOrCreate()


    val url = "jdbc:hive2://vps493708.ovh.net:10000/default"
    val driver = "org.apache.hive.jdbc.HiveDriver"
    val user = "hive"
    val password = "hive"


    spark.read.format("jdbc").options(Map(
      "url" -> url,
      "driver" -> driver,
      "user" -> user,
      "password" -> password,
      "dbtable" -> "src"))
      .load()
      .show

  }
}


