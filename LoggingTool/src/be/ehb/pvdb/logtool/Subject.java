package be.ehb.pvdb.logtool;

public interface Subject {
	public void registerListener (Listener o);
	public void notifyListeners();
}
