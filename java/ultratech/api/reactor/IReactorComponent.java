package ultratech.api.reactor;

public interface IReactorComponent {

	public void RestaureBlock();
	public boolean isFormed();
	public IReactorCoreEntity getCore();
	public void setCore(IReactorCoreEntity c);
	public void refreshCore();
	public void setCoreCoords(int x, int y, int z);
}
