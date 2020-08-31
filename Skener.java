import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer; 

public class Skener {

    public static void main(String args[]) throws IOException {
        // Used BufferReader instead of Scanner (uses regex, slows down performance)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Used StringTokenizer to break string into tokens 
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        
        // First row inputs
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int zr = Integer.parseInt(st.nextToken());
        int zc = Integer.parseInt(st.nextToken());

        // check whether inputs meet requirements
        // r and c between 1 - 50
        if (!(r >= 1 && r <= 50 && c >= 1 && c <= 50))
            return;
        // zr and zc between 1 - 5
        if (!(zr >= 1 && zr <= 5 && zc >= 1 && zc <= 5))
            return;

        // Used StringBuilder to create mutable string
        StringBuilder output = new StringBuilder();

        // 'Enlarge' logic
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            char[] line = st.nextToken().toCharArray();

            for (int ii = 0; ii < zr; ii++) {
                for (int j = 0; j < c; j++) {
                    for (int jj = 0; jj < zc; jj++) {
                        output.append(line[j]);
                    }
                }
                output.append('\n');
            }
        }

        System.out.println(output);
    }
}