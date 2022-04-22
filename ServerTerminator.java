class ServerTerminator{
    DoublyLinkedList<Server> queue;
    
    public ServerTerminator(DoublyLinkedList<Server> queue){
        this.queue = queue;
    }
    
    public void terminateNextServer(){
        Server dequeuedServer = queue.dequeue();
        if (dequeuedServer != null) dequeuedServer.setStatus(status.TERMINATED);
    }
}