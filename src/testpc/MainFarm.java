package testpc;

public class MainFarm {

	public static void main(String[] args) {
		
		//生产的年数
		int mInitYear = 10;
		//起始月份
		int mInitMonth = 9;
		
		//鸡的生产/消费
		int mInitChickenNum = 40;
		Chicken mChicken = new Chicken(mInitChickenNum,mInitYear,mInitMonth);
		new Thread(new Producer(mChicken)).start();
		new Thread(new Consumer(mChicken)).start();
		
		/*//羊的生产/消费
		int mInitRawNum = 8;
		int mInitEweNum = 2;
		Sheep mSheep = new Sheep(mInitRawNum,mInitEweNum,mInitYear,mInitMonth);
		new Thread(new Producer(mSheep)).start();
		new Thread(new Consumer(mSheep)).start();
		
		//牛的生产/消费
		int mInitBullNum = 1;
		int mInitBossyNum = 1;
		Cattle mCattle = new Cattle(mInitBullNum,mInitBossyNum,mInitYear,mInitMonth);
		new Thread(new Producer(mCattle)).start();
		new Thread(new Consumer(mCattle)).start();	*/	
		
	}

}
