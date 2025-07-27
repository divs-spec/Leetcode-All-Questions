/*class Solution {
    public int lastRemaining(int n) {
        boolean left = true;
        int remaining = n;
        int step = 1;
        int head = 1;
        while (remaining > 1) {
            if (left || remaining % 2 ==1) {
                head = head + step;
            }
            remaining = remaining / 2;
            step = step * 2;
            left = !left;
        }
        return head;
    }
}*/
class Solution {
    public int lastRemaining(int n) {
        if(n == 1){
            return 1;
        }
        return 2 * (1 + n/2 - lastRemaining(n/2));
    }
}
/** class Solution {
    public int lastRemaining(int n) {
        int head=1;
        int step=1;
        boolean left=true;
        int remaining=n;
        while(remaining>1){
            if(left||remaining%2==1)
                head+=step;
            step*=2;
            remaining/=2;
            left=!left;
        }
        return head;
    }
} */
/**class Solution {
    public int lastRemaining(int n) {
        int h = 1;
        int s = 1;
        boolean l = true;

        while (n > 1) {
            if (l || n % 2 == 1) {
                h += s;
            }

            s *= 2;
            n /= 2;
            l = !l;
        }

        return h;
    }
}*/

// class Solution {
//     public int lastRemaining(int n) {

//         LinkedList<Integer> a = new LinkedList<>();
//         for (int j = 1; j <= n; j++) a.add(j);

//         int d = 1;

//         while (a.size() > 1) {
//             if (d == 1) {
//                 ListIterator<Integer> it = a.listIterator();
//                 boolean r = true;

//                 while (it.hasNext()) {
//                     it.next();
//                     if (r) it.remove();
//                     r = !r;
//                 }

//                 d = 2;
//                 // i = a.size() - 1;

//             } else {
//                 ListIterator<Integer> it = a.listIterator(a.size());
//                 boolean r = true;

//                 while (it.hasPrevious()) {
//                     it.previous();
//                     if (r) it.remove();
//                     r = !r;
//                 }

//                 d = 1;
//                 // i = 0;
//             }
//         }

//         return a.get(0);
//     }
// }


// class Solution {
//     public int lastRemaining(int n) {

//         ArrayList<Integer> a = new ArrayList();
//         for(int i = 1; i<=n; i++) a.add(i);
//         int d = 1, i = 0;

//         while(a.size()>1){
//             if (d == 1){
//                 // System.out.println("d = 1 : " + "for i " +i + " " +a.get(i));
//                 a.remove(i--);

//                 if(i+2 > a.size()-1){
//                     d = 2;
//                     i = a.size()-1;
//                 }else i+=2;
//             } else {

//                 // System.out.println("d = 2: " + "for i " +i + " " + a.get(i));
//                 a.remove(i);

//                 if(i-2 < 0){
//                     d = 1;
//                     i = 0;
//                 }else i-=2;
//             }
//         }

//         return a.get(0);
        
//     }
// }
