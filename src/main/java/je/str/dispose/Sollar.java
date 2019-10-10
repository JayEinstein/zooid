package je.str.dispose;

public class Sollar {
	
	

	/**
	 * 梯子塔 计算行数，行数等于 1+2+3+...+(n-1)+n = n*(n+1)/2
	 * @param n 
	 * @return
	 */
	public int high(int n) {
		return n*(n+1)/2;
	}
	
	/**
	 * 梯子塔 砖数，等于各个行数之和 1+3+6+10+15+...+(n-1)*n/2+n*(n+1)/2 = n*(n+1)/4+n*(n+1)*(2*n+1)/12
	 * @param n 
	 * @return
	 */
	public int volume(int n) {
		return n*(n+1)/4+n*(n+1)*(2*n+1)/12;
	}
}
