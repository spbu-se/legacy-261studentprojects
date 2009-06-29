package Main;

import java.io.PrintWriter;
import java.util.Random;

public class Generate {
	void generate(PrintWriter out, int n) {
		Random r = new Random();
		for (int i = 0; i < n; i++) {			
			out.println(r.nextInt()  + " " + r.nextInt() + " " + (Math.abs(r.nextInt()) % 9000000 + 1000000));
		}
	}
}