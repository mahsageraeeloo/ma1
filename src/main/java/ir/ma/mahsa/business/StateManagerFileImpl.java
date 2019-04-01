package ir.ma.mahsa.business;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StateManagerFileImpl implements IStateManager, IHasLifeCycle {
    private Properties properties;

    public StateManagerFileImpl() {
        InstanceRegistry.getInstance().register(this);
        properties = new Properties();
        try {
            properties.load(StateManagerFileImpl.class.getResourceAsStream("/manager.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        try {
            List<IStatefull> iStatefulls = InstanceRegistry.getInstance().lookupMultiple(IStatefull.class);
            for (IStatefull iStatefull : iStatefulls) {
                retrieveState(iStatefull);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        if (isAutoSave()) {
            try {
                List<IStatefull> iStatefulls = InstanceRegistry.getInstance().lookupMultiple(IStatefull.class);
                for (IStatefull iStatefull : iStatefulls) {
                    saveState(iStatefull);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveState(IStatefull<?> statefull) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./carList.bin", false));
        objectOutputStream.writeObject(statefull.getState());
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Override
    public <S> void retrieveState(IStatefull<S> statefull) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./carList.bin"));
        statefull.setState((S) objectInputStream.readObject());
        objectInputStream.close();
    }

    @Override
    public boolean isAutoSave() {
        return Boolean.valueOf(properties.getProperty("autoSave"));
    }
}
