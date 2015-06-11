#include <SoftwareSerial.h>
 //declaration of receiving pin and transmitting pin of serial data of bluetooth
SoftwareSerial bluetooth(10, 11); // RX, TX
int buzzer = 8;// defined pin where buzzer is attached
int BluetoothData;

void setup() {
  // put your setup code here, to run once:
bluetooth.begin(9600);// 9600 is baud rate
  pinMode(buzzer, OUTPUT);

}

void loop() {
  // put your main code here, to run repeatedly:

  if (bluetooth.available())// if data is available 
  {
    BluetoothData = bluetooth.read();
    

    if (BluetoothData == '1')
    {
      digitalWrite(buzzer, 1);// if received data is '1' make buzzer pin high
      
      Serial.println("BUZZER ON");
    }

    if (BluetoothData == '0')
    {
      digitalWrite(buzzer, 0);// if received data is '0' make buzzer pin low
      
      Serial.println("BUZZER OFF");
    }



  }
  delay(100);
}
