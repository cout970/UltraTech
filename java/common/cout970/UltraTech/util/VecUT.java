package common.cout970.UltraTech.util;

import codechicken.lib.vec.Vector3;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public class VecUT {

	public double x;
	public double y;
	public double z;
	
	public VecUT(double i, double j, double k){
		x = i;
		y = j;
		z = k;
	}
	
	public static String toString(VecUT a) {
		return " Data x = "+a.x+" y = "+a.y+" z = "+a.z+" ";
	}
	
	public VecUT(Vec3 v){
		this(v.xCoord, v.yCoord, v.zCoord);
	}
	
	public VecUT(TileEntity v) {
		this(v.xCoord, v.yCoord, v.zCoord);
	}

	public static VecUT sumeVec(VecUT a, VecUT b){
		return new VecUT(a.x+b.x, a.y+b.y, a.z+b.z);
	}
	
	public static VecUT inverseVec(VecUT a){
		return new VecUT(-a.x, -a.y, -a.z);
	}
	
	public static VecUT VecForScalar(VecUT a, double b){
		return new VecUT(a.x*b, a.y*b, a.z*b);
	}
	
	public static double prodScalar(VecUT a, VecUT b){
		return a.x*b.x+a.y*b.y+a.z*b.z;
	}
	
	public static double getModule(VecUT a){
		return Math.sqrt((a.x*a.x)+(a.y*a.y)+(a.z*a.z));
	}
	
	public static VecUT prodVec(VecUT a, VecUT b){
		double x,y,z;
		x = (a.y*b.z)-(a.z*b.y);
		y = (a.z*b.x)-(a.x*b.z);
		z = (a.x*b.y)-(a.y*b.x);
		return new VecUT(x,y,z);
	}
	
	public static VecUT unitVec(VecUT a){
		double module = getModule(a);
		double x,y,z;
		x = a.x/module;
		y = a.y/module;
		z = a.z/module;
		return new VecUT(x,y,z);
	}

	public static double getAngle(VecUT a, VecUT b){
		// (a*b)/(|a|*|b|)=cos(x)
		double c = prodScalar(a,b)/(getModule(a)*getModule(b));
		return Math.acos(c);
	}

	public static double getDistance(VecUT a, VecUT b){
		return getModule(sumeVec(a, inverseVec(b)));
	}
}
