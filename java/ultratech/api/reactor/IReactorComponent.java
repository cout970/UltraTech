package ultratech.api.reactor;

public interface IReactorComponent {

	public void RestaureBlock();
	public boolean isFormed();
	public IReactorComponent getCore();
	public void setCore(IReactorComponent c);
}
