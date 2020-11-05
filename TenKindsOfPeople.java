import java.io.*;
import java.util.*;

public class TenKindsOfPeople {

    public static void dfs(int[][] vertices, int[][] visited, int key, int region, int r, int c) {
        // Set current vertex as visited
        visited[r][c] = region;

        // Search adjacent vertices
        // Top
        if (r - 1 >= 0) {
            if (vertices[r - 1][c] == key && visited[r - 1][c] == 0)
                dfs(vertices, visited, key, region, r - 1, c);
        }
        // Bottom
        if (r + 1 <= vertices.length - 1) {
            if (vertices[r + 1][c] == key && visited[r + 1][c] == 0)
                dfs(vertices, visited, key, region, r + 1, c);
        }
        // Left
        if (c - 1 >= 0) {
            if (vertices[r][c - 1] == key && visited[r][c - 1] == 0)
                dfs(vertices, visited, key, region, r, c - 1);
        }
        // Right
        if (c + 1 <= vertices[0].length - 1) {
            if (vertices[r][c + 1] == key && visited[r][c + 1] == 0)
                dfs(vertices, visited, key, region, r, c + 1);
        }
    }

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Input
        int r = io.nextInt();
        int c = io.nextInt();

        // Set up vertices
        int[][] vertices = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                vertices[i][j] = Character.getNumericValue(io.nextChar());
            }
        }

        // Query
        int[][] visited = new int[r][c];
        int regions = 0;
        
        int n = io.nextInt();
        for (int i = 0; i < n; i++) {
            int r1 = io.nextInt() - 1;
            int c1 = io.nextInt() - 1;
            int r2 = io.nextInt() - 1;
            int c2 = io.nextInt() - 1;

            // Populate unvisited into "regions"
            if (visited[r1][c1] == 0)
                dfs(vertices, visited, vertices[r1][c1], ++regions, r1, c1);
            if (visited[r2][c2] == 0)
                dfs(vertices, visited, vertices[r2][c2], ++regions, r2, c2);

            // Verify input
            if (visited[r1][c1] == visited[r2][c2]) {
                if (vertices[r1][c1] == 0)
                    io.print("binary");
                else if (vertices[r1][c1] == 1)
                    io.print("decimal");
            } else {
                io.print("neither");
            }
            io.print('\n');
        }

        // Output
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

        char nextChar() throws Exception {
            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            return (char)b;
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
