public class Heater {
    private int temperature;
    private int min;
    private int max;
    private int increment;
    
    public Heater(int min, int max) {
        this.min = min;
        this.max = max;
        this.increment = 5;
    }

    public void warmer() {
        if(this.increment > this.max) {
            System.out.println("warmer foi chamado com valor de incremento superior ao máximo: " + increment);
            return;
        }
        this.setTemperature(this.getTemperature() + this.increment);
    }

    public void cooler() {
        if(this.increment > this.min) {
            System.out.println("cooler foi chamado com valor de incremento superior ao mínimo: " + increment);
            return;
        }
        this.setTemperature(this.getTemperature() - this.increment);
    }

    public int getTemperature() {
        return this.temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setIncrement(int increment) {
        if(increment < 0) {
            System.out.println("setIncrement foi chamado com um valor negativo: " + increment);
            return;
        }
        this.increment = increment;
    }
}
