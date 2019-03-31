package ir.ma.mahsa.business;

import java.io.IOException;
import java.util.List;

public interface IStateManager {
    void saveState(IStatefull<?> statefull) throws IOException;
    <S> void retrieveState(IStatefull<S> statefull) throws IOException, ClassNotFoundException;
    boolean isAutoSave();
}
