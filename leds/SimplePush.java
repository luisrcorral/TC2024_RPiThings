import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class SimplePush {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Adding GPIO signal to enable a switch");

        // 1. Acquire GPIO controller
        
        final GpioController gpio = GpioFactory.getInstance();

        // 2. Provision GPIO pin #00 as an input pin. GPIO_00 is the logical pin.
        //    When wiring the circuit to the Pi, I need to use the physical pin 11.
        
        final GpioPinDigitalInput pin0 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN);


	// 3. Protect pin with shutdown state
	pin0.setShutdownOptions(true);
	
	// 4. Create the event listener to detect change in the input state
	pin0.addListener(new GpioPinListenerDigital(){
	       @Override
	       public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event){
	       System.out.println("Change in V detected in pin " + event.getPin() + " to " + event.getState() + ".");
	       
	       String action = event.getState() + "";
	       action = action.trim();
                
                if (action.equals("HIGH")){
                 // Do whatever you want in high state
                   
	        }
	     
	     
	     }
	});
	
        //5. Loop so the user can test it
        while(true){
           Thread.sleep(100);
        }
        
        
        
        //6. This method terminates the Pi4J GPIO controller
        //System.out.println("Finished");
        //gpio.shutdown();
        
        //Compile: sudo javac -classpath .:classes:/opt/pi4j/lib/'*' -d . SimplePush.java
        //Run:     sudo java -classpath .:classes:/opt/pi4j/lib/'*' SimplePush

       
        
    }
}

