package testpc;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sheep extends Animal {

	private int mCurrentSheepNum; // 当前总的羊数
	private int mCurrentRawNum; // 当前公羊数
	private int mCurrentEweNum; // 当前母羊数
	private int mSmallEweCount = 0;// 小母羊的数量
	private int mSmallRamCount = 0;// 小公羊
	private int pro_small_ewe_count = 0;
//	private Lock lock = new ReentrantLock();
//	private final Condition condition = lock.newCondition(); 
	
	private int mHeadNum;
	private int mLegNum;
	private int mInitYear;
	private int mCurrentYear;
	private int mCurrentMonth;
	private int mBadyNum;

	private Object mObj = new Object();

	public Sheep(int mInitRawNum, int mInitEweNum, int mInityear, int mInitmonth) {
		mCurrentSheepNum = mInitRawNum + mInitEweNum;
		mCurrentRawNum = mInitRawNum;
		mCurrentEweNum = mInitEweNum;
		mCurrentYear = 1;
		mInitYear = mInityear;
		mCurrentMonth = mInitmonth;
	}

	/*
	 * 羊群里面有8只母羊，2只公羊。每只母羊每年10月生产5只小羊，性别随机。
	 * 每年2月农夫出售20%羊，公羊母羊各一半（若为20%的羊数为奇数，多的那一头羊为公羊）。 小羊2年后开始生产，性别随机。
	 */
	@Override
	public void produce() {
		// 实现生产方法
		
		for (int i = 1; i <= mInitYear; i++) {
//			System.out.println(mCurrentYear+"-------"+mCurrentMonth);
			for (int j = 1; j <= 12; j++) {
				synchronized (mObj) {
//				lock.lock();
					int pro_small_ram_count = 0;
					if (mCurrentMonth == 10) {
						mObj.notify();
						if (mCurrentYear % 2 != 0 && mCurrentYear != 1) {
							mCurrentEweNum += mSmallEweCount;
							mSmallEweCount = 0;
						}
						if (mCurrentYear % 2 == 0 && mCurrentYear != 2) {
//							System.out.println(mCurrentEweNum);
//							System.out.println(pro_small_ewe_count);
							mCurrentEweNum += pro_small_ewe_count;
							pro_small_ewe_count = 0;
						}
						Random r = new Random();
						for (int k = 0; k < mCurrentEweNum * 5; k++) {
							int num = r.nextInt(100) + 1;
							if (num % 2 == 0) {
								if (mCurrentYear % 2 != 0) {
									mSmallEweCount++;
								}
								if (mCurrentYear % 2 == 0 ) {
									pro_small_ewe_count++;
								}
							} else {
								mCurrentRawNum++;
								pro_small_ram_count++;
								mSmallRamCount++;
							}
						}
						if (mCurrentYear % 2 != 0) {
							System.out.print("第"+ mCurrentYear + "年 " + "生了" + mSmallEweCount  + "只" + "小母羊， 生了" + pro_small_ram_count+ "只小公羊， ");
							
						} else {
							System.out.print("第"+ mCurrentYear + "年 " + "生了" + pro_small_ewe_count  + "只" + "小母羊， 生了" + pro_small_ram_count+ "只小公羊， ");
//							System.out.println();
//							System.out.println(mEweCount);
//							System.out.println(mRamCount);
//							System.out.println(pro_small_ewe_count);
						}
						mCurrentSheepNum += mCurrentEweNum * 5;
						System.out.println("总的羊的数量：" + mCurrentSheepNum);
						mCurrentMonth++;
						if (mCurrentMonth >= 13) {
							mCurrentMonth -= 12;
						}
						try {
							mObj.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						mObj.notify();
						mCurrentMonth++;
						if (mCurrentMonth >= 13) {
							mCurrentMonth -= 12;
						}
						try {
							mObj.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					mObj.notify();
				}
//					lock.unlock();
				
			}
			mCurrentYear++;
		}
//		condition.signal();
//		lock.unlock();
	}

	@Override
	public void consume() {
		// 实现消费方法
//		lock.lock();
		synchronized (mObj) {
			if (mCurrentMonth == 2) {
				mObj.notify();
				int sellCount = (int) (mCurrentSheepNum * 0.2);// 卖掉的羊的总数
				int sell_ewe_count = 0;
				int sell_ram_count = 0;
				if (sellCount % 2 != 0) {
					mCurrentRawNum = mCurrentRawNum - ((sellCount - 1) / 2 + 1);
					sell_ram_count = (sellCount - 1) / 2 + 1;
					sell_ewe_count = (sellCount - 1) / 2;
					if (sell_ewe_count >= mCurrentEweNum) {
						sell_ewe_count = mCurrentEweNum;
						mCurrentEweNum = 0;
					} else {
						mCurrentEweNum = mCurrentEweNum - sell_ewe_count;
					}
				} else {
					mCurrentRawNum = mCurrentRawNum - (sellCount / 2);
					sell_ram_count = sellCount / 2;
					sell_ewe_count = sellCount / 2;
					if (sell_ewe_count >= mCurrentEweNum) {
						sell_ewe_count = mCurrentEweNum;
						mCurrentEweNum = 0;
					} else {
						mCurrentEweNum = mCurrentEweNum - (sellCount / 2);
						sell_ewe_count = sellCount / 2;
					}
				}
				mCurrentSheepNum = mCurrentRawNum + mCurrentEweNum
						+ mSmallEweCount + pro_small_ewe_count;
				System.out.print("第" + mCurrentYear +"年 卖掉" + sell_ewe_count + "只母羊" + ", " + "卖掉"
						+ sell_ram_count + "只公羊， ");
				System.out.println("第" + mCurrentYear + "年" + "公羊的数量："
						+ mCurrentRawNum + ", 母羊的数量：" + mCurrentEweNum
						+ ",小母羊的数量：" + (mSmallEweCount + pro_small_ewe_count)
						+ ",羊的总数：" + mCurrentSheepNum);
				try {
					mObj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				mObj.notify();
				try {
					mObj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*try {
					mObj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
			}
//			condition.signal();
//			lock.unlock();
//			mObj.notify();
		}
	}

	// get/set方法

	public int getmCurrentRawNum() {
		return mCurrentRawNum;
	}

	public int getmCurrentSheepNum() {
		return mCurrentSheepNum;
	}

	public void setmCurrentSheepNum(int mCurrentSheepNum) {
		this.mCurrentSheepNum = mCurrentSheepNum;
	}

	public void setmCurrentRawNum(int mCurrentRawNum) {
		this.mCurrentRawNum = mCurrentRawNum;
	}

	public int getmCurrentEweNum() {
		return mCurrentEweNum;
	}

	public void setmCurrentEweNum(int mCurrentEweNum) {
		this.mCurrentEweNum = mCurrentEweNum;
	}

	public int getmHeadNum() {
		return mHeadNum;
	}

	public void setmHeadNum(int mHeadNum) {
		this.mHeadNum = mHeadNum;
	}

	public int getmLegNum() {
		return mLegNum;
	}

	public void setmLegNum(int mLegNum) {
		this.mLegNum = mLegNum;
	}

	public int getmInitYear() {
		return mInitYear;
	}

	public void setmInitYear(int mInitYear) {
		this.mInitYear = mInitYear;
	}

	public int getmCurrentYear() {
		return mCurrentYear;
	}

	public void setmCurrentYear(int mCurrentYear) {
		this.mCurrentYear = mCurrentYear;
	}

	public int getmCurrentMonth() {
		return mCurrentMonth;
	}

	public void setmCurrentMonth(int mCurrentMonth) {
		this.mCurrentMonth = mCurrentMonth;
	}

}
