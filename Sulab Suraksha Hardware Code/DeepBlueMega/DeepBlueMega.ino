//Including Files
#include  <SoftwareSerial.h>
#include  <ArduinoJson.h>
#include <TinyGPS++.h>



//Defining Pin Numbers
#define   Methane   A15
#define   Ammonia   A14
#define   PIR       15
#define   Echo      43
#define   Trig      41
#define   GPS_TX    17
#define   GPS_RX    16



/*
#define   RED       
#define   GREEN     
#define   BLUE       
*/
// declaration
float Latitude,Longitude;
float permitted_distance=10;
int flag =0;
int people = 0;


/*Setup Files*/
TinyGPSPlus    GPS;
SoftwareSerial Node(7,5);
SoftwareSerial GPS_Serial(GPS_RX,GPS_TX);

void setup() {
  // put your setup code here, to run once:
    Serial.begin(9600);
    GPS_Serial.begin(9600);
    Node.begin(115200);
    pinMode(Methane,INPUT);
    pinMode(Ammonia,INPUT);
    pinMode(Echo,INPUT);
    pinMode(PIR,INPUT_PULLUP);
    pinMode(Trig,OUTPUT);
    attachInterrupt(digitalPinToInterrupt(PIR),humanCount,FALLING);
    Serial.print("PREHEAT TIME");
    delay(20000);
    Serial.print("Ready To USE");

}
//Functions 
float convertFour(float number)
{
  int x;
  x = (int)(number*10000);
  number = x/10000.0; 
  return(number);
}
float convertTwo (float number)
{
  int x;
  x = (int)(number*100);
  number = x/100.0; 
  return(number);
  
}
void gpsLocation()
{
  while(GPS_Serial.available()>0)
  {  GPS.encode(GPS_Serial.read());
     if (GPS.location.isUpdated())
     {  double Raw_latitude,Raw_longitude;
        Raw_latitude  = GPS.location.lat();
        Raw_longitude = GPS.location.lng();
        Serial.println("GPS");
        Longitude =convertFour(Raw_latitude);
        Latitude  =convertFour(Raw_longitude);
     } 
  }
}
float methaneReading()
{
  int Reading;
  float Percentage;
  Reading = analogRead(Methane);
  Percentage = ((float)Reading/1024.0)*100;
  Serial.println(Percentage);
  return(convertTwo(Percentage));  
}
float ammoniaReading()
{
  int Reading;
  float Percentage;
  Reading = analogRead(Ammonia);
  Percentage = ((float)Reading/1024.0)*100;
  Serial.println(Percentage);
  return(convertTwo(Percentage));  
}
void humanCount()
{ double current_distance;
  while(1)
  { current_distance = distance();
    if(current_distance < permitted_distance)
    { flag=1;
      break;
    }
  }
  while(1)
  { current_distance = distance();
    if(current_distance > (permitted_distance-5))
    {
      break;
    }
  }  
  if(flag)
  { flag=0;
    people++; 
  }
}
float distance()
{ int Time_taken;
  float Calculated_distance;
  digitalWrite(Trig,HIGH);
  delayMicroseconds(10);
  digitalWrite(Trig,LOW);
  delayMicroseconds(2);
  Time_taken=pulseIn(Echo,HIGH);
  Calculated_distance = Time_taken*340.0/20000.0;
  return(Calculated_distance); 
}

//Execution Code
void loop() {
  // put your main code here, to run repeatedly:
  StaticJsonBuffer<1000> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();
  //gpsLocation();
  float m,a;
  m= methaneReading();
  a= ammoniaReading();
  root["Methane"]   = m;
  root["Ammonia"]   = a;
  root["Latitude"]  = Latitude;
  root["Longitude"] = Longitude;
  root["Visitors"]  = people;
  root["Status"]    = flag;


  root.printTo(Node);

}
