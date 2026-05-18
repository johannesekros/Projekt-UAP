
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
            String[] datasets = {"Small", "Medium", "Large"};
            String[] paths = {
                    "randomNumbers/DataSmall.txt",
                    "randomNumbers/DataMedium.txt",
                    "randomNumbers/DataLarge.txt"
            };
            for (int i = 0; i < datasets.length; i++){
                int[] data = readFile(paths[i]);
                

                //Insert
                for (int run = 0; run < 10; run++){
                    DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
                    long start = System.nanoTime();
                    for (int num : data){
                        doubleLinkedList.addLast(num);

                    }
                    System.out.println("LinkedList," + datasets[i] + ",insert," + (System.nanoTime() - start));
                }    

                //Search
                for (int run = 0; run < 10; run++){
                    DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

                    //Fyll strukturen en gång
                    for (int num : data){
                        doubleLinkedList.addLast(num);
                    }

                    long start = System.nanoTime();
                    for (int num : data){
                        doubleLinkedList.search(num);
                    }
                    System.out.println("LinkedList," + datasets[i] + ",search," + (System.nanoTime() - start));
                }
                //Delete
                for (int run = 0; run < 10; run++){
                    DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
                    //Fyll strukturen en gång
                    for (int num : data){
                        doubleLinkedList.addLast(num);
                    }

                    long start = System.nanoTime();
                    for (int num : data){
                        doubleLinkedList.delete(num);
                    }
                    System.out.println("LinkedList," + datasets[i] + ",delete," + (System.nanoTime() - start));
                }
            }            
            


            


        } else if (args[0].equals("MinHeap")){
            String[] datasets = {"Small", "Medium", "Large"};
            String[] paths = {
                    "randomNumbers/DataSmall.txt",
                    "randomNumbers/DataMedium.txt",
                    "randomNumbers/DataLarge.txt"
            };           
           
            for (int i = 0; i < datasets.length; i++){
                int[] data = readFile(paths[i]);

                //Insert
                for (int run = 0; run < 10; run++){
                    MinHeap minHeap = new MinHeap(data.length);
                    
                    long start = System.nanoTime();
                    for (int num : data){
                        minHeap.insert(num);
                    }
                    System.out.println("MinHeap," + datasets[i] + ",insert," + (System.nanoTime() - start));
                }

                //Search
                for (int run = 0; run < 10; run++){
                    MinHeap minHeap = new MinHeap(data.length);   
                    
                    //Fyll strukturen en gång
                    for (int num : data){
                        minHeap.insert(num);
                    }

                    long start = System.nanoTime();
                    for (int num : data){
                        minHeap.lookup(num);
                    }
                    System.out.println("MinHeap," + datasets[i] + ",search," + (System.nanoTime() - start));
                }
                //Delete
                for (int run = 0; run < 10; run++){
                    MinHeap minHeap = new MinHeap(data.length);                    
                    
                    //Fyll strukturen en gång
                    for (int num : data){
                        minHeap.insert(num);
                    }
                    
                    long start = System.nanoTime();
                    for (int num : data){
                        minHeap.delete(num);
                    }

                    System.out.println("MinHeap," + datasets[i] + ",delete," + (System.nanoTime() - start));
                }

            }

        } else if (args[0].equals("BST")){
            String[] datasets = {"Small", "Medium", "Large"};
            String[] paths = {
                    "randomNumbers/DataSmall.txt",
                    "randomNumbers/DataMedium.txt",
                    "randomNumbers/DataLarge.txt"
            };
            
            for (int i = 0; i < datasets.length; i++){
                int[] data = readFile(paths[i]);
                
                //Insert
                for (int run = 0; run < 10; run++){
                    BinarySearchTree bst = new BinarySearchTree();
                    
                    long start = System.nanoTime();
                    for (int num : data){
                        bst.add(num);
                    }
                    System.out.println("BST," + datasets[i] + ",insert," + (System.nanoTime() - start));
                }
                //Search
                for (int run = 0; run < 10; run++){
                    BinarySearchTree bst = new BinarySearchTree();
                    
                    //Fyll strukturen en gång innan mätning.
                    for (int num : data) {
                        bst.add(num); //setup utan mätning
                    }

                    long start = System.nanoTime();
                    for (int num : data){
                        bst.contains(num);
                    }
                    
                    System.out.println("BST," + datasets[i] + ",search," + (System.nanoTime() - start));
                }
                //Delete
                for (int run = 0; run < 10; run++){
                    BinarySearchTree bst = new BinarySearchTree();
                    
                    //Fyll strukturen en gång
                    for (int num : data){
                        bst.add(num);
                    }
                    //Här börjar mätningen.
                    long start = System.nanoTime();
                    for (int num : data){
                        bst.remove(num);
                    }
                    System.out.println("BST," + datasets[i] + ",delete," + (System.nanoTime() - start));
                }
            }
  
        }
       
    }


}

