package testpc;

public class Cattle extends Animal{

	private int mCurrentAnimalNum; //当前总的牛数
	private int mCurrentBullNum; //当前公牛数
	private int mCurrentBossyNum; //当前母牛数
	private int mHeadNum;
	private int mLegNum;
	private int mInitYear;
	private int mCurrentYear;
	private int mCurrentMonth;
	private int mBadyNum;
	
	private Object mObj = new Object();
	
	public Cattle(int mInitBullNum, int mInitBossyNum, int mInityear, int mInitmonth) {
		mCurrentAnimalNum = mInitBullNum + mInitBossyNum;
		mCurrentBullNum = mInitBullNum;
		mCurrentBossyNum = mInitBossyNum;
		mCurrentYear = mInityear;
		mInitYear = mInityear;
		mCurrentMonth = mInitmonth;
	}
	
	@Override
	public void produce() {
		//实现生产方法
		synchronized (mObj)
		{
			System.out.println("第"+mCurrentYear+"年 "+mCurrentMonth+"月,生育小牛"
					+mBadyNum+"只，现在农场一共有"+mCurrentAnimalNum+"只牛");
		}	
		
	}

	@Override
	public void consume() {
		//实现消费方法
		synchronized (mObj)
		{
			System.out.println("第"+mCurrentYear+"年 "+mCurrentMonth+"月,出售"
					+0+"只，现在农场一共有"+mCurrentAnimalNum+"只牛");
		}	
		
	}

	
	//get/set方法


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
	
	

}
