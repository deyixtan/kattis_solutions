import java.io.*;
import java.util.*;

public class Workstations {

    static class Researcher implements Comparable<Researcher> {
        private int arrivalTime;
        private int stayDuration;

        public Researcher(int arrivalTime, int stayDuration) {
            this.arrivalTime = arrivalTime;
            this.stayDuration = stayDuration;
        }

        public int getArrivalTime() {
            return this.arrivalTime;
        }

        public int getDepartureTime() {
            return this.arrivalTime + this.stayDuration;
        }

        public int compareTo(Researcher other) {
            return this.arrivalTime - other.arrivalTime;
        }
    }

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);
        
        // Inputs
        int n = io.nextInt();
        int m = io.nextInt();

        // Populate researchers array
        Researcher[] researchers = new Researcher[n];
        for (int i = 0; i < n; i++) {
            int a = io.nextInt();
            int s = io.nextInt();
            researchers[i] = new Researcher(a, s);
        }
        Arrays.sort(researchers); // sort arrival time, chronological order

        // Process researchers and workstations
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int saved = 0;
        for (int i = 0; i < n; i++) {
            Researcher r = researchers[i];

            // Remove 'locked' workstations from pq
            for (int j = 0; j < pq.size(); j++) {
                if (r.getArrivalTime() > pq.peek() + m)
                    pq.poll();
                else
                    break;
            }
            
            // Assign researcher to workstation 
            if (pq.size() == 0) {
                pq.add(r.getDepartureTime());
            } else {
                if (r.getArrivalTime() >= pq.peek() && r.getArrivalTime() <= pq.peek() + m) {
                    pq.add(r.getDepartureTime());
                    pq.poll();
                    saved++;
                } else {
                    pq.add(r.getDepartureTime());
                }
            }
        }

        // Output
        io.print(saved);
        io.flushOutput();
    }

    // ############################################################################
    // ##################### Fast IO found online (modified) ######################
    // https://github.com/MrinallU/USACO-java-fastIO/blob/master/NewIOtemplate.java
    // ############################################################################
    static class FastIO {
        InputStream dis;
        PrintWriter pw;
        byte[] buffer = new byte[1 << 17];
        int pointer = 0;

        public FastIO(InputStream input, OutputStream output) throws Exception {
            dis = input;
            pw = new PrintWriter(new OutputStreamWriter(output));
        }

        int nextInt() throws Exception {
            int ret = 0;
            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = nextByte();
            }
            while (b >= '0' && b <= '9') {
                ret = 10 * ret + b - '0';
                b = nextByte();
            }
            return (negative) ? -ret : ret;
        }
        
        long nextLong() throws Exception {
            long ret = 0;
            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = nextByte();
            }
            while (b >= '0' && b <= '9') {
                ret = 10 * ret + b - '0';
                b = nextByte();
            }
            return (negative) ? -ret : ret;
        }
        
        byte nextByte() throws Exception {
            if (pointer == buffer.length) {
                dis.read(buffer, 0, buffer.length);
                pointer = 0;
            }
            return buffer[pointer++];
        }
        
        String next() throws Exception {
            StringBuffer ret = new StringBuffer();
            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            while (b > ' ') {
                ret.appendCodePoint(b);
                b = nextByte();
            }
            return ret.toString();
        }

        void print(char text) {
            pw.write(text);
        }

        void print(String text) {
            pw.write(text);
        }

        void print(int value) {
            pw.print(value);
        }

        void flushOutput() {
            pw.flush();
        }
    }
}
