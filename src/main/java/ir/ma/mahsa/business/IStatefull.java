package ir.ma.mahsa.business;

public interface IStatefull<S> {
    S getState();
    void setState(S state);
}
