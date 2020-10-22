import java.io.*;
import java.util.*;

public class WeakVertices {

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Logic
        while (true) {
            int n = io.nextInt();
            if (n == -1)
                break;

            // Vertices array that store each vertex status (if it is weak)
            // 0 = weak, 1 = strong
            int[] vertices = new int[n];

            // Populate Adjacent Matrix
            // Initialise Vertices array
            int[][] adjMatrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjMatrix[i][j] = io.nextInt();
                }
                vertices[i] = 0; // initially treat all vertices as weak
            }

            // Loop through each vertex
            // Check against each neighbour and see if they form a triangle
            // Update Vertices array
            for (int i = 0; i < n; i++) {
                if (vertices[i] == 0) {
                    for (int j = 0; j < n; j++) {
                        boolean exit = false;
                        for (int k = 0; k < n; k++) {
                            if (adjMatrix[i][j] == 1 && adjMatrix[j][k] == 1 && adjMatrix[k][i] == 1) {
                                vertices[i] = 1;
                                vertices[j] = 1;
                                vertices[k] = 1;
                                exit = true;
                                break;
                            }
                        }

                        // If all 3 vertices is able to form a triangle
                        // They can already be deemed as strong
                        // Therefore, move on to next vertex
                        if (exit)
                            break;
                    }
                }

                // Output if vertex is still weak
                if (vertices[i] == 0) {
                    io.print(i);
                    io.print(' ');
                }
            }
            io.print('\n');
        }
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
