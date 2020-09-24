//ABDUL REHMAN ALI ALI
public class MyDoubleLinkedStack implements IMyStack {
	private MyDoubleLinkedNode<IMyStackElement> headNode;
	private MyDoubleLinkedNode<IMyStackElement> tailNode;
	private int numItems;

	public MyDoubleLinkedStack()
	{
		this.headNode = null;
		this.tailNode = null;
		this.numItems = 0;
	}

	public void pushTop(IMyStackElement element)
	{
		if (tailNode == null) {
			headNode = tailNode = new MyDoubleLinkedNode<IMyStackElement>(element, null, null);
			numItems++;
		}
		else {
			MyDoubleLinkedNode<IMyStackElement> newNode = tailNode;
			tailNode.setNext(new MyDoubleLinkedNode<>(element, newNode, null));
			tailNode = tailNode.getNext();
			numItems++;
		}
	}

	public void pushBottom(IMyStackElement element)
	{
		if (headNode == null) {
			headNode = tailNode = new MyDoubleLinkedNode<IMyStackElement>(element, null, null);
			numItems++;
		}
		else {
			MyDoubleLinkedNode<IMyStackElement> newNode = headNode;
			headNode.setNext(new MyDoubleLinkedNode<>(element, newNode, null));
			headNode = headNode.getNext();
			numItems++;
		}
	}

	public IMyStackElement popTop()
	{
		if (numItems==0) {
			System.out.println("Empty Stack");
			return null;
		}
		IMyStackElement topElement = tailNode.value;
		tailNode = tailNode.getPrevious();
		tailNode.setNext(null);
		numItems--;
		return topElement;
	}

	public IMyStackElement popBottom()
	{
		if (numItems==0) {
			System.out.println("Empty Stack");
			return null;
		}
		else if (headNode == tailNode)
		{
			headNode = null;
			numItems--;
			return headNode.value;
		}
		else {
			IMyStackElement bottomElement = headNode.value;
			headNode = headNode.getNext();
			tailNode.setPrevious(null);
			numItems--;
			return bottomElement;
		}
	}


	public IMyStackElement peekTop(int number) {
		if (numItems == 0){
			System.out.println("Empty Stack");
			return null;
		}
		else {
			return null;
			}
	}

	public IMyStackElement peekBottom(int number)
	{
		if (numItems == 0){
			System.out.println("Empty Stack");
			return null;
		}
		else {

			return null;
		}
	}
	public int size() {
		return numItems;
	}

	public boolean isEmpty() {
		return headNode == null;
	}

	public void print() {
		MyDoubleLinkedNode<IMyStackElement> newNode = headNode;
		while (newNode != null) {
			System.out.print(newNode.getValue().getValue() + " ");
			newNode = newNode.getNext();
		}
		System.out.println();
	}

	public int getValue()
	{
		return size();
	}
}
