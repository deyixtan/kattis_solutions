import java.io.*;
import java.util.*;

public class Islands3 {

    public static void dfs(char[][] vertices, boolean[][] visited, int r, int c) {
        // Set current vertex as visited
        visited[r][c] = true;

        // Search adjacent vertices
        // Top
        if (r - 1 >= 0) {
            if (vertices[r - 1][c] != 'W' && !visited[r - 1][c])
                dfs(vertices, visited, r - 1, c);
        }
        // Bottom
        if (r + 1 <= vertices.length - 1) {
            if (vertices[r + 1][c] != 'W' && !visited[r + 1][c])
                dfs(vertices, visited, r + 1, c);
        }
        // Left
        if (c - 1 >= 0) {
            if (vertices[r][c - 1] != 'W' && !visited[r][c - 1])
                dfs(vertices, visited, r, c - 1);
        }
        // Right
        if (c + 1 <= vertices[0].length - 1) {
            if (vertices[r][c + 1] != 'W' && !visited[r][c + 1])
                dfs(vertices, visited, r, c + 1);
        }
    }

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Input
        int r = io.nextInt();
        int c = io.nextInt();

        char[][] vertices = new char[r][c];
        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                vertices[i][j] = io.nextChar();
            }
        }

        // Logic
        int islands = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (vertices[i][j] == 'L' && !visited[i][j]) {
                    dfs(vertices, visited, i, j);
                    islands++;
                }
            }
        }

        // Output
        io.print(islands);
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
