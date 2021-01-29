#include <SoftwareSerial.h>
SoftwareSerial Mega(D5,D6);
#include <ArduinoJson.h>
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

/*Declaration*/
float Methane,Ammonia,Latitude,Longitude;
int Visitors,Status;
String l = "1";
uint8_t sen = D1 ; 
String sup = " ";
String water="yes";
int flag = 1;
String com = "NICE TOILETS";
String path = "/location";
String device_no = "/4"; // device no
int device = 4; // device no
double lat = 18.9853;
double lo =  72.833;
String condi = "N/A";
String img1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT_w-OoJDA4DxRdHpvhYkyREFSXTNqj3EyCWxRhpcA7VLPUQ2x0";
String  img2  = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT_w-OoJDA4DxRdHpvhYkyREFSXTNqj3EyCWxRhpcA7VLPUQ2x0";
//Define FirebaseESP8266 data object

#define FIREBASE_HOST "deepblue-997b8.firebaseio.com"
#define FIREBASE_AUTH "wKeCLSLXyHMmtX9SwyujzoG1V0OI0pBdvM9q5xSS"
#define WIFI_SSID "Android"
#define WIFI_PASSWORD "@bCdeFGh"



void setup() {
  // Initialize Serial port
  Serial.begin(9600);
  Mega.begin(115200);
  //Connecting TO WiFi 
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
 
}



void loop() {
  StaticJsonBuffer<1000> jsonBufferMega;
  JsonObject& root = jsonBufferMega.parseObject(Mega);
  if (root == JsonObject::invalid())
    {Serial.println("No object");}
  else
  {root.prettyPrintTo(Serial);
       Methane   = root["Methane"]   ;
       Ammonia   = root["Ammonia"]   ;
       Latitude  = root["Latitude"]  ;
       Longitude = root["Longitude"] ;
       Visitors  = root["Visitors"]  ;
       Status    = root["Status"]    ;
   databaseUpdate();    
  }

  
}



// defining Functions
void databaseUpdate ()
{
  if(Firebase.failed())
  {Serial.println("not connected");
   Serial.println(Firebase.error());
  if(WiFi.status() != WL_CONNECTED)
    {WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");}
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
   Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
   delay(1000);}
else
  {
  Serial.println("printed");
  if(flag == 1){   
  flag = 0;
  for(int i=1;i<=5;i++){
   
   Firebase.setString(path + device_no + "/cleaners/" + i +"/ID"  , l);
   Firebase.setString(path + device_no + "/reviews/" + i  , com);    
  }
  Firebase.setInt(path + device_no + "/count"  , 0);
  Firebase.setString(path + device_no + "/supervisor"  , sup);
  Firebase.setString(path + device_no + "/water"  ,water);
  Firebase.setInt(path + device_no + "/device"  , device);
  Firebase.setFloat(path + device_no + "/parameters/co2", Ammonia);
  Firebase.setFloat(path + device_no + "/parameters/methane",Methane);
  Firebase.setFloat( path + device_no + "/lat",  lat);
  Firebase.setFloat(path + device_no + "/long", lo);
  Firebase.setString(path + device_no + "/condition",condi);
  Firebase.setString(path + device_no + "/image1" , img1);
  Firebase.setString(path + device_no + "/image2" ,img2);
  //Firebase.setString("status",statusCheck());
  
  
  delay(1000);
  }
  
  }
}  
