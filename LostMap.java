import java.io.*;
import java.util.*;

public class LostMap {

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Input
        int n = io.nextInt();

        PrimsMST prims = new PrimsMST();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int w = io.nextInt();
                if (i != j) {
                    prims.addEdge(i, j, w);
                }
            }
        }

        String result = prims.start();
        io.print(result);

        // Output
        io.flushOutput();
    }

    static class PrimsMST {
        private ArrayList<ArrayList<Edge>> edgeList;
        private ArrayList<Boolean> taken;
        private PriorityQueue<Edge> pq;

        public PrimsMST() {
            this.edgeList = new ArrayList<ArrayList<Edge>>();
            this.taken = new ArrayList<Boolean>();
            this.pq = new PriorityQueue<Edge>();      
        }

        public void addEdge(int src, int dest, int weight) {
            // Add default value
            // If vertex and taken at src index does not exist
            if (this.edgeList.size() <= src)
                this.edgeList.add(new ArrayList<Edge>());
            if (this.taken.size() <= src)
                this.taken.add(false);

            // Add edge to list
            this.edgeList.get(src).add(new Edge(src, dest, weight));
        }

        public void process(int vertex) {
            // Set current vertex as 'taken'
            taken.set(vertex, true);

            // Get all edges of vertex
            // Add edges whose destination not 'taken' yet
            for (int j = 0; j < edgeList.get(vertex).size(); j++) {
                Edge edge = edgeList.get(vertex).get(j);
                if (!taken.get(edge.getDest())) {
                    pq.offer(new Edge(edge.getSrc(), edge.getDest(), edge.getWeight()));
                }
            }
        }

        public String start() {
            // Output purposes
            StringBuffer sb = new StringBuffer();

            // Start from vertex 0
            process(0);

            while (!pq.isEmpty()) {
                // Get next shortest weighted edge
                // Process edge if destination not 'taken' yet
                Edge edge = pq.poll();
                if (!taken.get(edge.getDest())) {
                    process(edge.getDest());

                    // Building output
                    int src = edge.getSrc() + 1;
                    int dest = edge.getDest() + 1;
                    if (src < dest) {
                        sb.append(src);
                        sb.append(' ');
                        sb.append(dest);
                    }
                    else {
                        sb.append(dest);
                        sb.append(' ');
                        sb.append(src);
                    }
                    sb.append('\n');
                }
            }

            return sb.toString();
        }

        // Edge class
        static class Edge implements Comparable<Edge> {
            private int src;
            private int dest;
            private int weight;

            public Edge(int src, int dest, int weight) {
                this.src = src;
                this.dest = dest;
                this.weight = weight;
            }

            public int compareTo(Edge other) {
                if (this.getWeight() != other.getWeight()) {
                    return this.getWeight() - other.getWeight();
                }
                else {
                    if (this.getSrc() != other.getSrc())
                        return this.getSrc() - other.getSrc();
                    else
                        return this.getDest() - other.getDest();
                }
            }

            public int getSrc() { 
                return this.src; 
            }

            public int getDest() { 
                return this.dest; 
            }

            public int getWeight() { 
                return this.weight; 
            }
        }
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
