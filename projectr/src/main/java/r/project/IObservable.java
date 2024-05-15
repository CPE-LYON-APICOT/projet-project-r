package r.project;

interface IObservable {
    void addObserver(IObserver observer);
    void notifyObservers();
}
