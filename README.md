Dear Developer,

This project is an android application programmed in Kotlin with the  
purpose to emulate NMEA 0183 sentences from the DST800 sensor.

Keep in mind that mock data is being used, but the string format should
be correct.

Identifiers:
- $SDDBT = Depth
- $SDDPT = Depth
- $VWVHW = Speed
- $VWVLW = Distance
- $YXMTW = Water temperature

You can listen to the data with (Java) Sockets and setting up the  
config with:
- Ip address = 10.0.2.2
- port = 5000

Created for The Mobile Company B.V.
Created by Amsterdam University of Applied Sciences IVDM3
