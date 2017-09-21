package testpc;

public class Chicken extends Animal {

	private int mCurrentAnimalNum; //当前鸡的数量
	private int mHeadNum; //头的数量
	private int mLegNum; //脚的数量
	private int mInitYear; //初始年份
	private int mCurrentYear=1; //当前年份
	private int mCurrentMonth; //当前月份
	private int mBabyNum; //每个月产的小鸡数

	private Object mObj = new Object();
	private int eggs = 0;

	public Chicken(int mInitchickennum, int mInityear, int mInitmonth) {
		mCurrentAnimalNum = mInitchickennum;
		mInitYear = mInityear;
		mCurrentMonth = mInitmonth;
	}
	/*
	 * 请利用填空题第3题中Java所具备的特性，完成如下编程题。 有一家农场，养了一群小动物。养的有鸡40只，羊10只，牛2头。
	 * 
	 * 其中鸡全是母鸡，每只母鸡每个月有20天下蛋，每次下蛋1颗，其余时间休息。 每年2月份农夫会人工孵化鸡蛋剩余数的20%，其余鸡蛋全部出售，并且会出售
	 * 或宰杀20%母鸡。孵化出来的小鸡全是母鸡，小鸡一年之后开始下蛋。
	 * 
	 * 
	 * 现在是2017年9月1日，请问10年后农产有多少只鸡，多少头养，多少头牛？请问10年 后动物的头和脚的数量各是多少。同时请注意闰年天数。
	 */

	@Override
	public void produce() {
		// 实现生产方法
		for (int i = 1; i <= mInitYear; i++) {
			for (int j = 1; j <= 12; j++) {
				synchronized (mObj) {
					if (mCurrentMonth == 2) {
						try {
							mObj.notify();							
							eggs += mCurrentAnimalNum * 20;
//							System.out.println("二月初的蛋的数量是："+eggs);
							if (mCurrentYear > 1) {
								mCurrentAnimalNum += mBabyNum;
							}
							mBabyNum = (int)(eggs * 0.2);							
//							System.out.println("第" + mCurrentYear + "年 " + mCurrentMonth + "月,生育小鸡" + mBabyNum + "只，现在农场一共有"
//									+ mCurrentAnimalNum + "只下蛋母鸡");
							eggs = 0;
//							System.out.println("第" + mCurrentYear + "年 " + mCurrentMonth+"月，蛋的数量是："+eggs);
							mObj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else {
						eggs += mCurrentAnimalNum * 20;	

//						System.out.println("第" + mCurrentYear + "年 " + mCurrentMonth+"月，蛋的数量是："+eggs+"现在农场一共有"
//								+ mCurrentAnimalNum + "只下蛋母鸡");
					}					
					mCurrentMonth++;
					if (mCurrentMonth >= 13) {
						mCurrentMonth -= 12;
					}			
				}

			}
			System.out.println("历时" + mCurrentYear + "年 " + "小鸡有"+mBabyNum+"只，下蛋母鸡有"+mCurrentAnimalNum+"只，"
					+"农场一共有"+(mCurrentAnimalNum+mBabyNum)+"只鸡，蛋一共有"+eggs+"个");
			mCurrentYear++;
			
		}
	}

	@Override
	public void consume() {
		// 实现消费方法
		
			synchronized (mObj) {
				if(mCurrentMonth != 2) {
					mObj.notify();
					try {
						mObj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					mObj.notify();
				int mSkillorSell = (int)(mCurrentAnimalNum * 0.2);
				mCurrentAnimalNum = mCurrentAnimalNum-mSkillorSell;
				try {
//					System.out.println(
//						"第" + mCurrentYear + "年 " + mCurrentMonth + "月,出售宰杀" + mSkillorSell
//						+ "只下蛋母鸡，现在农场一共有" + mCurrentAnimalNum + "只下蛋母鸡");
					mObj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
				
			}
		
	
	}

	// get/set方法
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

	public int getmInitYear() {
		return mInitYear;
	}

	public void setmInitYear(int mInitYear) {
		this.mInitYear = mInitYear;
	}

	public int getmCurrentAnimalNum() {
		return mCurrentAnimalNum;
	}

	public void setmCurrentAnimalNum(int mCurrentAnimalNum) {
		this.mCurrentAnimalNum = mCurrentAnimalNum;
	}

}
