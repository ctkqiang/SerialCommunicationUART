/**
 * Copyright 2020 © John Melody Melissa
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @Author : John Melody Melissa
 * @Copyright: John Melody Melissa  © Copyright 2020
 * @INPIREDBYGF : Sin Dee <3
 * @Class: ArduinoSerial.ino
 */
void setup()
{  
 // Open serial communications and wait for port to open:

//  Serial.begin(1200);
//  Serial.begin(2400);
//  Serial.begin(4800);
  Serial.begin(9600);
//  Serial.begin(14400);
//  Serial.begin(19200);
//  Serial.begin(38400);
//  Serial.begin(57600);
//  Serial.begin(115200); 
//   while (!Serial) {
//    ; // wait for serial port to connect. Needed for Leonardo only
//  }
}

void loop()
{
  while(Serial.available() > 0) {
    Serial.write(Serial.read());
  }
}
