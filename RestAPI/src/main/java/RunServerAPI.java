
import static spark.Spark.*;

public class RunServerAPI {

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }


}
