import java.util.*;

public class PriorityQueueWithHeap {

    static class Task {
        String name;
        int priority;
        long order; 
        Task(String name, int priority, long order) {
            this.name = name;
            this.priority = priority;
            this.order = order;
        }

        @Override
        public String toString() {
            return String.format("Task{name='%s', priority=%d}", name, priority);
        }
    }

    private final PriorityQueue<Task> heap;
    private long counter = 0; 
    private final List<Task> allTasks; 

    public PriorityQueueWithHeap() {
       
        heap = new PriorityQueue<>((a, b) -> {
            if (b.priority != a.priority) return b.priority - a.priority;
            return Long.compare(a.order, b.order);
        });
        allTasks = new ArrayList<>();
    }

  
    public void addTask(String name, int priority) {
        Task t = new Task(name, priority, counter++);
        heap.add(t);
        allTasks.add(t);
    }


    public Task executeNext() {
        Task t = heap.poll();
        if (t != null) allTasks.remove(t);
        return t;
    }

 
    public Task peek() {
        return heap.peek();
    }

    
    public void changePriority(String name, int newPriority) {
        boolean found = false;
        for (Task t : allTasks) {
            if (t.name.equals(name)) {
                t.priority = newPriority;
                found = true;
            }
        }
        if (found) {
     
            heap.clear();
            heap.addAll(allTasks);
        }
    }

      public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

       
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        
        System.out.println("下一個要執行: " + pq.peek());

     
        while (true) {
            Task t = pq.executeNext();
            if (t == null) break;
            System.out.println("執行: " + t);
        }

    
        pq.addTask("A", 2);
        pq.addTask("B", 4);
        pq.addTask("C", 1);
        pq.changePriority("C", 6); // C 優先級最高
        System.out.println("修改後下一個要執行: " + pq.peek());
    }
}