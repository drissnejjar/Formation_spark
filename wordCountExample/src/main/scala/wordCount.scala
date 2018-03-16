
import org.apache.spark.{SparkConf, SparkContext}

object wordCount {

  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf()
      .setMaster("local")
      .setAppName("SparkWordCount"))

    val resourcesPath = "/2010-12-01.csv"

    val file = getClass.getResource(resourcesPath).getPath
    val fileRDD = sc.textFile(file)


    // split each document into words  val file= sc.textFile(args(0))
    val counts = fileRDD.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    counts.foreach(println)
    counts.saveAsTextFile("wordCountOutput")

    // System.out.println(counts)
  }
}
