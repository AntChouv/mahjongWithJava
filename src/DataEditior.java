import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class DataEditior implements blockEdit {
    @Override
    public void randSuffle(ArrayList <String> suffled){
             Collections.shuffle(suffled);
    }  
    @Override
    public ArrayList <LinkedList> formGroups(ArrayList <String> suffled) {             
             ArrayList <LinkedList> lst = new ArrayList<>();
             
             int blck = suffled.size();
             int counter1=0,counter2=0;
            
             while (blck>0){
                LinkedList<String> list = new LinkedList();
                if (counter1<2){                    
                    for (int i=0;i<14;i++){     //2 List των 14  
                        list.add(suffled.get(blck-1));
                        blck -=1;
                    }
                    lst.add(list);
                    counter1 +=1;
                }else if (counter1==2 && counter2<4){ //4 List των 13
                    
                    for (int i=0;i<13;i++){
                        list.add(suffled.get(blck-1));
                        blck -=1;
                    }
                    lst.add(list);
                    counter2 +=1;
                }else if (counter2>=4){    //4 List των 11 
                    for (int i=0;i<11;i++){
                        list.add(suffled.get(blck-1));
                        blck -=1;
                    }
                    lst.add(list);
                    counter2 +=1;
                }                
             }
             return lst;
    }

   @Override
    public void getLayout(int l, ArrayList<LinkedList> lst) {
        
        switch (l) {
            case 2:
                for (int i = 0;i<lst.size()-1;i++){ 
                    lst.add(i,lst.remove(lst.size()-1));
                }   break;
            case 3:
                {
                    ArrayList<LinkedList> list1 = new ArrayList<>();
                    list1.add(lst.get(0));
                    list1.add(lst.get(1));
                    for (int i = 2;i <= 5;i++){
                        list1.add(1,lst.get(i));
                    }       for (int i = 6;i < lst.size();i++){
                        list1.add(3,lst.get(i));
                    }       lst.clear();
                    lst.addAll(list1);
                    break;
                }
            case 4:
                {
                    ArrayList<LinkedList> list1 = new ArrayList<>();
                    for (int i = 6;i < lst.size();i++){
                        list1.add(lst.get(i));
                    }       for (int i = 2;i<=5;i++){
                        list1.add(2,lst.get(i));
                    }       list1.add(4,lst.get(0));
                    list1.add(4,lst.get(1));
                    lst.clear();
                    lst.addAll(list1);
                    break;
                }            
            default:
                break;
        }
        
    }
    @Override
    public void reArrange(ArrayList <LinkedList> lout){
        
            ArrayList <String> secondary = new ArrayList<>();
            LinkedList <String> list = new LinkedList<>();
            for (int i=0;i<lout.size();i++){
                list = lout.get(i);  
                for (int j=0;j<list.size();j++){                    
                    secondary.add(list.get(j));
                }
            }
            randSuffle(secondary);
            for (int i=0;i<lout.size();i++){
                list = lout.get(i);  
                for (int j=0;j<list.size();j++){ 
                  list.set(j,secondary.get(0));
                  secondary.remove(0);
                }
            }           
    }
}
