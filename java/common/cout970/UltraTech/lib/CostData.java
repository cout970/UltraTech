package common.cout970.UltraTech.lib;

import api.cout970.UltraTech.Wpower.StorageInterface.MachineTipe;

public enum CostData {
	/**
	 * 1MeV = 8 FT => 0.125 MeV = 1FT
	 */
	Generator		(  200d	,1	,1/8		,1),
	Boiler   		(  400d	,2	,2d			,0),
	Solar_Panel		(    5d	,1	,0.05d		,1),
	Furnace			(   50d	,2	,25d		,0),
	Purifier		(   50d	,2	,25d		,0),
	Cutter			(   60d	,2	,30d		,0), 
	MA				(  400d  ,3	,50d		,0),
	Charge_Station	(  400d	,1	,20d		,2),
	Climate_Station ( 1200d	,3	,1200d		,0),
	CVD				(   80d	,1	,40d		,0),
	Fermenter		(  200d	,1	,3/8		,0),
	Fluid_Generator (  200d	,2	,0d			,1), 
	Turbine			(  200d	,2	,2d			,1),
	Miner    		(  800d	,3	,15.2d		,0),
	Dynamo     		(  400d	,2	,1d			,0),
	Tesseract		( 1250d ,3	,0			,0),
	WindMill		(  200d ,2	,1/6		,1),
	LavaGenerator   (  200d	,2	,1			,1),
	
	Storage_1		( 	20000d	,1	,0			,2),
	Storage_2		(  120000d	,2	,0			,2),
	Storage_3		( 1200000d	,3	,0			,2);
	
	public double cap,use;
	public int tier;
	public MachineTipe type;
	
	private CostData(double cap,int tier,double use,int inter){
		this.cap = cap;
		this.tier = tier;
		this.use = use;
		if(inter == 0)type = MachineTipe.Nothing;
		if(inter == 1)type = MachineTipe.Output;
		if(inter == 2)type = MachineTipe.Storage;
		if(inter == 3)type = MachineTipe.Input;
	}
}
