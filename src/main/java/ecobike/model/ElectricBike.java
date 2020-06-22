package ecobike.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ElectricBike extends AbstractBike {
    private String type = "E-BIKE";

    @Override
    public String toDataFormat() {
        return getType() + " " + getBrand() + "; "
                + getMaxSpeed() + "; "
                + getWeight() + "; "
                + isLights() + "; "
                + getBatteryCapacity() + "; "
                + getColor() + "; "
                + getPrice();
    }

    @Override
    public String toProductLook() {
        return getType() + " " + getBrand()
                + " with " + getBatteryCapacity()
                + " mAh battery and" + (isLights() ? " " : " no ") + "head/tail light." + "\n"
                + "Price: " + getPrice() + " euros.";
    }
}
