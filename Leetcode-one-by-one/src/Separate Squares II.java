import java.util.*;

class Solution {

    static class Event {
        double y;
        double x1, x2;
        int type; // +1 add, -1 remove

        Event(double y, double x1, double x2, int type) {
            this.y = y;
            this.x1 = x1;
            this.x2 = x2;
            this.type = type;
        }
    }

    static class Interval {
        double l, r;
        Interval(double l, double r) {
            this.l = l;
            this.r = r;
        }
    }

    public double separateSquares(int[][] squares) {
        List<Event> events = new ArrayList<>();

        for (int[] s : squares) {
            double x1 = s[0];
            double x2 = s[0] + s[2];
            double y1 = s[1];
            double y2 = s[1] + s[2];

            events.add(new Event(y1, x1, x2, 1));
            events.add(new Event(y2, x1, x2, -1));
        }

        events.sort(Comparator.comparingDouble(e -> e.y));

        List<Interval> active = new ArrayList<>();
        List<double[]> strips = new ArrayList<>();

        double prevY = events.get(0).y;
        double area = 0.0;

        for (Event e : events) {
            double curY = e.y;
            double dy = curY - prevY;

            if (dy > 0 && !active.isEmpty()) {
                double width = unionX(active);
                strips.add(new double[]{prevY, curY, width});
                area += width * dy;
            }

            if (e.type == 1) {
                active.add(new Interval(e.x1, e.x2));
            } else {
                for (int i = 0; i < active.size(); i++) {
                    Interval in = active.get(i);
                    if (Math.abs(in.l - e.x1) < 1e-9 &&
                        Math.abs(in.r - e.x2) < 1e-9) {
                        active.remove(i);
                        break;
                    }
                }
            }

            prevY = curY;
        }

        double target = area / 2.0;
        double acc = 0.0;

        for (double[] s : strips) {
            double y1 = s[0], y2 = s[1], w = s[2];
            double stripArea = w * (y2 - y1);

            if (acc + stripArea >= target) {
                return y1 + (target - acc) / w;
            }
            acc += stripArea;
        }

        return strips.get(strips.size() - 1)[1];
    }

    private double unionX(List<Interval> intervals) {
        intervals.sort(Comparator.comparingDouble(a -> a.l));

        double total = 0.0;
        double curL = intervals.get(0).l;
        double curR = intervals.get(0).r;

        for (int i = 1; i < intervals.size(); i++) {
            Interval in = intervals.get(i);
            if (in.l > curR) {
                total += curR - curL;
                curL = in.l;
                curR = in.r;
            } else {
                curR = Math.max(curR, in.r);
            }
        }

        total += curR - curL;
        return total;
    }
}
