package ultratech.api.reactor;

public interface IReactorBlock {

	/**
	 * 0 only can go inside the structure
	 * 1 can go in or out
	 * 2 only can go on the surface
	 * @return
	 */
	public int getLayer();
}
