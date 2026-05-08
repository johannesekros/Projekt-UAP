public class doubleLinkedList {
    private Node head;
    private Node tail;
    private int size;

    private class Node{
        int data;
        Node next;
        Node prev;

        Node(int data){
            this.data = data;
        }
    }
    public void addFirst(int data){
        Node newNode = new Node(data);
        if (head == null && tail == null){
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size += 1;
    }

    public void addLast(int data){
        Node newNode = new Node(data);
        if (head == null && tail == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size += 1;
    }

    public void delete(int data){
        Node current = head;
        while(current.data != data && current.next != null){
            current = current.next;
        }
            if (current.data == data) {
                if(current == head){
                    head = head.next;
                    head.prev = null;
                }  else if (current == tail){
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
            size -= 1;
            }
    }


    //searches for element in list. Returns true if element is in list, false if list is empty or element not found.
    public boolean search(int data){
        Node current = head;
        if (head == null && tail == null){
            return false;
        }
        while(current.data != data && current.next != null){
            current = current.next;
        }
            return current.data == data;
    }

    public int getSize(){
        return size;
    }
}
