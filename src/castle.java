/*
ID: enzocam1
LANG: JAVA
PROG: castle
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class castle {
    private static final int WEST=1,NORTH=2,EAST=4,SOUTH=8;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("castle.in"));
             PrintWriter pw = new PrintWriter("castle.out")) {
            int cols, rows;
            String ln;
            StringTokenizer st;
            ln = br.readLine();
            st = new StringTokenizer(ln);
            cols = Integer.parseInt(st.nextToken());
            rows = Integer.parseInt(st.nextToken());
            Module[][] inp = new Module[rows][cols];
            for (int i = 0; i < rows; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < cols; j++) {
                    inp[i][j] = new Module(i, j, Integer.parseInt(st.nextToken()));
                }
            }
            Room room;
            List<Room> rooms = new ArrayList<>();
            Module m, cur;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    m = inp[i][j];
                    if (m.assigned) continue;
                    room = new Room();
                    Queue<Module> q = new LinkedList<>();
                    q.add(m);
                    boolean w,n,e,s;
                    while (!q.isEmpty()) {
                        cur = q.remove();
                        if (cur.assigned) continue;
                        w = (cur.val&1)==0;
                        n = (cur.val&2)==0;
                        e = (cur.val&4)==0;
                        s = (cur.val&8)==0;
                        if (w) q.add(inp[cur.r][cur.c - 1]);
                        if (n) q.add(inp[cur.r - 1][cur.c]);
                        if (e) q.add(inp[cur.r][cur.c + 1]);
                        if (s) q.add(inp[cur.r + 1][cur.c]);
                        room.add(cur);
                        cur.assigned = true;
                    }
                    rooms.add(room);
                }
            }
            Collections.sort(rooms);
            Module moduleBreak = null;
            int newRoomSize = 0;
            int wallBreak = -1;
            Module mN, mE;
            int sz;
            for (int j = 0; j < cols; j++) {
                for (int i = rows - 1; i >= 0; i--) {
                    m = inp[i][j];
                    if (i > 0) {
                        mN = inp[i - 1][j];
                        if (m.room != mN.room && (sz=m.room.size() + mN.room.size()) > newRoomSize) {
                            wallBreak = NORTH;
                            moduleBreak = m;
                            newRoomSize = sz;
                        }
                    }
                    if (j < cols - 1) {
                        mE = inp[i][j + 1];
                        if (m.room != mE.room && (sz=m.room.size() + mE.room.size()) > newRoomSize) {
                            wallBreak = EAST;
                            moduleBreak = m;
                            newRoomSize = sz;
                        }
                    }
                }
            }

            int nRooms = rooms.size();
            int lRoom = rooms.get(nRooms - 1).size();
            assert moduleBreak != null;
            int lRoomRem = newRoomSize;
            int mRow = moduleBreak.r + 1;
            int mCol = moduleBreak.c + 1;
            char wb;
            switch (wallBreak) {
                case EAST:
                    wb='E';
                    break;
                case NORTH:
                    wb='N';
                    break;
                case SOUTH:
                    wb='S';
                    break;
                case WEST:
                    wb='W';
                    break;
                default:
                    wb='X';
                    break;
            }
            pw.println(nRooms);
            pw.println(lRoom);
            pw.println(lRoomRem);
            pw.println(mRow + " " + mCol + " " + wb);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class Room implements Comparable<Room>{
        Set<Module> modules;
        Room() {
            modules = new HashSet<>();
        }
        int size() {
            return modules.size();
        }
        public void add(Module m) {
            m.room = this;
            modules.add(m);
        }

        @Override
        public int compareTo(Room o) {
            if (size() < o.size()) return -1;
            if (size() > o.size()) return 1;
            return 0;
        }
    }

    private static class Module {
        int r, c, val;
        Room room;
        boolean assigned;
        Module(int row, int col, int val) {
            r = row;
            c = col;
            this.val = val;
            assigned = false;
        }

    }
}
