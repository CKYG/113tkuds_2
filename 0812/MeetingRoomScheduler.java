import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minEnd = new PriorityQueue<>();
        for (int[] itv : intervals) {
            if (!minEnd.isEmpty() && minEnd.peek() <= itv[0]) {
                minEnd.poll();
            }
            minEnd.offer(itv[1]);
        }
        return minEnd.size();
    }

    static class Meeting {
        int id, s, e;
        int duration() { return e - s; }
        Meeting(int id, int s, int e) { this.id = id; this.s = s; this.e = e; }
    }

    public static class ScheduleResult {
        public int totalTime;
        public List<List<int[]>> roomSchedules;
        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("totalTime=").append(totalTime).append("\n");
            for (int r = 0; r < roomSchedules.size(); r++) {
                sb.append("Room ").append(r).append(": ");
                List<int[]> lst = roomSchedules.get(r);
                sb.append("[");
                for (int i = 0; i < lst.size(); i++) {
                    int[] iv = lst.get(i);
                    sb.append("[").append(iv[0]).append(",").append(iv[1]).append("]");
                    if (i+1 < lst.size()) sb.append(", ");
                }
                sb.append("]\n");
            }
            return sb.toString();
        }
    }

    public static ScheduleResult scheduleMaxTotalTime(int[][] intervals, int rooms) {
        int n = intervals == null ? 0 : intervals.length;
        if (n == 0 || rooms <= 0) {
            ScheduleResult empty = new ScheduleResult();
            empty.totalTime = 0;
            empty.roomSchedules = new ArrayList<>();
            for (int i = 0; i < Math.max(rooms, 0); i++) empty.roomSchedules.add(new ArrayList<>());
            return empty;
        }

        Meeting[] ms = new Meeting[n];
        for (int i = 0; i < n; i++) ms[i] = new Meeting(i, intervals[i][0], intervals[i][1]);

        class Ev { int t, type, id; Ev(int t,int type,int id){this.t=t;this.type=type;this.id=id;} }
        List<Ev> evs = new ArrayList<>(2*n);
        for (Meeting m : ms) {
            evs.add(new Ev(m.e, 0, m.id));
            evs.add(new Ev(m.s, 1, m.id));
        }
        evs.sort((a,b)-> (a.t!=b.t) ? Integer.compare(a.t,b.t) : Integer.compare(a.type,b.type));

        TreeSet<Integer> active = new TreeSet<>((i, j) -> {
            if (i == j) return 0;
            int di = ms[i].duration(), dj = ms[j].duration();
            if (di != dj) return Integer.compare(di, dj);
            return Integer.compare(i, j);
        });

        boolean[] selected = new boolean[n];
        Arrays.fill(selected, true);

        for (Ev ev : evs) {
            int id = ev.id;
            if (ev.type == 0) {
                active.remove(id);
            } else {
                active.add(id);
                if (active.size() > rooms) {
                    int drop = active.first();
                    active.remove(drop);
                    selected[drop] = false;
                }
            }
        }

        int total = 0;
        List<Meeting> chosen = new ArrayList<>();
        for (Meeting m : ms) if (selected[m.id]) {
            total += m.duration();
            chosen.add(m);
        }

        chosen.sort((a,b)-> (a.s!=b.s)? Integer.compare(a.s,b.s) : Integer.compare(a.e,b.e));
        List<List<int[]>> roomSchedules = new ArrayList<>();
        for (int i = 0; i < rooms; i++) roomSchedules.add(new ArrayList<>());
        PriorityQueue<int[]> roomsHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        int nextRoomId = 0;
        for (Meeting m : chosen) {
            while (!roomsHeap.isEmpty() && roomsHeap.peek()[0] <= m.s) {
                break;
            }
            if (!roomsHeap.isEmpty() && roomsHeap.peek()[0] <= m.s) {
                int[] top = roomsHeap.poll();
                int roomId = top[1];
                roomSchedules.get(roomId).add(new int[]{m.s, m.e});
                roomsHeap.offer(new int[]{m.e, roomId});
            } else if (nextRoomId < rooms) {
                int roomId = nextRoomId++;
                roomSchedules.get(roomId).add(new int[]{m.s, m.e});
                roomsHeap.offer(new int[]{m.e, roomId});
            } else {
                throw new IllegalStateException("Room assignment exceeded capacity");
            }
        }
        List<List<int[]>> trimmed = new ArrayList<>();
        for (List<int[]> lst : roomSchedules) if (!lst.isEmpty()) trimmed.add(lst);

        ScheduleResult res = new ScheduleResult();
        res.totalTime = total;
        res.roomSchedules = trimmed;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}}));
        System.out.println(minMeetingRooms(new int[][]{{1,5},{8,9},{8,9}}));

        int[][] ex1 = {{1,4},{2,3},{4,6}};
        ScheduleResult r1 = scheduleMaxTotalTime(ex1, 1);
        System.out.println("N=1 result:\n" + r1);

        int[][] ex2 = {{0,3},{1,2},{2,5},{4,7},{6,9}};
        ScheduleResult r2 = scheduleMaxTotalTime(ex2, 2);
        System.out.println("N=2 result:\n" + r2);
    }
}