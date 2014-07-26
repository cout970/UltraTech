package ultratech.api.reactor;

public interface IReactorComponent {

	public void RestaureBlock();
	public boolean isFormed();
	public IReactorCoreEntity getCore();
	public void setCore(IReactorCoreEntity c);
}
