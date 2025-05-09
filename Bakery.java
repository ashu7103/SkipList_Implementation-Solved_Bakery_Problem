import java.util.*;

public class Bakery {
    static int solve(ArrayList<Integer> cakes){
        // TO be completed by students
        SkipList countNode =new SkipList();
        int answer = 0;


        for (Integer i : cakes) {
            int j=countNode.upperBound(i);
                if(j!=Integer.MAX_VALUE){
                    countNode.insert(i);
                    countNode.delete(j);
                }
                else{
                    countNode.insert(i);
                    answer++;
                }
        }
        return answer;
    }
    public static void main(String[] args) {
        Bakery b =new Bakery();
        ArrayList <Integer> a=new ArrayList<>();
a.add(10);
a.add(4);
a.add(5);
a.add(9);
a.add(4);
a.add(10);
a.add(2);
a.add(7);
a.add(4);
a.add(6);
System.out.println(b.solve(a));

    }
}
