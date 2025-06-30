import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Test15 {
    public static void main(String[] args) {
        LocalDate birthDay = LocalDate.of(2022, 01, 01);
        LocalDate today = LocalDate.now();
        
        System.out.println(ChronoUnit.YEARS.between(birthDay, today));
    }
}