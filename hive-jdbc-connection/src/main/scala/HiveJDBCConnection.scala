import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

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

    val conf = new SparkConf()
      .setAppName("kafkawordcount")
      .setMaster("local[2]")

    val sc = new SparkContext(conf)

    val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc)

    sqlContext.sql("select * from retail").show

    val retailHiveTable = spark.read.format("jdbc").options(Map(
      "url" -> url,
      "driver" -> driver,
      "user" -> user,
      "password" -> password,
      "dbtable" -> "retail")).load



    // retailHiveTable.printSchema()
    retailHiveTable.createOrReplaceTempView("retail1")
    // retailHiveTable.select("retail.invoiceno").show
    // retailHiveTable.sqlContext.sql("select * from retail1")
    // retailHiveTable.head(10)
    spark.sql("select * from retail1").show
  }
}


