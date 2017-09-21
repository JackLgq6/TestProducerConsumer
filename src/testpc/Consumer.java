package testpc;

public class Consumer implements Runnable{

	private Animal mAnimal;
	
	public Consumer(Animal animal) {
		mAnimal = animal;
	}
	
	@Override
	public void run() {	
		
		while (true)
		{
			if(mAnimal.getmCurrentYear() <= mAnimal.getmInitYear()) {
				mAnimal.consume();
			}else {
				break;
			}
				
		}			
	}

}
