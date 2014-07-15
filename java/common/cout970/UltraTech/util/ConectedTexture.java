package common.cout970.UltraTech.util;

public enum ConectedTexture {
	
	//0 sides
	t0(2,0,2,
		0,1,0,
		2,0,2),
	//all sides
	t1(2,1,2,
		1,1,1,
		2,1,2),
	//1 side
	t2(2,1,2,
		0,1,0,
		2,0,2),
	
	t3(2,0,2,
		0,1,0,
		2,1,2),
				
	t4(2,0,2,
		0,1,1,
		2,0,2),
						
	t5(2,0,2,
		1,1,0,
		2,0,2),
	//2 sides perpendicular
	t6(2,1,2,
		0,1,0,
		2,1,2),
		
	t7(2,0,2,
		1,1,1,
		2,0,2),
	//3 sides
	t8(2,1,2,
		1,1,1,
		2,0,2),
	
	t9(2,0,2,
		1,1,1,
		2,1,2),
						
	t10(2,1,2,
		0,1,1,
		2,1,2),
								
	t11(2,1,2,
		1,1,0,
		2,1,2),
	//corners
	t12(2,1,1,
		0,1,1,
		2,0,2),
		
	t13(1,1,2,
		1,1,0,
		2,0,2),
				
	t14(2,0,2,
	    1,1,0,
		1,1,2),
						
	t15(2,0,2,
        0,1,1,
	    2,1,1),
								
	t16(2,1,0,
	    0,1,1,
	    2,0,2),
										
	t17(0,1,2,
	    1,1,0,
		2,0,2),
												
	t18(2,0,2,
	    1,1,0,
	    0,1,2),
														
	t19(2,0,2,
		0,1,1,
		2,1,0),
		
	t20(0,1,0,
		1,1,1,
		0,1,0);

	public int[] a;
	public static ConectedTexture[] tex = {t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20};

	private ConectedTexture(int... a){
		this.a = a;
	}

	public static int getTex(boolean... a){
		for(ConectedTexture b : tex)
			if(isTheSame(a, b.a))return b.ordinal();
		return 0;
	}

	public static boolean isTheSame(boolean[] a,int[] b){
		for(int f = 0;f<a.length;f++){
			if(a[f] != (b[f]==1)){
				if(b[f] != 2)return false;
			}
		}
		return true;
	}
	
	
}
