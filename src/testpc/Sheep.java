package testpc;

public class Sheep extends Animal{

	private int mCurrentAnimalNum; //当前总的羊数
	private int mCurrentRawNum; //当前公羊数
	private int mCurrentEweNum; //当前母羊数
	private int mHeadNum;
	private int mLegNum;
	private int mInitYear;
	private int mCurrentYear;
	private int mCurrentMonth;
	private int mBadyNum;
	
	private Object mObj = new Object();
	
	public Sheep(int mInitRawNum, int mInitEweNum, int mInityear, int mInitmonth) {
		mCurrentAnimalNum = mInitRawNum + mInitEweNum;
		mCurrentRawNum = mInitRawNum;
		mCurrentEweNum = mInitEweNum;
		mCurrentYear = mInityear;
		mInitYear = mInityear;
		mCurrentMonth = mInitmonth;
	}
	
	@Override
	public void produce() {
		//实现生产方法
		synchronized (mObj)
		{
			System.out.println("第"+mCurrentYear+"年 "+mCurrentMonth+"月,生育小羊"
					+mBadyNum+"只，现在农场一共有"+mCurrentAnimalNum+"只羊");
		}	
		
	}

	@Override
	public void consume() {
		//实现消费方法
		synchronized (mObj)
		{
			System.out.println("第"+mCurrentYear+"年 "+mCurrentMonth+"月,出售"
					+0+"只，现在农场一共有"+mCurrentAnimalNum+"只羊");
		}	
		
	}

	
	//get/set方法
	
	
	public int getmCurrentRawNum() {
		return mCurrentRawNum;
	}

	public int getmCurrentAnimalNum() {
		return mCurrentAnimalNum;
	}

	public void setmCurrentAnimalNum(int mCurrentAnimalNum) {
		this.mCurrentAnimalNum = mCurrentAnimalNum;
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
