
public class Stack<E> {
private LinkedList<E> linkedList=new LinkedList();
public void push(E e) {
	linkedList.addLast(e);
}
public E pop() {
	E ans=linkedList.getLast();
	linkedList.deleteLast();
	return ans;
}
public E peek() {
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
public E getTop() {
	return linkedList.getLast();
}

}
