package testpc;

public class Producer implements Runnable {

	private Animal mAnimal;
	
	public Producer(Animal animal) {
		mAnimal = animal;
	}
	
	@Override
	public void run() {
		
		while (true)
		{
			if(mAnimal.getmCurrentYear() <= mAnimal.getmInitYear()) {
				mAnimal.produce();
				
			}else {				
				break;
			}
				
		}		
		
	}

	

}
