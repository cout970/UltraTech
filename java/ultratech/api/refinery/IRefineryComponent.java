package ultratech.api.refinery;

import ultratech.api.reactor.IReactorCoreEntity;

public interface IRefineryComponent {

	public void RestaureBlock();
	public boolean isFormed();
	public IRefineryCoreEntity getCore();
	public void setCore(IRefineryCoreEntity c);
	public void refreshCore();
	public void setCoreCoords(int x, int y, int z);
}
