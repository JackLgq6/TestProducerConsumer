package testpc;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MainFarm {

	public static void main(String[] args) {
		
		//生产的年数
		int mInitYear = 10;
		//起始月份
		int mInitMonth = 9;
		
		//鸡的生产/消费
		BigDecimal mInitChickenNum = new BigDecimal("40");
		Chicken mChicken = new Chicken(mInitChickenNum,mInitYear,mInitMonth);
		new Thread(new Producer(mChicken)).start();
		new Thread(new Consumer(mChicken)).start();
		
		//羊的生产/消费
		int mInitRawNum = 2;
		int mInitEweNum = 8;
		Sheep mSheep = new Sheep(mInitRawNum,mInitEweNum,mInitYear,mInitMonth);
		new Thread(new Producer(mSheep)).start();
		new Thread(new Consumer(mSheep)).start();
				
		
		//牛的生产/消费
		int mInitBullNum = 1;
		int mInitBossyNum = 1;
		Cattle mCattle = new Cattle(mInitBullNum,mInitBossyNum,mInitYear,mInitMonth);
		new Thread(new Producer(mCattle)).start();
		
		while(true) {
			if (mChicken.ismChickenFlag() && mSheep.ismSheepFlag() && mCattle.ismCattleFlag()) {
				int totalNum1 = mSheep.getmCurrentSheepNum() + mCattle.getmCurrentAnimalNum();
				BigInteger totalCount = mChicken.getmCurrentAnimalNum().add(new BigInteger("" + totalNum1)); 
				System.out.println();
				System.out.println(mInitYear + "后总的动物数量：" + totalCount);
				System.out.println(mInitYear + "后鸡的头数：" + mChicken.getmCurrentAnimalNum());
				System.out.println(mInitYear + "后鸡的脚数：" + (mChicken.getmCurrentAnimalNum().multiply(new BigInteger("2"))));
				System.out.println(mInitYear + "后羊的头数：" + mSheep.getmCurrentSheepNum());
				System.out.println(mInitYear + "后羊的脚数：" + mSheep.getmCurrentSheepNum()*4);
				System.out.println(mInitYear + "后牛的头数：" + mCattle.getmCurrentAnimalNum());
				System.out.println(mInitYear + "后牛的脚数：" + mCattle.getmCurrentAnimalNum()*4);
				break;
			} 
		}
	}

}
