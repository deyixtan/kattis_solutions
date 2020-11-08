import java.io.*;
import java.util.*;

public class Dominos {

    public static void dfs(HashMap<Integer, LinkedList<Integer>> graph, boolean[] visited, Stack<Integer> toposort, int vertex) {
        // Set current vertex as visited
        visited[vertex] = true;

        // Search adjacent vertices
        LinkedList<Integer> edges = graph.get(vertex);
        if (edges != null) {
            for (int i = 0; i < edges.size(); i++) {
                Integer dest = edges.get(i);
                if (!visited[dest]) {
                    dfs(graph, visited, toposort, dest);
                }
            }
        }

        // Add to toposort list
        if (toposort != null)
            toposort.push(vertex);
    }

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Input + Logic
        int l = io.nextInt();
        for (int i = 0; i < l; i++) {
            int n = io.nextInt();
            int m = io.nextInt();

            // Create Graph (each node will have a linkedlist, containing their neighbours)
            HashMap<Integer, LinkedList<Integer>> g = new HashMap<Integer, LinkedList<Integer>>();
            for (int j = 0; j < m; j++) {
                int x = io.nextInt() - 1;
                int y = io.nextInt() - 1;

                LinkedList<Integer> vertexEdges;
                if (!g.containsKey(x))
                    vertexEdges = new LinkedList<Integer>();
                else
                    vertexEdges = g.get(x);

                vertexEdges.add(y);
                g.put(x, vertexEdges);
            }

            // DFS Topological Sort
            boolean[] visited = new boolean[n];
            Stack<Integer> toposort = new Stack<Integer>();
            for (int j = 0; j < n; j++) {
                if (!visited[j])
                    dfs(g, visited, toposort, j);
            }

            // Count components (reverse toposort and apply dfs)
            int components = 0;
            visited = new boolean[n]; // clear visited array
            while (!toposort.isEmpty()) {
                int v = toposort.pop();
                if (!visited[v]) {
                    dfs(g, visited, null, v);
                    components++;
                }
            }
            
            io.print(components);
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
