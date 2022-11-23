# drones
	There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. 
	Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication,
	the drone has the potential to leapfrog traditional transportation infrastructure.

# To Run without Docker

	> mvn clean install
	> java -jar target/drones-0.0.1.jar

# To Run with Docker
	> mvn clean install
	> docker build -t drones .
	> docker run -d -p 8282:8282 drones

	> docker stop <image-name>

# APIs Examples :

# Get all registered drones API
	> GET - http://localhost:8282/dispatchController/getAllDrones

# Register a drone  API
	> POST - http://localhost:8282/dispatchController/registerDrone
	{
	   "serialNumber":"1234456",
	   "model":"Middleweight",
	   "weightLimit":"500",
	   "batteryCapacity":"28",
	   "state":"IDLE"
	}

# Load Medications API
	>POST - http://localhost:8282/dispatchController/loadMedications
		{
			"dronSerialNumber": "1234456",
			"medications":[
				{
					"name": "medica1",
					"weight": "250",
					"code": "MED_1"
				}
			]
		}

# Checking loaded medication items for a given drone;
	>GET - http://localhost:8282/dispatchController/checkLoadMedications?droneSerialNo=123445

# Check drone battery API
	> GET - http://localhost:8282/dispatchController/checkDroneBattery?droneSerialNo=123445

# Check Available Droned
	>GET - http://localhost:8282/dispatchController/checkAvailableDrones
	
# List audit API
	> GET - http://localhost:8282/dispatchController/listAudit
