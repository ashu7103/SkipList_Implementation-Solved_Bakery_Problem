import java.util.ArrayList;
import java.util.Collections;


public class SkipList {

     class insertHelper{   //me
        int currH;
        ArrayList<SkipListNode> linklist=new ArrayList<SkipListNode>();
    }

        public SkipListNode head;
        public SkipListNode tail;
        public int height;
        public Randomizer randomizer;
        private final int NEG_INF = Integer.MIN_VALUE;
        private final int POS_INF = Integer.MAX_VALUE;
        private int len=0;   //me

        SkipList(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            this.head = new SkipListNode(NEG_INF,1);
            this.tail = new SkipListNode(POS_INF,1);
            this.head.next.add(0,this.tail);
            this.tail.next.add(0,null);
            this.height = 1;
            this.randomizer = new Randomizer();
        }

        /**
         * @param num
         * @return
         */
        public boolean delete(int num){
            // TO be completed by students
            System.out.println("delete :"+num);
            boolean b=false;


            if(this.search(num)){
                ArrayList<SkipListNode> a=full_list_before_Delete(num);
                int temp_list_size =a.size();
                int node_height= a.get(temp_list_size-1).next.get(0).height;
                for(int i=0;i<(node_height);i++){

                    // a.get(temp_list_size-1-i).next.get(i)=a.get(temp_list_size-1-i).next.get(i).next.get(i) ;
                    a.get(temp_list_size-1-i).next.set(i,a.get(temp_list_size-1-i).next.get(i).next.get(i) );

                }
                while(head.next.get(height-1)==tail && height>1){
                    tail.next.remove(height-1);
                    head.next.remove(height-1);
                    height--;
                    tail.height--;
                    head.height--;
                   
                }
                // if(height>2){
                //     while(head.next.get(height-2).next.get(height-2)==tail  && height>2){     
                //             //    && height>2
                //         tail.next.remove(height-2);
                //         head.next.remove(height-2);
                //         height--;
                //         tail.height--;
                //         head.height--;
                        
                    
                //     }
                // }
                len--;
                return true;
            }
            else{
                return b;
            }
            
            
        }

        

        
        //maiye need to use  toliner()
        public boolean search(int num){
            // TO be completed by students
            System.out.println("search : "+num );
            SkipListNode temp=head;
            for(int i=this.height-1; i>=0;i--){
                
                while(num>temp.next.get(i).value){
                    temp=temp.next.get(i);
                }
            }
            return temp.next.get(0)!=null && temp.next.get(0).value==num;
        }
       
        private  SkipListNode search_just_before_next(int num){
            // just before wali node even node is equal it give before point
            SkipListNode temp=head;
            for(int i=this.height-1; i>=0;i--){
                
                while(num>temp.next.get(i).value){
                    temp=temp.next.get(i);
                }
            }
            return temp;
        } 
        
        private ArrayList<SkipListNode> full_list_before_Delete (int num){
            insertHelper a=new insertHelper();
            ArrayList<SkipListNode> l=new ArrayList<SkipListNode>();
            SkipListNode temp=head;
            for(int i=this.height-1; i>=0;i--){
                
                while(num>temp.next.get(i).value){
                    temp=temp.next.get(i);
                }
                //check here
                // if(num==temp.value || num==temp.next.get(i).value){
                //     while( num==temp.next.get(i).value){
                //         temp=temp.next.get(i);
                //     }
                    
                
                l.add(temp);

            }

            return l;
        }
        
        private insertHelper list_of_before (int num){
            insertHelper a=new insertHelper();
            ArrayList<SkipListNode> l=new ArrayList<SkipListNode>();
            SkipListNode temp=head;
            for(int i=this.height-1; i>=0;i--){
                
                while(num>temp.next.get(i).value){
                    temp=temp.next.get(i);
                }
                a.currH=i;
                l.add(temp);
            }
             a.linklist=l;
            return a;
        }

