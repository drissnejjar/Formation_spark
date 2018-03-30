import org.apache.spark.{SparkConf, SparkContext}

object sparkActions {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("actions")
    val sc = new SparkContext(conf)

    val x = sc.parallelize(Array("b", "a", "c"))
    val y = x.map(z => (z,1))
    println(x.collect().mkString(", "))
    println(y.collect().mkString(", "))

  }
}