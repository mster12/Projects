//ABDUL REHMAN ALI ALI
public class MyDynamicStack implements IMyStack {

	private MyNode<IMyStackElement>head = null;
	private int numItems;				//to know numItems in the list


	public MyDynamicStack()
	{
		this.head = null;
		this.numItems = 0;
	}

	public void pushTop(IMyStackElement element)
	{
		if (head == null) {
			head = new MyNode<IMyStackElement>(element, null);
			numItems++;
		}
		else {
			MyNode<IMyStackElement> newNode = head;
			while (newNode.next != null) {
				newNode = newNode.next ;
			}
			newNode.setNext (new MyNode<IMyStackElement> (element, null));
			numItems++;
		}

	}

	public void pushBottom(IMyStackElement element)
	{
		if (head == null) {
			head = new MyNode<IMyStackElement>(element, null);
			numItems++;
		}
		else {
			MyNode<IMyStackElement> newNode = head;
			head = new MyNode<IMyStackElement>(element, null);
			head.next = newNode;
			numItems++;
		}
	}
	public IMyStackElement popTop() {
		if (numItems == 0) {
			System.out.println("Empty Stack");
			return null;
		}
			else
				{ IMyStackElement topElement = head.value;
				MyNode<IMyStackElement> newNode = head;
				while (newNode.next.next != null) {
					newNode = newNode.next;
					}
				newNode.next = null;
				numItems--;
				return topElement;
				}
		}

	public IMyStackElement popBottom() {
		if (numItems == 0) {
			System.out.println("Empty Stack");
			return null;
		} else {
			final IMyStackElement bottomElement = head.value;
			head = head.next;
			numItems--;
			return bottomElement;
		}
	}
	public IMyStackElement peekTop(int number) {
		if (numItems == 0) {
			System.out.println("Empty Stack");
			return null;
		}
		else {
			number = numItems - number - 1;
			while (number-- > 0 && head != null) {
				head = head.next;

			}
		}

		return head.value;
	}

	public IMyStackElement peekBottom(int number)
	{
		if (numItems == 0) {
			System.out.println("Empty Stack");
			return null;
		}
		else {
			while (number-- > 0 && head != null) {
				head = head.next;
			}
		}
		return head.value;
	}

	public int size() {
		return this.numItems;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void print() {
		MyNode<IMyStackElement> newNode = head;
		while (newNode != null) {
			System.out.print(newNode.getValue().getValue() + " ");
			newNode = newNode.next;
		}
		System.out.println();
	}


	public int getValue()
	{
		return size();
	}
}