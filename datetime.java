import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) {
		LocalDateTime nyt = LocalDateTime.now();
		System.out.println(nyt);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = nyt.format(format);
		System.out.println(formatDateTime);
	}
}
