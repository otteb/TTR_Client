package ObserverPattern;

import java.util.List;

/**
 * Created by fjameson on 2/28/18.
 */

public class Observable {
    public List<Observer> observerList;
    public boolean changed = false;
    public void addObserver(Observer obs)
    {
        observerList.add(obs);
    }

    public void deleteObserver(Observer obs)
    {
        observerList.remove(obs);
    }

    public void deleteObservers()
    {
        observerList.retainAll(observerList);
    }

    public int countObservers()
    {
        return observerList.size();
    }

    protected void setChanged()
    {
        changed= true;
    }

    protected  void clearChanged()
    {
        changed=false;
    }

    public boolean hasChanged()
    {
        return changed;
    }

    public void notifyObservers(Observer obs)
    {

    }

    public void notifyObservers(Object o, Observer obs)
    {
        obs.update(this, o);
    }







}
