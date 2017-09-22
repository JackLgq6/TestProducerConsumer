package testpc;

import java.util.Random;

public class Cattle extends Animal {

	private int mCurrentAnimalNum; // 当前总的牛数
	private int mCurrentBullNum; // 当前公牛数
	private int mCurrentBossyNum; // 当前母牛数
	private int mHeadNum;
	private int mLegNum;
	private int mInitYear;
	private int mCurrentYear;
	private int mCurrentMonth;
	private int mBadyNum = 0; // 小母牛数量
	private boolean mCattleFlag;


	public Cattle(int mInitBullNum, int mInitBossyNum, int mInityear,
			int mInitmonth) {
		mCurrentAnimalNum = mInitBullNum + mInitBossyNum;
		mCurrentBullNum = mInitBullNum;
		mCurrentBossyNum = mInitBossyNum;
		mCurrentYear = mInityear;
		mInitYear = mInityear;
		mCurrentMonth = mInitmonth;
	}

	/*
	 * 公牛和母牛各1头，每2年生产1只小牛。小牛2年会后开始生产，小牛的性别随机。
	 */
	@Override
	public void produce() {
		// 实现生产方法
		// 母牛每两年生产一头小牛
		for (int i = 2; i <= mInitYear; i += 2) {
			
			// 计算母牛数量
			mCurrentBossyNum += mBadyNum;
			// System.out.println("第"+i+"年生产了"+mCurrentBossyNum+"头小牛");
			// 每隔两年清空小牛数量
			mBadyNum = 0;
			// 根据随机数确定每头小牛的性别
			for (int j = 1; j <= mCurrentBossyNum; j++) {
				Random random = new Random();
				// 随机数gender表示小牛性别 0:公 1:母
				int gender = random.nextInt(2);
				// 如果是公牛，直接将小牛加入mCurrentBullNum
				if (0 == gender) {
					// System.out.println("第"+i+"年生育的小牛的性别是：公");
					mCurrentBullNum++;
				} else {
					// System.out.println("第"+i+"年生育的小牛的性别是：母");
					// 如果是母牛，将小母牛数量加1
					mBadyNum++;
				}

			}
			System.out.println("第" + i + "年生育的小牛数量是：" + mCurrentBossyNum);
			// System.out.println("第"+i+"年生产了"+mBadyNum+"头小母牛");
			// 当年牛的总数：公牛+母牛+小母牛
			mCurrentAnimalNum = mCurrentBossyNum + mCurrentBullNum + mBadyNum;
			System.out.println("第" + i + "年一共有" + mCurrentAnimalNum + "头牛");
			if (mCurrentYear == mInitYear) {
				mCattleFlag = true;
			}
			mCurrentYear += 2;
		}


	}

	@Override
	public void consume() {
		
	}

	// get/set方法

	public int getmCurrentBullNum() {
		return mCurrentBullNum;
	}

	public int getmCurrentAnimalNum() {
		return mCurrentAnimalNum;
	}

	public void setmCurrentAnimalNum(int mCurrentAnimalNum) {
		this.mCurrentAnimalNum = mCurrentAnimalNum;
	}

	public void setmCurrentBullNum(int mCurrentBullNum) {
		this.mCurrentBullNum = mCurrentBullNum;
	}

	public int getmCurrentBossyNum() {
		return mCurrentBossyNum;
	}

	public void setmCurrentBossyNum(int mCurrentBossyNum) {
		this.mCurrentBossyNum = mCurrentBossyNum;
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

	public boolean ismCattleFlag() {
		return mCattleFlag;
	}

	public void setmCattleFlag(boolean mCattleFlag) {
		this.mCattleFlag = mCattleFlag;
	}

}
