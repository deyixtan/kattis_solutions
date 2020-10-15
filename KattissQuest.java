import java.io.*;
import java.util.*;

public class KattissQuest {

    static class Quest implements Comparable<Quest> {
        private static int questCount = 0;
        private int id;
        private int energy;
        private int gold;

        public Quest(int energy, int gold) {
            this.id = ++questCount;
            this.energy = energy;
            this.gold = gold;
        }

        public int getEnergy() {
            return this.energy;
        }

        public int getGold() {
            return this.gold;
        }

        public int compareTo(Quest other) {
            int energyDiff = this.energy - other.energy;
            if (energyDiff == 0) {
                int goldDiff = this.gold - other.gold;
                if (goldDiff == 0) {
                    return this.id - other.id;
                }
                return goldDiff;
            }
            return energyDiff;
        }
    }

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Input
        int n = io.nextInt();

        // Process Input
        TreeSet<Quest> quests = new TreeSet<Quest>();
        for (int i = 0; i < n; i++) {
            // Parse each line of input
            String input = io.next();
            switch (input) {
                case "add":
                    // Add quest into pool
                    int e = io.nextInt();
                    int g = io.nextInt();
                    quests.add(new Quest(e, g));
                    break;
                case "query":
                    // Quest clearing
                    int x = io.nextInt();
                    
                    long goldTotal = 0;
                    while (!quests.isEmpty()) {
                        Quest tmp = quests.floor(new Quest(x, Integer.MAX_VALUE));
                        if (tmp != null) {
                            quests.remove(tmp);
                            x -= tmp.getEnergy();
                            goldTotal += tmp.getGold();
                        } else {
                            break;
                        }
                    }

                    // Output
                    io.print(goldTotal);
                    io.print('\n');
                    break;
            }
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

        void print(long text) {
            pw.print(text);
        }

        void flushOutput() {
            pw.flush();
        }
    }
}
