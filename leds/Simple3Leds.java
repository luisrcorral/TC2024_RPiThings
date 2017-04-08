import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Simple3Leds {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Adding GPIO signals to light up 3 leds");

        // 1. Acquire GPIO controller
        
        final GpioController gpio = GpioFactory.getInstance();

        // 2. Provision GPIO pin #00, 01 and 02 as output pins.
        //    When wiring the circuit to the Pi, mind the physical correspondance of the .
        
        final GpioPinDigitalOutput ledr = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        final GpioPinDigitalOutput ledy = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
        final GpioPinDigitalOutput ledg = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);

	// 3. Leds up for 2 second
	System.out.println("Leds up");
	ledr.high();
	ledy.high();
	ledg.high();
	Thread.sleep(2000);

	// 4. Leds down for 1 second
	System.out.println("Leds down");
	ledr.low();
	ledy.low();
	ledg.low();
	Thread.sleep(1000);

        //5. Blink the led every 1/2 second for 5 seconds
        System.out.println("Leds blinking");
        ledr.blink(500, 5000);
        ledy.blink(500, 5000);
        ledg.blink(500, 5000);
        Thread.sleep(5000);
        
        System.out.println("Finished");
        gpio.shutdown();

        //Compile: sudo javac -classpath .:classes:/opt/pi4j/lib/'*' -d . Simple3Leds.java
        //Run:     sudo java -classpath .:classes:/opt/pi4j/lib/'*' Simple3Leds

        
    }
}

