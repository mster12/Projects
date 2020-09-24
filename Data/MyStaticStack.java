//ABDUL REHMAN ALI ALI
public class MyStaticStack implements IMyStack {
	private IMyStackElement [] stack;   //stores the items
	private int capacity = INITIAL_CAPACITY;
	private int top = -1;
	protected static final int INITIAL_CAPACITY = 4;

	public MyStaticStack()
	{
		this.stack = new IMyStackElement [INITIAL_CAPACITY];

	}

	public void pushTop(IMyStackElement element)
	{
		if (size() == stack.length) { //Is full
			doubleCapacity();
		}
		top++;
		stack[top] = element;
	}

	public void pushBottom(IMyStackElement element)
	{
		if (size() == stack.length) { //Is full
			doubleCapacity();
		}
		//Moving all the elements to the right
		for (int i = top + 1; i > 0; i--) {
			stack[i] = stack[i - 1];
		}
		stack[0] = element;
		top++;
	}

	public IMyStackElement popTop()
	{
		if (isEmpty()){
			System.out.println("Empty Stack");
			return null;
		}
		if (size()<capacity/4){
			reduceCapacity();
		}
		IMyStackElement topElement = stack[top - 1]; //Va antes que la funcion reducir?
		top--;
		return topElement;
	}

	public IMyStackElement popBottom()
	{
		if (isEmpty()){
			System.out.println("Empty Stack");
			return null;
		}
		if (size()<capacity/4){
			reduceCapacity();
		}
		IMyStackElement botElement = stack[0]; //Va antes que la funcion reducir?
		//Mover todos los elementos a la izquierda 1 posicion
		for(int i = 0; i<=top; i++){
		stack[i] = stack[i + 1];
	}
		top--;
		return botElement;

	}

	public IMyStackElement peekTop(int number) {
		if (0 < number || number > capacity){
			throw new ArrayIndexOutOfBoundsException();

		}

		return stack[top - number];

	}

	public IMyStackElement peekBottom(int number)
	{
		if (0 < number || number > capacity){
		throw new ArrayIndexOutOfBoundsException();
	}

		return stack[number];

	}

	public int size() {

		return top+1;
	}

	public boolean isEmpty() {

		return top == -1;
	}

	public void print() {
		for (int i = 0; i <= top; i++) {
			System.out.print(stack[i].getValue() + " ");
		}
		System.out.println();
	}

	public int getValue()
	{
		return (int) Math.round(Math.pow(size(), 2) / 2);
	}


	private void doubleCapacity(){				//Double the capacity of the stack if needed
		int newCapacity = 2*stack.length;
		IMyStackElement [] newStack = new IMyStackElement [newCapacity];
		for (int i=0; i< stack.length; i++) {
			newStack[i] = stack[i];
		}
		stack=newStack;
	}
	private void reduceCapacity(){			//Reduce the capacity by half if needed
		int newCapacity = stack.length/2;
		IMyStackElement [] newStack = new IMyStackElement [newCapacity];
		for (int i=0; i<stack.length; i++) {
			newStack[i] = stack [i];
		}
		stack=newStack;
	}

}

