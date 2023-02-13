
public class linkedList<T> {
	
	public Node<T> head ;
	public Node<T> current;
	private int size;
	
	public linkedList(){
	 head=current=null;
	 size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public int length(){
		return size;
	}

	public boolean empty(){
		return head==null;
	}
	
	public T retrieve(){
		if(current != null)
		return current.data;
		return null;
	}
	
	public void findfirst(){
		current = head ;	
	}
	
	public boolean full(){
		return false;
	}
	
	public boolean last() {
		return current.next==null;
	}
	
	public void findLast() {
		if(!empty()) {
			while(current.next != null)
				current = current.next;
		}
	}
	
	public boolean FindWord(String w) {
		findfirst();
		if(empty()) return false;
		while(current.next != null) {
			if(((WordInformation)current.data).getWord().equalsIgnoreCase(w))
				return true;
			current = current.next;
		}
		if(((WordInformation)current.data).getWord().equalsIgnoreCase(w))
			return true;
		
		return false;
	}
	
	
	public void insert(T v) {
		if(empty()){
			head=current=new Node<T>(v);
		    size++;	
		}
		else {
		Node<T> tmp;
		tmp = current.next;
		current.next = new Node<T>(v);
	    size++;	
		current = current.next;
		current.next = tmp;
		}
	}

	public boolean outOfList() {
		if(current == null) {
			current = head;
			return true;
		}
		return false;
	}
	
	public void findNext() {
		current = current.next;
	}
	
	

}
