package ecobike.dao.impl;

import ecobike.dao.BikeDao;
import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import ecobike.model.FoldingBike;
import ecobike.model.Speedelec;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

@Repository
public class BikeDaoImpl implements BikeDao {

    @SneakyThrows
    @Override
    public List<String> addAll(String path, List<AbstractBike> unsavedProducts) {
        List<String> result = convertToData(unsavedProducts);
        Files.write(Paths.get(path), result);
        return result;
    }

    @Override
    public List<AbstractBike> getAll(String path, List<AbstractBike> catalog) {
        if (catalog.isEmpty()) {
            for (String data : inputFile(path)) {
                if (data.charAt(0) == 'F') {
                    catalog.add(createFoldingBike(data));
                    continue;
                }
                if (data.charAt(0) == 'E') {
                    catalog.add(createEBike(data));
                    continue;
                }
                if (data.charAt(0) == 'S') {
                    catalog.add(createSpeedElec(data));
                }
            }
        }
        return catalog;
    }

    public List<String> convertToData(List<AbstractBike> list) {
        List<String> result = new ArrayList<>();
        for (AbstractBike bike : list) {
            result.add(bike.toDataFormat());
        }
        return result;
    }

    public FoldingBike createFoldingBike(String data) {
        String[] splitData = data.split("; ");
        int i = 0;
        FoldingBike product = new FoldingBike();
        product.setBrand(Arrays
                .stream(splitData[i++].split(" "))
                .skip(2)
                .collect(Collectors.joining(" ")));
        product.setWheelSize(Integer.parseInt(splitData[i++]));
        product.setGearsCount(Integer.parseInt(splitData[i++]));
        product.setWeight(Integer.parseInt(splitData[i++]));
        product.setLights(Boolean.parseBoolean(splitData[i++]));
        product.setColor(splitData[i++]);
        product.setPrice(new BigDecimal(splitData[i]));
        return product;
    }

    public ElectricBike createEBike(String data) {
        String[] splitData = data.split("; ");
        int i = 0;
        ElectricBike product = new ElectricBike();
        product.setBrand(Arrays
                .stream(splitData[i++].split(" "))
                .skip(1)
                .collect(Collectors.joining(" ")));
        product.setWheelSize(Integer.parseInt(splitData[i++]));
        product.setWeight(Integer.parseInt(splitData[i++]));
        product.setLights(Boolean.parseBoolean(splitData[i++]));
        product.setBatteryCapacity(Integer.parseInt(splitData[i++]));
        product.setColor(splitData[i++]);
        product.setPrice(new BigDecimal(splitData[i]));
        return product;
    }

    public Speedelec createSpeedElec(String data) {
        String[] splitData = data.split("; ");
        int i = 0;
        Speedelec product = new Speedelec();
        product.setBrand(Arrays
                .stream(splitData[i++].split(" "))
                .skip(1)
                .collect(Collectors.joining(" ")));
        product.setMaxSpeed(Integer.parseInt(splitData[i++]));
        product.setWeight(Integer.parseInt(splitData[i++]));
        product.setLights(Boolean.parseBoolean(splitData[i++]));
        product.setBatteryCapacity(Integer.parseInt(splitData[i++]));
        product.setColor(splitData[i++]);
        product.setPrice(new BigDecimal(splitData[i]));
        return product;
    }

    @SneakyThrows
    public List<String> inputFile(String path) {
        return Files.readAllLines(Paths.get(path));
    }
}
