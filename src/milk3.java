/*
ID: enzocam1
LANG: JAVA
PROG: milk3
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class milk3 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
             PrintWriter pw = new PrintWriter("milk3.out")) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            Bucket a = new Bucket(Integer.parseInt(st.nextToken()));
            Bucket b = new Bucket(Integer.parseInt(st.nextToken()));
            Bucket c = new Bucket(Integer.parseInt(st.nextToken()));
            c.state = c.cap;
            endAmounts = new HashSet<>();
            states = new HashSet<>();
            BucketState state = new BucketState(a, b, c);
            Queue<BucketState> q = new LinkedList<>();
            q.add(state);
            visit(q);
            List<Integer> amounts = new ArrayList<>(endAmounts);
            Collections.sort(amounts);
            for (int i = 0; i < amounts.size(); i++) {
                pw.print(amounts.get(i));
                if (i < amounts.size() - 1) pw.print(" ");
            }
            pw.println();
            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Set<Integer> endAmounts;
    public static Set<BucketState> states;

    public static void visit(Queue<BucketState> q) {
        BucketState state;
        do {
            if (q.isEmpty()) return;
            state = q.remove();
        } while (states.contains(state));
        states.add(state);

        BucketState newState;
        if (canPourFrom(state.a)) {
            if (canPourTo(state.b)) {
                newState = pour(state.a, state.b, state);
                if (newState.isDone()) {endAmounts.add(newState.c.state);}
                q.add(newState);
            }
            if (canPourTo(state.c)) {
                newState = pour(state.a, state.c, state);
                if (newState.isDone()) {endAmounts.add(newState.c.state);}
                q.add(newState);
            }
        }
        if (canPourFrom(state.b)) {
            if (canPourTo(state.a)) {
                newState = pour(state.b, state.a, state);
                if (newState.isDone()) {endAmounts.add(newState.c.state);}
                q.add(newState);
            }
            if (canPourTo(state.c)) {
                newState = pour(state.b, state.c, state);
                if (newState.isDone()) {endAmounts.add(newState.c.state);}
                q.add(newState);
            }
        }
        if (canPourFrom(state.c)) {
            if (canPourTo(state.b)) {
                newState = pour(state.c, state.b, state);
                if (newState.isDone()) {endAmounts.add(newState.c.state);}
                q.add(newState);
            }
            if (canPourTo(state.a)) {
                newState = pour(state.c, state.a, state);
                if (newState.isDone()) {endAmounts.add(newState.c.state);}
                q.add(newState);
            }
        }
        visit(q);
    }

    public static BucketState pour(Bucket from, Bucket to, BucketState state) {
        int amount = Math.min(from.state, to.cap - to.state);
        Bucket fromCpy = new Bucket(from.cap, from.state - amount);
        Bucket toCpy = new Bucket(to.cap, to.state + amount);
        BucketState newState = new BucketState(state.a, state.b, state.c);
        if (from == state.a) {
            newState.a = fromCpy;
            if (to == state.b) {
                newState.b = toCpy;
                newState.c = new Bucket(state.c);
            }
            else {
                newState.c = toCpy;
                newState.b = new Bucket(state.b);
            }
        }
        else if (from == state.b) {
            newState.b = fromCpy;
            if (to == state.a) {
                newState.a = toCpy;
                newState.c = new Bucket(state.c);
            }
            else {
                newState.c = toCpy;
                newState.a = new Bucket(state.a);
            }
        }
        else {
            newState.c = fromCpy;
            if (to == state.a) {
                newState.a = toCpy;
                newState.b = new Bucket(state.b);
            }
            else {
                newState.b = toCpy;
                newState.a = new Bucket(state.a);
            }
        }
        return newState;
    }

    public static boolean canPourTo(Bucket to) {
        return to.state < to.cap;
    }

    public static boolean canPourFrom(Bucket from) {
        return from.state > 0;
    }

    public static boolean canPour(Bucket from, Bucket to) {
        return canPourFrom(from) && canPourTo(to);
    }
}

class BucketState {
    Bucket a, b, c;
    public BucketState(Bucket a, Bucket b, Bucket c){this.a=a;this.b=b;this.c=c;}
    public BucketState(){};
    public boolean isDone(){return a.state == 0;}
    public boolean equals(Object o) {if (!(o instanceof BucketState)) return false;
        BucketState ob = (BucketState) o;
        return a.equals(ob.a) && b.equals(ob.b) && c.equals(ob.c);
    }

    public int hashCode() {
        int iHashCode = 17;
        iHashCode = 31 * iHashCode + a.cap;
        iHashCode = 31 * iHashCode + a.state;
        iHashCode = 31 * iHashCode + b.cap;
        iHashCode = 31 * iHashCode + b.state;
        iHashCode = 31 * iHashCode + c.cap;
        iHashCode = 31 * iHashCode + c.state;
        return iHashCode;
    }
}

class Bucket {
    int cap, state;
    public Bucket(int cap, int state){this.cap = cap;this.state = state;}
    public Bucket(int cap) {this(cap, 0);}
    public Bucket(Bucket b){this(b.cap, b.state);}
    public boolean equals(Object o) {
        if (!(o instanceof Bucket)) return false;
        Bucket ob = (Bucket) o;
        return cap == ob.cap && state == ob.state;
    }
//    public int hashCode() {
//        int iHashCode = 17;
//        iHashCode = 31 * iHashCode + cap;
//        iHashCode = 31 * iHashCode + state;
//        return iHashCode;
//    }
}
