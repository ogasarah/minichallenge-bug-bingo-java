public class DoublyLinkedList<T>{
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    
    // Race condition
    synchronized public T dequeue() {
        // NPE
        if (head == null) {
            return null;    
        }
        
        DoublyLinkedListNode node = head;
        T response = head.getValue();
        
        head = head.getPrevious();
        
        // NPE
        if (head == null) {
            tail = null;
            return response;
        }
        
        // Memory Leak -> didn't remove next pointer.
        head.setNext(null);
        node.setPrevious(null);
        
        return response;

        // Unreachable code -> returning before setting new head.
        // head = head.getPrevious();
    }
    
    // Race condition
    synchronized public void enqueue(T value) {
        // Uninitialized Variable -> We never initialized node before trying to use it.
        // DoublyLinkedListNode<T> node;
        // node.setValue(value);
        DoublyLinkedListNode<T> node = new DoublyLinkedListNode<>(value);
        node.setValue(value);
        
        if (tail == null) {
            tail = node;
            head = node;
            return;
        }
        
        tail.setPrevious(node);
        node.setNext(tail);
        tail = node;
    }
    
    public static void main(String args[]) {
        Server s1 = new Server("Server 1", Status.ACTIVE);
        Server s2 = new Server("Server 2", Status.STOPPED);
        Server s3 = new Server("Server 3", Status.HYBERNATED);

        DoublyLinkedList queue = new DoublyLinkedList();
        ServerTerminator st = new ServerTerminator(queue);
        
        queue.enqueue(s1);
        st.terminateNextServer();
        
        queue.enqueue(s2);
        queue.enqueue(s3);
        st.terminateNextServer();
        st.terminateNextServer();
    }
}
