package r.project;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class DebuffObservable extends Observable{
    private static DebuffObservable instance;
    public void debuff(){
        setChanged();
        notifyObservers();
    }

    public static DebuffObservable getInstance(){
        if (instance==null){
            instance=new DebuffObservable();
        }
        return instance;
    }
}

