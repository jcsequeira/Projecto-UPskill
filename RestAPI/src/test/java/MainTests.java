import java.time.LocalDate;

public class MainTests {
    public static void main(String[] args) {
        if ((LocalDate.of(3000,01,01)) instanceof LocalDate) {
            System.out.println("YES");;

        }
        //System.out.println(LocalDate.of(3000,01,01));
    }
}
