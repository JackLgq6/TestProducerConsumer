package testpc;

import java.util.Random;

public class Sheep extends Animal {

	private int mCurrentSheepNum; // 当前总的羊数
	private int mCurrentRawNum; // 当前公羊数
	private int mCurrentEweNum; // 当前母羊数
	private int mSmallEweCount = 0;// 第1,3,5等年小母羊的数量
	private int mSmallRamCount = 0;// 小公羊数量
	private int pro_small_ewe_count = 0; //第2,4,6等年生的小羊数量
	
	private int mHeadNum;
	private int mLegNum;
	private int mInitYear;
	private int mCurrentYear;
	private int mCurrentMonth;
	private boolean mSheepFlag;

	private Object mObj = new Object();

	public Sheep(int mInitRawNum, int mInitEweNum, int mInityear, int mInitmonth) {
		mCurrentSheepNum = mInitRawNum + mInitEweNum;
		mCurrentRawNum = mInitRawNum;
		mCurrentEweNum = mInitEweNum;
		mCurrentYear = 1;
		mInitYear = mInityear;
		mCurrentMonth = mInitmonth;
	}

	@Override
	public void produce() {
		for (int i = 1; i <= mInitYear; i++) {
			for (int j = 1; j <= 12; j++) {
				synchronized (mObj) {
					int pro_small_ram_count = 0;
					if (mCurrentMonth == 10) {
						mObj.notify();
						if (mCurrentYear % 2 != 0 && mCurrentYear != 1) {
							mCurrentEweNum += mSmallEweCount;
							mSmallEweCount = 0;
						}
						if (mCurrentYear % 2 == 0 && mCurrentYear != 2) {
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
						}
						mCurrentSheepNum += mCurrentEweNum * 5;
						System.out.print("总的羊的数量：" + mCurrentSheepNum + ", ");
						mCurrentMonth++;
						if (mCurrentMonth >= 13) {
							mCurrentMonth -= 12;
						}
						try {
							mObj.wait();
						} catch (InterruptedException e) {
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
							e.printStackTrace();
						}
					}
				}
			}
			if(mCurrentYear==mInitYear){
				mSheepFlag = true;
			}
			mCurrentYear++;
		}
	}

	@Override
	public void consume() {
		// 实现消费方法
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
				System.out.println("第" + mCurrentYear +"年 卖掉" + sell_ewe_count + "只母羊" + ", " + "卖掉"
						+ sell_ram_count + "只公羊， " + "第" + mCurrentYear + "年" + "公羊的数量："
						+ mCurrentRawNum + ", 母羊的数量：" + mCurrentEweNum
						+ ",小母羊的数量：" + (mSmallEweCount + pro_small_ewe_count)
						+ ",羊的总数：" + mCurrentSheepNum);
				try {
					mObj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				mObj.notify();
				try {
					mObj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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

	public boolean ismSheepFlag() {
		return mSheepFlag;
	}

	public void setmSheepFlag(boolean mSheepFlag) {
		this.mSheepFlag = mSheepFlag;
	}
	

}
