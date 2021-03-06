package ecobike.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Speedelec extends AbstractBike {
    private String type = "SPEEDELEC";

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
