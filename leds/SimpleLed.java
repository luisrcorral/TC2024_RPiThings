import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class SimpleLed {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Adding GPIO signal to light up a led");

        // 1. Acquire GPIO controller
        
        final GpioController gpio = GpioFactory.getInstance();

        // 2. Provision GPIO pin #00 as an output pin. GPIO_00 is the logical pin.
        //    When wiring the circuit to the Pi, I need to use the physical pin 11.
        
        final GpioPinDigitalOutput led0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);


	// 3. Led on for 3 seconds
	System.out.println("Led on");
	led0.high();
	Thread.sleep(3000);
	
	// 4. Led off for 2 seconds
	System.out.println("Led off");
	led0.low();
	Thread.sleep(2000);
	
        //5. Blink the led every 1/2 second for 10 seconds
        System.out.println("Led blinking");
        led0.blink(500, 10000);
        Thread.sleep(10000);
        
        
        //6. This method terminates the Pi4J GPIO controller
        System.out.println("Finished");
        gpio.shutdown();
        
        //Compile: sudo javac -classpath .:classes:/opt/pi4j/lib/'*' -d . SimpleLed.java
        //Run:     sudo java -classpath .:classes:/opt/pi4j/lib/'*' SimpleLed

       
        
    }
}

