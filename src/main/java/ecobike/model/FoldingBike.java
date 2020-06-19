package ecobike.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
@EqualsAndHashCode(callSuper = false)
@Data
public class FoldingBike extends AbstractBike {
    private String type = "FOLDING BIKE";

    @Override
    public String toDataFormat() {
        return getType() + " " + getBrand() + "; "
                + getWheelSize() + "; "
                + getGearsQuantity() + "; "
                + getWeight() + "; "
                + isLights() + "; "
                + getColor() + "; "
                + getPrice();
    }

    @Override
    public String toProductLook() {
        return getType() + " " + getBrand()
                + " with " + getGearsQuantity()
                + " gear(s) and" + (isLights() ? " " : " no ") + "head/tail light." + "\n"
                + "Price: " + getPrice() + " euros.";
    }
}
