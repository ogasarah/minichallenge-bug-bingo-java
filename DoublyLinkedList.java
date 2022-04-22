public class DoublyLinkedList<T>{
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    
    synchronized public T dequeue(){
        if (head == null) {
            return null;    
        }
        
        DoublyLinkedListNode node = this.head;
        T response = head.getValue();
        
        this.head = this.head.getPrevious();
        
        if(this.head == null){
            this.tail = null;
            return response;
        }
        
        this.head.setNext(null);
        node.setPrevious(null);
        
        return response; 
    }
    
    synchronized public void enqueue(T value){
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