        public Integer upperBound(int num){ 
            // TO be completed by students  
            System.out.println(  "upperbound : "+ num);
            SkipListNode temp=head;
            for(int i=this.height-1; i>=0;i--){
                
                while(num>=temp.next.get(i).value){
                    temp=temp.next.get(i);
                }
                if(temp.next.get(0).value>num){
                    return temp.next.get(0).value;
                }
                  
            
               
                }
                return Integer.MAX_VALUE;
            
            }
        
 
        public void insert(int num){
            // TO be completed by students
            System.out.println("insert : "+ num);
            SkipListNode newNode =new SkipListNode(num,1);
            if(len==0){
                head.next.set(0, newNode);
                newNode.next.add( tail);
                tail.next.set(0, null);

                while(randomizer.binaryRandomGen()){
                    head.next.add(newNode);
                    newNode.height++;

                    if(newNode.height>height){
                        height++;
                        head.height++;
                        tail.height++;
                        newNode.next.add( tail);
                        tail.next.add(null);
                        break;
                    }
                    height++;
                    head.height++;
                    tail.height++;
                    newNode.next.add( tail);
                    tail.next.add(null);
                    
                }
                len++;
            }
            else{
                SkipListNode  temp ;

                insertHelper obj=list_of_before(num);
                int size=obj.linklist.size();
                int j=0;
                newNode.next.add(obj.linklist.get(size-1).next.get(0));
                obj.linklist.get(size-1).next.set(0,newNode);
                boolean p=true;
                for(int i=1;i<=obj.currH;i++){
                    p=randomizer.binaryRandomGen();
                    if(p){
                        newNode.next.add(obj.linklist.get(size-1).next.get(i));
                        obj.linklist.get(size-1).next.set(i,newNode);
                        newNode.height++;
                    }
                    else{
                        j=i-1;
                        break;
                    }
                    j=i;
                }
                if(j==obj.currH && p==true){
                    int k=1;
                    int tem_height=height;
                    
                    while(randomizer.binaryRandomGen()){
                        if((j+k)<tem_height){
                            
                            newNode.next.add(obj.linklist.get(size-1-k).next.get(j+k));
                            obj.linklist.get(size-1-k).next.set(j+k,newNode);
                            newNode.height++;
                            k++;
                        }
                        else{

                            head.next.add(newNode);
                            newNode.height++;
                            if(newNode.height>height){
                                height++;
                                head.height++;
                                tail.height++;
                                newNode.next.add( tail);
                                tail.next.add(null);

                                break;
                            }
                            height++;
                            head.height++;
                            tail.height++;
                            newNode.next.add( tail);
                            tail.next.add(null);
                            
                        }
                    }
                }
                len++;
            }

        }


      
          
        public void print(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            for(int i = this.height ; i>=1; --i){
                SkipListNode it = this.head;
                while(it!=null){
                    if(it.height >= i){
                        System.out.print(it.value + "\t");
                    }
                    else{
                        System.out.print("\t");
                    }
                    it = it.next.get(0);
                }
                System.out.println("null");
            }
        }


        public static void main(String[] args) {
            SkipList s1= new SkipList();
            // s1.insert(1);
            // s1.insert(1);
            // s1.insert(1);
            // s1.insert(1);




            s1.insert(3);
            s1.insert(1);
            s1.insert(5);
            s1.insert(8);
            System.out.println( s1.search(1));
            System.out.println( s1.search(10));
            // s1.insert(1);
            // s1.insert(1);
            // s1.insert(1);
            // s1.insert(1);
            // s1.insert(4);
            // s1.insert(5);
            // s1.insert(4);
            // s1.insert(3);
            // s1.delete(3);
            // s1.delete(3);

            // s1.delete(5);
            // s1.delete(5);
            // s1.delete(8);

            // System.out.println(s1.upperBound(3));
            // s1.delete(3);
            // s1.delete(8);
            // s1.insert(3);
            // s1.insert(3);
            System.out.println( "2 :"+ s1.upperBound(2));
            
            System.out.println("3 :"+ s1.upperBound(3));
            // s1.insert(15);
            // s1.insert(1);

            // s1.insert(20);
            // s1.insert(30);
            // s1.insert(5);
            // s1.insert(1);
            // s1.insert(15);

            // s1.insert(5);
            // s1.insert(8);
            // s1.insert(7);
            // s1.insert(1);
            // s1.insert(2);
            // s1.insert(15);
            // s1.insert(15);

            s1.insert(20);
            // s1.insert(30);
            s1.insert(15);


            // System.out.println( s1.search(3));
            // System.out.println( s1.search(4));
            // System.out.println( s1.search(8));

            // System.out.println( s1.delete(3));
            // System.out.println( s1.delete(3));
            // System.out.println(s1.delete(7));
            // System.out.println(s1.delete(8));
            // System.out.println(s1.delete(1));
            // System.out.println(s1.delete(1));
            // System.out.println(s1.delete(1));
            System.out.println(s1.height);

            System.out.println("4 "  + s1.search(7));


            s1.print();

s1.delete(1);
s1.delete(3);
s1.delete(15);
s1.delete(5);

s1.delete(20);
s1.delete(8);


            System.out.println("   ");
            System.out.println("    -------------------------------------------------------------");

            // System.out.println("height");
            SkipListNode tem=s1.head;
            while (tem!=null) {
                System.out.print(tem.height + "\t");
                tem=tem.next.get(0);
            }
            System.out.println("   ");
            System.out.println("    -------------------------------------------------------------");

            // for(int i=0; i<7;i++){
            // System.out.println(  "value at i= "+i+" : "+ s1.full_list_before_Delete().get(i).value);}
            // // s1.delete(3);
            // // s1.delete(2);
            // s1.delete(1);
            // // s1.delete(1);
            // // s1.delete(1);

                // s1.tail.height++;
                // s1.head.height++;
                // s1.height++;


            s1.print();
            // tem=s1.head;
            // while (tem!=null) {
            //     System.out.print(tem.height + "\t");
            //     tem=tem.next.get(0);
            // }
            
}
}




