// Distributed with a free-will license.
// Use it any way you want, profit or free, provided it fits in the licenses of its associated works.
// ADC121C021
// This code is designed to work with the ADC121C021_I2CADC I2C Mini Module available from ControlEverything.com.
// https://www.controleverything.com/content/Analog-Digital-Converters?sku=ADC121C021_I2CADC#tabs-0-product_tabset-2

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class ADC121C021
{
	public static void main(String args[]) throws Exception
	{
		// Create I2CBus
		I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
		// Get I2C device, ADC121C021 I2C address is 0x50(80)
		I2CDevice device = bus.getDevice(0x50);

		// Select configuration register
		// Automatic conversion mode enable
		device.write(0x02, (byte)0x20);
		Thread.sleep(500);

		// Read 2 bytes of data from adress 0x00(0)
		// raw_adc msb, raw_adc lsb
		byte[] data = new byte[2];
		device.read(0x00, data, 0, 2);

		// Convert the data to 12-bits
		int raw_adc = ((data[0] & 0x0F) * 256 + (data[1] & 0xFF));

		// Output to screen
		System.out.printf("Digital Value of Analog Input : %d %n", raw_adc);
	}
}
