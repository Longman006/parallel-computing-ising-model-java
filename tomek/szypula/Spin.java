package tomek.szypula;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longman on 16.01.19.
 */
public class Spin {
    private int value = 0;
    private List<Spin> neighbours;
    

    public Spin(int value, List<Spin> neighbours) {
        setValue(value);
        this.neighbours = neighbours;
    }
    public Spin() {
        setValue(1);
        this.neighbours = new ArrayList<>();
    }
    public void addNeighbour(Spin spin){
        if(!spin.equals(this))
        neighbours.add(spin);
    }

    public void setValue(int value) {
        if(value == 1 || value == -1)
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public double getEnergy(double field){
        int energy = 0 ;
        for (Spin spin :
                getNeighbours()) {
            energy -= getValue() * spin.getValue();
        }
        return energy - getValue()*field;
    }

    public List<Spin> getNeighbours() {
        return neighbours;
    }
    public void flipSpin(){
        if (getValue() == 1){
            setValue(-1);
        }
        else {
            setValue(1);
        }
    }

    @Override
    public String toString() {
        return " "+getValue()+" ";
    }
}
