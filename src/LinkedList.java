
public class LinkedList<E> {
	protected Node start;

private Node end;
private Long size=0L;
public void addFirst(E e) {
	size++;
	Node tmp=new Node(e);
	if(start!=null) {
tmp.next=start;
start=tmp;}
	else {
		start=tmp;
		end=tmp;}
}
public String toString() {
	String a="";
	Node pointer=start;
	while(pointer!=null) {
		a+=pointer.data.toString()+" ";
		pointer=pointer.next;
	}
	return a;
}
public void addLast(E e) {
	size++;
	Node tmp=new Node(e);
	if(start!=null) {
end.next=tmp;
end=tmp;
	}
	else {
		addFirst(e);
	}
}
public void deleteFirst() {
	size--;
	if(start!=null) {
		if(start==end)
			end=null;
		start=start.next;
		
	}
}
public void deleteLast() {
	size--;
	if(getSize()==1)
		deleteFirst();
	else if(getSize()>0){
	Node pointer=start;
	Node last=start;
	while(pointer.next!=null) {
		last=pointer;
		pointer=pointer.next;}
	last.next=null;
	end=last;
		}
}
public E getFirst() {
	if(start!=null)
		return (E)start.data;
	return null;
}
public E getLast() {
	if(end==null)
		return null;
	else
	return end.data;
}
public long   getSize() {
	return size;
	
}
public void clear() {
	start=null;
}
public boolean isEmpty() {
	return (start==null);
}
public void reverse() {

	reve(null,start);
}
public void reverse2() {
	reve2(start);
}
void reve2(Node node) {
	if(node.next==null)
		start=node;
	else {
		reve2(node.next);
		node.next.next=node;
		node.next=null;
	}
}
void reve(Node last,Node now) { 
		
	
			Node tmp=now.next;
		now.next=last;
		if(tmp!=null) {
			
		reve(now, tmp);}
		else {
			start=now;
			
			}
		
	
	}
class Node{
	E data;
	Node next;
	public Node(E data) {
	this.data=data;
	}
}

}

