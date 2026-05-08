
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class App {
    static int[] readFile(String path) throws Exception {
            
            ArrayList<Integer> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
            br.close();
            return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        }
    public static void main(String[] args) throws Exception {
        
        BinarySearchTree bst = new BinarySearchTree();
        

        long start = System.nanoTime();

        int[] data = readFile("randomNumbers/DataSmall.txt");

        for (int num : data){
            bst.add(num);
        }

        long elapsed = System.nanoTime() - start;

        System.out.println("BST" + "," + "Small" + "," + "insert" + "," + elapsed);
    }


}

