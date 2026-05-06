import java.util.Scanner;
import java.io.*;

//Tack claude för denna testklass
public class bstTest {
    public static void main(String[] args) throws IOException {
        BinarySearchTree tree = new BinarySearchTree();

        //Insert
        Scanner scanner = new Scanner(new File("randomNumbers/DataSmall.txt"));
        while (scanner.hasNextInt()) {
            tree.add(scanner.nextInt());
        }
        scanner.close();

        System.out.println("=== EFTER VI INSERTAT ALLT FRÅN FILEN ===");
        System.out.println("Size:   " + tree.size());
        System.out.println("Height: " + tree.height());

        tree.add(1); //TESTAR ADDA ETT EXTRA ELEMENT BROR
        System.out.println("\n=== EFTER VI INSERTAT EN ETTA SOM TEST ===");
        System.out.println("Size:   " + tree.size());
        System.out.println("Height: " + tree.height());

        //Search
        System.out.println("\n=== SÖKNING ===");
        System.out.println("Contains 42?  " + tree.contains(42));
        System.out.println("Contains 801? " + tree.contains(801));

        //Remove
        System.out.println("\n=== REMOVE ===");
        System.out.println("Remove 42:  " + tree.remove(42));// not in tree
        System.out.println("Remove 801: " + tree.remove(801)); 
        System.out.println("Contains 801 after remove? " + tree.contains(801));
        System.out.println("Size after remove: " + tree.size());
        System.out.println("Height after remove: " + tree.height());

        //Sort
        System.out.println("\n=== OUTPUT (SKALL VARA AUTOMATISKT SORTERAT) ===");
        System.out.println(tree);

        //Clear
        tree.clear();
        System.out.println("=== EFTER CLEAR ===");
        System.out.println("Size: " + tree.size());
    }
}