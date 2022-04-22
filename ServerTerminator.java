// Tight Coupling -> Should be an interface as it is tightly coupled with the queue implementation.
class ServerTerminator{
    DoublyLinkedList<Server> queue;
    
    public ServerTerminator(DoublyLinkedList<Server> queue){
        this.queue = queue;
    }
    
    public void terminateNextServer(){
        // Null Pointer Exception -> calling setStatus on potential null object.
        // Nonexistent Enum Entry -> using TERMINATING instead of TERMINATED.
        // queue.dequeue().setStatus(Status.TERMINATING);
        Server dequeuedServer = queue.dequeue();
        if (dequeuedServer != null) dequeuedServer.setStatus(status.TERMINATED);
    }
}