package maxPointsOnALine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by flamearrow on 1/31/16.
 */
public class Solution3 {
    public static void main(String[] args) {
        System.out.println(gcd2(36, 27));
    }

    private static int gcd2(int a, int b) {
        int bigger = a > b ? a : b;
        int smaller = bigger == a ? b : a;
        while (smaller != 0) {
            int tmp = smaller;
            smaller = bigger % smaller;
            bigger = tmp;
        }
        return bigger;
    }

    public int maxPoints(Point[] points) {
        Map<MyLine, Set<Integer>> lines = new HashMap<>();
        int len = points.length;
        boolean allSameNodes = true;
        int ret = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (isSameNode(points[i], points[j])) {
                    continue;
                } else {
                    allSameNodes = false;
                    MyLine newLine = new MyLine(points[i], points[j]);
                    Set<Integer> pointIndicies = null;
                    if (lines.containsKey(newLine)) {
                        pointIndicies = lines.get(newLine);
                    } else {
                        pointIndicies = new HashSet<>();
                        lines.put(newLine, pointIndicies);
                    }
                    pointIndicies.add(i);
                    pointIndicies.add(j);
                    ret = Math.max(ret, pointIndicies.size());
                }
            }
        }
        return allSameNodes ? points.length : ret;
    }

    static int gcd(int a, int b) {
        while (a > 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

    private boolean isSameNode(Point p1, Point p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }
}

class MyLine {
    //ax + by + c = 0;
    private int a, b, c;

    public MyLine(Point pA, Point pB) {
        a = pB.y - pA.y;
        b = pB.x - pA.x;

        if (a != 0 && b != 0) {
            int gcd = gcd(a, b);
            a /= gcd;
            b /= gcd;
            if (a < 0) {
                a = -a;
                b = -b;
            }
            c = a * pA.x + b * pB.y;
        } else if (a == 0) {
            c = pB.x;
            a = 1;
            b = 0;
        } else if (b == 0) {
            c = pB.y;
            a = 0;
            b = 1;
        }
    }

    @Override
    public int hashCode() {
        return a << 3 + b << 2 + c << 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyLine) {
            MyLine ml = (MyLine) obj;
            return a == ml.a && b == ml.b && c == ml.c;
        } else {
            return false;
        }
    }

    private int gcd(int a, int b) {
        while (a > 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}
