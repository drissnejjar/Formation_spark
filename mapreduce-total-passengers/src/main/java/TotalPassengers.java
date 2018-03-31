import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TotalPassengers {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: TotalPassengers <input path> <output path>");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(TotalPassengers.class);
        job.setJobName("Total passengers");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(TotalPassengersMapper.class);
        job.setReducerClass(TotalPassengersReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static class TotalPassengersReducer extends Reducer<Text, IntWritable, Text, IntWritable> {


        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {

            int total = 0;
            for (IntWritable value : values) {
                total = total + value.get();
            }
            context.write(key, new IntWritable(total));
        }
    }

    public static class TotalPassengersMapper extends Mapper<LongWritable, Text, Text, IntWritable> {


        public void map(LongWritable key, Text value, Reducer.Context context) throws IOException, InterruptedException {

            String line = value.toString();
            String[] lineSplit = line.split(",");
            String date = lineSplit[3];
            String totalNumberPassengersPerRequest = lineSplit[7];
            String totalSimilarRequests = lineSplit[10];

            Integer total = (new Integer(totalNumberPassengersPerRequest)) * (new Integer(totalSimilarRequests));

            context.write(new Text(date), new IntWritable(total));
        }
    }


}


