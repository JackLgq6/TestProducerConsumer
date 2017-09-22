package testpc;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Chicken extends Animal {

	private BigDecimal mCurrentAnimalNum = new BigDecimal("0"); // 当前鸡的数量
	private int mHeadNum; // 头的数量
	private int mLegNum; // 脚的数量
	private int mInitYear; // 初始年份
	private int mCurrentYear = 1; // 当前年份
	private int mCurrentMonth; // 当前月份
	private BigDecimal mBabyNum = new BigDecimal("0"); // 每个月产的小鸡数
	private boolean mChickenFlag;
	private BigDecimal bi = new BigDecimal("20");

	private Object mObj = new Object();
	private BigDecimal eggs = new BigDecimal("0");

	public Chicken(BigDecimal mInitchickennum, int mInityear, int mInitmonth) {
		mCurrentAnimalNum = mInitchickennum;
		mInitYear = mInityear;
		mCurrentMonth = mInitmonth;
	}

	@Override
	public void produce() {
		// 实现生产方法
		for (int i = 1; i <= mInitYear; i++) {
			
			for (int j = 1; j <= 12; j++) {
				synchronized (mObj) {
					if (mCurrentMonth == 2) {
						try {
							mObj.notify();
							eggs = eggs.add(mCurrentAnimalNum.multiply(bi));
							if (mCurrentYear > 1) {
								mCurrentAnimalNum = mCurrentAnimalNum
										.add(mBabyNum);
							}
							mBabyNum = eggs.multiply(new BigDecimal("0.2"));
							eggs = new BigDecimal("0");
							mObj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						eggs = eggs.add(mCurrentAnimalNum.multiply(bi));
					}
					mCurrentMonth++;
					if (mCurrentMonth >= 13) {
						mCurrentMonth -= 12;
					}
				}

			}
			System.out.println("历时" + mCurrentYear + "年 " + "小鸡有" + mBabyNum.toBigInteger()
					+ "只，下蛋母鸡有" + mCurrentAnimalNum.toBigInteger() + "只，" + "农场一共有"
					+ (mCurrentAnimalNum.add(mBabyNum).toBigInteger()) + "只鸡，蛋一共有" + eggs.toBigInteger()
					+ "个");
			if (mCurrentYear == mInitYear) {
				mChickenFlag = true;
			}
			mCurrentYear++;

		}
	}

	@Override
	public void consume() {
		// 实现消费方法

		synchronized (mObj) {
			if (mCurrentMonth != 2) {
				mObj.notify();
				try {
					mObj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				mObj.notify();
				BigDecimal mSkillorSell = mCurrentAnimalNum
						.multiply(new BigDecimal("0.2"));
				mCurrentAnimalNum = mCurrentAnimalNum.subtract(mSkillorSell);
				try {
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

	public BigInteger getmCurrentAnimalNum() {
		return mCurrentAnimalNum.add(mBabyNum).toBigInteger();
	}

	public void setmCurrentAnimalNum(BigDecimal mCurrentAnimalNum) {
		this.mCurrentAnimalNum = mCurrentAnimalNum;
	}

	public boolean ismChickenFlag() {
		return mChickenFlag;
	}

	public void setmChickenFlag(boolean mChickenFlag) {
		this.mChickenFlag = mChickenFlag;
	}

}
