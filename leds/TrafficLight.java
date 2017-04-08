import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class TrafficLight {

    public static void main(String[] args) throws InterruptedException {

        int x;
        
        System.out.println("Starting traffic light");

        // 1. Acquire GPIO controller
        
        final GpioController gpio = GpioFactory.getInstance();

        // 2. Provision GPIO pin #00, 01 and 02 as output pins.
        //    When wiring the circuit to the Pi, mind the physical correspondance of the layout.
        
        final GpioPinDigitalOutput ledr = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        final GpioPinDigitalOutput ledy = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
        final GpioPinDigitalOutput ledg = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);

	
	// 3. Traffic light algorithm
	ledr.low();
	ledy.low();
	ledg.low();

        for (x=0; x<5; x++) {                               // Loop

          System.out.println ("Red light on... stop.\n");
          ledr.high();                                      //Switches on red light
          Thread.sleep(5000);
          ledr.low();                                       //Turns off red light

          System.out.println ("Green light on... go.\n");
          ledg.high();                                      //Switches on green light
          Thread.sleep(6000);
          ledg.low();                                       //Turns off green light

          System.out.println ("Attention!\n");
          ledg.blink(500, 4000);
          Thread.sleep(4000);                               //Blinks green light
          ledg.low();
   
          System.out.println ("Yellow light on... caution.\n");
          ledy.high();                                      //Switches on green light
          Thread.sleep(2000);
          ledy.low();
          
 
        }
	
        
        System.out.println("Finished");
        gpio.shutdown();

        //Compile: sudo javac -classpath .:classes:/opt/pi4j/lib/'*' -d . TrafficLight.java
        //Run:     sudo java -classpath .:classes:/opt/pi4j/lib/'*' TrafficLight

        
    }
}

