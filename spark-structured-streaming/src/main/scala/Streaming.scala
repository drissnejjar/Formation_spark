import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object Streaming {

  def main(args: Array[String]): Unit = {

   // val input = args(0)

/*
    val spark: SparkSession = SparkSession.builder()
      // .master("local")
      .appName("streaming")
      .getOrCreate()
*/
    val conf = new SparkConf()
        .setMaster("local[2]")
      .setAppName("actions")
    val sc = new SparkContext(conf)
    val sql = new SQLContext(sc)

    val x = sc.parallelize(Array("b", "a", "c"))
    val y = x.map(z => (z,1))
    println("Res de x: " + x.collect().mkString(","))
    println("Res de y: " + y.collect().mkString(","))

    val resourcePath = getClass.getResource("/by-day").getPath
    val resourcePathJson = getClass.getResource("/by-day-json/2015-summary.json").getPath
    println("resourcePath " + resourcePath)

    val byDayFileRDD = sc.textFile(resourcePath)

    //byDayFileRDD.foreach(println)

    val byDayFileDF = sql.read.option("inferSchema",true).option("header",true).csv(resourcePath)

    byDayFileDF.printSchema()

    byDayFileDF.show(10)

    byDayFileDF.coalesce(4).write.mode("Overwrite").parquet("byDayFileParquet")



/*
    val staticDataFrame = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load(args(0))

    staticDataFrame.createOrReplaceTempView("retail_data")
    val staticSchema = staticDataFrame.schema

    import org.apache.spark.sql.functions.{col, window}
    staticDataFrame
      .selectExpr("CustomerId",
        "(UnitPrice * Quantity) as total_cost",
        "InvoiceDate")
      .groupBy(
        col("CustomerId"), window(col("InvoiceDate"), "1 day"))
      .sum("total_cost")
      .show(5)


    val streamingDataFrame = spark.readStream.schema(staticSchema).option("maxFilesPerTrigger", 1).format("csv").option("header", "true").load(args(0))

    val purchaseByCustomerPerHour = streamingDataFrame.selectExpr("CustomerId", "(UnitPrice * Quantity) as total_cost", "InvoiceDate").groupBy(col("CustomerId"), window(col("InvoiceDate"), "1 day")).sum("total_cost")

    spark.conf.set("spark.sql.shuffle.partitions", "5")

    purchaseByCustomerPerHour.writeStream.format("console").queryName("customer_purchases").outputMode("complete").start()

    spark.sql(""" SELECT * FROM customer_purchases ORDER BY `sum(total_cost)` DESC """).show(5)

    //  purchaseByCustomerPerHour.writeStream.format("console").queryName("customer_purchases").outputMode("complete").start()
*/

  }
}