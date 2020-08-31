import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer; 

public class FizzBuzz {

    public static void main(String args[]) throws IOException {
        // Used BufferReader instead of Scanner (uses regex, slows down performance)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Used StringTokenizer to break string into tokens 
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        
        // inputs
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // check whether inputs meet requirements
        if (!(1 <= x && x <= y && y <= n && n <= 100))
            return;

        // Used StringBuilder to create mutable string
        StringBuilder output = new StringBuilder();

        // loop to check i divisible by X and Y
        for (int i = 1; i <= n; i++) {
            boolean x_cond = (i % x == 0);
            boolean y_cond = (i % y == 0);

            if (x_cond && y_cond)
                output.append("FizzBuzz");
            else if (x_cond)
                output.append("Fizz");
            else if (y_cond)
                output.append("Buzz");
            else
                output.append(i);
            output.append('\n');
        }
        
        // print output
        System.out.print(output);
    }
}