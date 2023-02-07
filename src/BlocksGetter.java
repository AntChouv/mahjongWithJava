import com.google.gson.Gson;                
import com.google.gson.reflect.TypeToken;    
import com.google.gson.stream.JsonReader;   
                                             
import java.io.FileNotFoundException;       
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BlocksGetter {
    private Gson gson;

    public BlocksGetter() {
       gson = new Gson();
    }
    public ArrayList<String>  getBlocks() throws FileNotFoundException{//επιστρεφει ArrayList με ολες της επαφες απο το json αρχειο
        
        // Το path για το αρχειο json.
        String path = System.getProperty("user.dir") + "/src/Tiles.json";//Με αυτο τον τροπο βρησκει το path σε οποιο Η/Υ και αν τρεξει           
                                                                            // αρκει να υπαρχει το αρχειο καπου
        // 
        JsonReader jsonReader = new JsonReader(new FileReader(path));

        // define return class type for gson
        Type type = new TypeToken<ArrayList<Blocks>>() {}.getType();       // κανουμε define το τυπο που θα επιστρεφει η κλαση απο το gson

        ArrayList<Blocks> blocks = gson.fromJson(jsonReader, type);      // parse json σε ArrayList<Contact> και επεστρεψε το
        
        if (blocks == null){          //Αν gson.fromJson επιστρεψει null, αρχικοποιησε το contacts list
            blocks = new ArrayList();
        }
        ArrayList <String> multi = new ArrayList<>();
             for (int i = 0;i<blocks.size();i++){
                 String str1 = blocks.get(i).getCharN();
                 String str = blocks.get(i).getReps();
                 int reps = Integer.valueOf(str);
                 for (int j=0;j<reps;j++){
                    multi.add(str1);     //ArrayList με ολα τα στοιχεια του μαγιονγκ. Για καθε στοιχειο εχουμε τι ειδος ειναι 
                                         //και ποσες φορες επαναλαμβανεται
                 }
             }
        return multi;
    }  
}