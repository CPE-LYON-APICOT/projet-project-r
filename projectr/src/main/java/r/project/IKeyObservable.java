package r.project;

public interface IKeyObservable {
    void addObserver(IKeyObserver observer);
    void removeObserver(IKeyObserver observer);
    void notifyObservers(char key);
}