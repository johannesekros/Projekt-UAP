
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
        if (args[0].equals("LinkedList")){
            DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
            int[] data = readFile("randomNumbers/DataSmall.txt");   
            long start = System.nanoTime();
            
            for (int num : data){
                doubleLinkedList.addLast(num);
            }

            long elapsed = System.nanoTime() - start;
            
            System.out.println("LinkedList" + "," + "Small" + "," + "addLast" + "," + elapsed);


        } else if (args[0].equals("MinHeap")){
            MinHeap minHeap = new MinHeap(1000);
            int[] data = readFile("randomNumbers/DataSmall.txt");
            
            long start = System.nanoTime();

            for(int num : data){
                minHeap.insert(num);
            }

            long elapsed = System.nanoTime() - start;

            System.out.println("MinHeap" + "," + "Small" + "," + "insert" + "," + elapsed);


        } else if (args[0].equals("BST")){
            BinarySearchTree bst = new BinarySearchTree();

            int[] data = readFile("randomNumbers/DataSmall.txt");
            long start = System.nanoTime();
            for (int num : data){
                bst.add(num);
            }

            long elapsed = System.nanoTime() - start;

            System.out.println("BST" + "," + "Small" + "," + "add" + "," + elapsed);

            
        }
       
    }


}

