package common.cout970.UltraTech.managers;

import ultratech.api.power.StorageInterface.PowerIO;

public enum MachineData {
	/**
	 * 2 burning tick = 1MeV
	 */
	Generator_T1	( 3200d	,1	,1d			,1),//1coal => 800Mev
	Generator_T2	( 3200d	,1	,1d			,1),
	Solar_Panel	(   10d	,1	,0.2d		,1),//1Mev => 5 ticks
	Fluid_Generator (  800d	,2	,1d			,1),//Wip
	Turbine			(  800d	,2	,0.1d		,1),//per mb
	LavaGenerator   (  800d	,2	,10d	   		,1),//MeV per 1mb
	WindMill		(  400d ,2	,1.5d		,1),//idk (I don't know)

	Furnace			( 1000d	,2	,100d		,0),//1 items cost
	Purifier		( 1000d	,2	,150d		,0),
	Cutter			( 1000d	,2	,200d		,0), 
	CVD				( 1000d	,1	,100d		,0),
	Laminator		( 1000d ,1	,100d		,0),
	
	Miner    		(32000d	,3	,100d		,0),//per block
	MAssembly		( 1600d ,3	,100d		,0),
	Charge_Station	( 3200d	,1	,200d		,2),//per tick
	Climate_Station(200000d	,3	,200000d	,0),
	Fermenter		(  800d	,1	,10d		,0),//Mev per mb
	Tesseract		(20000d ,3	,0d			,0),
	ChemicalPlant	( 1000d ,2	,100d		,0),//per item
	Heater			( 1600d	,2	,100d		,0),//per 200proces
	Pump			( 2000d ,2  ,150d		,0),//per 1000mb
	
	Dynamo     		( 1600d	,2	,1d			,0),
	
	Battery_T1		(    64000d	,1	,0			,2),
	Battery_T2		(   500000d	,2	,0			,2),
	Battery_T3		(  8000000d	,3	,0			,2);
	
	public double cap,use;
	public int tier;
	public PowerIO type;
	
	private MachineData(double cap,int tier,double use,int inter){
		this.cap = cap;
		this.tier = tier;
		this.use = use;
		if(inter == 0)type = PowerIO.Nothing;
		if(inter == 1)type = PowerIO.Output;
		if(inter == 2)type = PowerIO.Storage;
		if(inter == 3)type = PowerIO.Input;
	}
}
