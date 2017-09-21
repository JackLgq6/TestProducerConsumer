package testpc;

/*
 *  请利用填空题第3题中Java所具备的特性，完成如下编程题。
    有一家农场，养了一群小动物。养的有鸡40只，羊10只，牛2头。

    其中鸡全是母鸡，每只母鸡每个月有20天下蛋，每次下蛋1颗，其余时间休息。
    每年2月份农夫会人工孵化鸡蛋剩余数的20%，其余鸡蛋全部出售，并且会出售
    或宰杀20%母鸡。孵化出来的小鸡全是母鸡，小鸡一年之后开始下蛋。
    
    羊群里面有8只母羊，2只公羊。每只母羊每年10月生产5只小羊，性别随机。每年
    2月农夫出售20%羊，公羊母羊各一半（若为20%的羊数为奇数，多的那一头羊为公
    羊）。小羊2年后开始生产，性别随机。
    
    公牛和母牛各1头，每2年生产1只小牛。小牛2年会后开始生产，小牛的性别随机。
    
	现在是2017年9月1日，请问10年后农产有多少只鸡，多少头养，多少头牛？请问10年
	后动物的头和脚的数量各是多少。同时请注意闰年天数。
*/
public abstract class Animal {
	private int mCurrentAnimalNum;
	private int mHeadNum;
	private int mLegNum;
	private int mInitYear;
	private int mCurrentYear;
	private int mCurrentMonth;
	
	public abstract void produce();
	public abstract void consume();
	
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
