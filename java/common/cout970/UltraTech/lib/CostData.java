package common.cout970.UltraTech.lib;

import api.cout970.UltraTech.MeVpower.StorageInterface.MachineTipe;

public enum CostData {
	/**
	 * 1FT = 2DP => 0.5 DP = 1FT
	 */
	Generator		( 1600d	,1	,5d			,1),
//	Boiler   		(  800d	,2	,2d			,0),
	Solar_Panel		(   10d	,1	,0.2d		,1),
	Furnace			(  800d	,2	,100d		,0),
	Purifier		(  800d	,2	,100d		,0),
	Cutter			(  800d	,2	,100d		,0), 
	MA				( 1600d  ,3	,100d		,0),
	Charge_Station	( 1600d	,1	,80d		,2),
	Climate_Station (20000d	,3	,20000d		,0),
	CVD				(  800d	,1	,100d		,0),
	Fermenter		(  800d	,1	,10			,0),
	Fluid_Generator (  800d	,2	,0d			,1), 
	Turbine			(  800d	,2	,20d			,1),
	Miner    		(  800d	,3	,15.2d		,0),
	Dynamo     		( 1600d	,2	,1d			,0),
	Tesseract		(20000d ,3	,0			,0),
	WindMill		(  800d ,2	,1d/6		,1),
	LavaGenerator   (  800d	,2	,0.4d		,1),
	Laminator		(  800d ,1	,40d		,0),
	ChemicalPlant	(  800d ,2	,20d		,0),
	Heater			(  400d	,2	,50d			,0), 
	
	Storage_1		(    64000d	,1	,0			,2),
	Storage_2		(   400000d	,2	,0			,2),
	Storage_3		(  7000000d	,3	,0			,2);
	
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
