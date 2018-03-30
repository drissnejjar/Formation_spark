import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;


public final class ReverseUdf extends UDF {
    public Text evaluate(final Text s) {
        if (s == null) {
            return null;
        }
        String reverse = new StringBuilder(s.toString()).toString();
        return new Text(reverse);
    }
}