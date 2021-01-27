
public class Queue<E> {
	LinkedList<E> linkedList=new LinkedList<>();
public void enqueue(E e) {
	linkedList.addLast(e);
}
public E dequeue() {
	E ans=linkedList.getFirst();
	linkedList.deleteFirst();
	return ans;
}
public E getFist() {
return linkedList.getFirst();
}
public long getSize() {
	return linkedList.getSize();
}
public void clear() {
	linkedList.clear();
}
public boolean isEmpty() {
	return linkedList.isEmpty();
}

}
