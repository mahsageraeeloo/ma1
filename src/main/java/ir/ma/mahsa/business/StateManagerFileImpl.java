package ir.ma.mahsa.business;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StateManagerFileImpl implements IStateManager, IHasLifeCycle {
    private boolean isSaved = false;
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

    public boolean isSaved() {
        return isSaved;
    }


    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    @Override
    public void saveState(IStatefull<?> statefull) throws IOException {
//        if(!this.isSaved()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./carList.bin", false));
            objectOutputStream.writeObject(statefull.getState());
            objectOutputStream.flush();
            objectOutputStream.close();
            this.setSaved(true);
//        }
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

//    public void persistCarList(CarManager cm) throws IOException {
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./carList.bin", false));
//        List<Car> temp = cm.getCarList();
//        objectOutputStream.writeInt(temp.size());
//        for (Car car : temp) {
//            objectOutputStream.writeObject(car);
//        }
//        objectOutputStream.flush();
//        objectOutputStream.close();
//    }
//
//    public void loadCarList(CarManager cm) throws IOException, ClassNotFoundException, AddCarException {
//        HashMap<Integer, Car> carList = new HashMap<>();
//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./carList.bin"));
//        int size = objectInputStream.readInt();
//        int max = 0;
//
//        for (int i = 0; i < size; i++) {
//            Car c = (Car) objectInputStream.readObject();
//            int id = c.getId();
//            if (id > max) {
//                max = c.getId();
//            }
//            carList.put(id, c);
//        }
//        cm.setLastCarId(max);
//        cm.setCarList(carList);
//        objectInputStream.close();
//    }

//    public static void main(String[] args) {
//        List<Integer> integers = new ArrayList<>();
//        List anotherPointer = integers;
//        Car c = new Car();
//        anotherPointer.add(c);
//        System.out.println("anotherPointer = " + anotherPointer);
//    }
}
