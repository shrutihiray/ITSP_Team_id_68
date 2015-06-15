#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>

// Define baud rate
#define USART_BAUDRATE 9600   
#define F_CPU 10000000
#define BAUD_PRESCALE (((F_CPU / (USART_BAUDRATE * 16UL))) - 1)



void uartinit()
{
    UCSRB |= ((1 << RXEN) | (1 << TXEN) | (1<<RXCIE)); 
    UCSRC |= (1 << URSEL) | (1 << UCSZ0) | (1 << UCSZ1); 
    UBRRL = BAUD_PRESCALE; 
    UBRRH = (BAUD_PRESCALE >> 8); 
	UCSRC |=(1 << UCSZ0) | (1 << UCSZ1); 
	
	
}

unsigned char USART_ReceiveByte(void){
  while(!((UCSRA) &(1<<RXC)));
	return UDR;

  
}
/*void USART_SendByte(unsigned char d){
  while(!((UCSRA) &(1<<UDRE)));
  	// PORTD=0b0100000;
	UDR = d;
	if(UDR == '\0')
{ PORTD=0b0000000;
_delay_ms(300);
}
  
}*/

int main(void){
    char received;
	DDRD=0b1000000;//PIND6 initialised as output pin 
	PORTD=0b0000000;
	
	uartinit();  // Initialise UART
   //sei();         // enable all interrupts

     
	 while(1){
	
	 received=USART_ReceiveByte();
     
	 if(received=='1')
	 {
	 PORTD=0b10000000;
	 _delay_ms(1000);
	 }
	 
	 if(received=='0')
	 {
	 PORTD=0b00000000;
	 _delay_ms(1000);
	 
	 }
	
	 }



 }