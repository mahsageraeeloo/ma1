package ir.ma.mahsa.business;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StateManagerFileImpl implements IStateManager {
    private boolean isSaved = false;
    public StateManagerFileImpl() {
        InstanceRegistry.register(this);
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    @Override
    public void saveState(IStatefull<?> statefull) throws IOException {
        if(!this.isSaved()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./carList.bin", false));
            objectOutputStream.writeObject(statefull.getState());
            objectOutputStream.flush();
            objectOutputStream.close();
            this.setSaved(true);
        }
    }

    @Override
    public <S> void retrieveState(IStatefull<S> statefull) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./carList.bin"));
        statefull.setState((S) objectInputStream.readObject());
        objectInputStream.close();
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
