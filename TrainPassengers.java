import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class TrainPassengers {

    public static void main(String args[]) throws IOException {
        // Used BufferReader instead of Scanner (uses regex, slows down performance)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        
        // First row inputs
        int c = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);

        // Looping through each station
        int passengers = 0;
        int left = -1;
        int entered = -1;
        int stayed = -1;
        boolean possible = true;
        for (int i = 0; i < n; i++) {
            // Read measurements
            input = br.readLine().split(" ");
            left = Integer.parseInt(input[0]);
            entered = Integer.parseInt(input[1]);
            stayed = Integer.parseInt(input[2]);

            // Compute current passenger count after train arrived and left ith station
            passengers += (entered - left);

            // Check if train loses more passengers than what it initially has
            // Check whether passenger count exceeds capacity
            // Check if any passenger unnecessarily waited at the station
            if ((passengers < 0) || (passengers > c) || (passengers < c && stayed > 0)) {
                possible = false;
                break;
            }
        }

        // Last station checks
        // No one should be entering the train, or wait at the last station
        // No passengers should be in the train either
        if (passengers != 0 || entered != 0 || stayed != 0) {
            possible = false;
        }

        // Output based on possibility
        if (possible) {
            System.out.println("possible");
        }
        else {
            System.out.println("impossible");
        }
    }
}