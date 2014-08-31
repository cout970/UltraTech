package common.cout970.UltraTech.managers;

import ultratech.api.power.StorageInterface.PowerIO;

public enum MachineData {
	/**
	 * 2 burning tick = 1MeV
	 */
	Generator		( 1600d	,1	,1d			,1),//1coal => 800Mev
	Solar_Panel		(   10d	,1	,0.2d		,1),//1QP => 5 ticks
	Fluid_Generator (  800d	,2	,1d			,1),//Wip
	Turbine			(  800d	,2	,0.1d		,1),//per mB
	LavaGenerator   (  800d	,2	,10d	   	,1),//QP per 1mb
	WindMill		(  400d ,2	,1.5d		,1),//idk (I don't know)

	Furnace			(  800d	,2	,100d		,0),//1 items cost
	Purifier		(  800d	,2	,150d		,0),
	Cutter			(  800d	,2	,200d		,0), 
	CVD				(  800d	,1	,100d		,0),
	Laminator		(  800d ,1	,100d		,0),
	
	Miner    		(16000d	,3	,50d		,0),//per block
	MAssembly		(  800d ,3	,100d		,0),
	Charge_Station	( 3200d	,1	,200d		,2),//per tick
	Climate_Station	(100000d,3	,100000d	,0),
	Fermenter		(  800d	,1	,1d			,0),//QP per mB
	Tesseract		(  800d ,3	,0d			,0),
	ChemicalPlant	(  800d ,2	,100d		,0),//per item
	Heater			( 1600d	,2	,100d		,0),//per 200proces
	Pump			(  600d ,2  ,150d		,0),//per 1000mB
	Engine    		(  800d ,2  ,8			,0),//1QP => 2MJ
	Dynamo     		( 1600d	,2	,1d			,0),
	Kinetic_Generator(1600d ,2	,1d			,1),
	Transformer 	(  800d ,2  ,0d         ,2),
	
	Battery_T1		(    60000d	,1	,0			,2),//3 cells
	Battery_T2		(   360000d	,2	,0			,2),//18 cells
	Battery_T3		(  4000000d	,3	,0			,2),//162 cells
	Battery_T4		( 20000000d	,4	,0			,2);//729 cells
	
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
