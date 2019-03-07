import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mahsa on 3/4/2019.
 */
public class InstanceRegistry {
    private static List<Object> registry = new ArrayList<>();

    public static void register(Object o) {
        registry.add(o);
    }

    public static <O> O lookupSingle(Class<O> objClass) {
        return (O) registry.stream().filter(o -> objClass.isInstance(o)).findFirst().get();
    }

    public static <O> List<O> lookupMultiple(Class<O> objClass) {
        return (List<O>) registry.stream().filter(o -> objClass.isInstance(o)).collect(Collectors.toList());
    }


}
