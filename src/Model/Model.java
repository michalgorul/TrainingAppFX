package Model;

import Exceptions.MyException;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Model {

    private Double height = 0.0;
    private Double weight = 0.0;


    /**
     * This method will check if user's weight and height are in reasonable bounds
     * @throws MyException thrown if height or weight arent proper
     */
    public void checkHeightAndWeight() throws MyException {
        if(this.height < 1.0 || this.height > 320.0 || this.weight < 1.0 || this.weight > 320.0){

            throw new MyException(this.height, this.weight);
        }
    }

    public void setHeightAndWeight(String height, String weight){

        try {

            height = height.replace(",",".");
            weight = weight.replace(",",".");
            this.height = Double.parseDouble(height);
            this.weight = Double.parseDouble(weight);

        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter right value");
            alert.showAndWait();
        }
    }

    /**
     * This method will calculate BMI of user
     * @return user's BMI
     */
    public Double calculateBmi(){
        double bmi;

        if (this.height <= 0 || this.weight <= 0) {
            throw new ArithmeticException("incorrect values");
        }
        else {
            bmi = this.weight/((this.height/100)*(this.height/100));
        }

        return BigDecimal.valueOf(bmi).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
