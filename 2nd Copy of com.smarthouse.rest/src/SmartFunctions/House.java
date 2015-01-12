package SmartFunctions;

public class House {

	// Make sure the right ones are indoors and outdoors
	
	
	//Add another dimension with eg- {{"Stove","7"},} etc
	private String[] deviceNameIN = {"Stove", "Water Leakage","Electricity Cut","Light Inside","Auto Light Inside","Heater Inside","Heater Roof","Fan","Auto Air Conditioning","Temperature Inside","Temperature Inside Roof","Electricity Consumption"};
	private String[] deviceNameOUT = {"Fire Alarm","Window Open","Door Open","Light Outside","Auto Light Outside","Temperature Outside","Security Alarm"};
	private int outsideTotal = deviceNameOUT.length;
	private int insideTotal = deviceNameIN.length;
	public Device[] inside = new Device[insideTotal];
	public Device[] outside = new Device[outsideTotal];
	
	public int houseID = 0;
	
	

	public House(){
		
	//Must update to include virtual devices
	int i = 0;
	while(i<insideTotal){
		inside[i] = new Device();
		inside[i].setName(deviceNameIN[i]);
		i++;
	
	}
	i = 0;
	while(i<outsideTotal){
		outside[i] = new Device();
		outside[i].setName(deviceNameOUT[i]);
		i++;
	}
	
	
	}
	
//	private boolean fireAlarm;
//	private boolean stove;
//	private boolean waterLeakage;
//	private boolean window;
//	private boolean door;
//	private boolean electricity;
//	private boolean lightIn;
//	private boolean autoLightIn;
//	private boolean lightOut;
//	private boolean autoLightOut;
//	private boolean heaterIn;
//	private boolean heaterRoof;
//	private int fan = 0;
//	private int autoAir = 0;
//	private boolean tempOut;
//	private boolean tempIn;
//	private boolean tempRoof;
//	private boolean elCon;
//	private boolean secAlarm;
	
	public int getHouseID() {
		return houseID;
	}

	public void setHouseID(int houseID) {
		this.houseID = houseID;
	}

	public Device[] getInside() {
		return inside;
	}

	public void setInside(Device[] inside) {
		this.inside = inside;
	}

	public Device[] getOutside() {
		return outside;
	}

	public void setOutside(Device[] outside) {
		this.outside = outside;
	}

	public class Device {
		public String name = null;
		public int id = 0;
		public boolean state;
		public int value = 0;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public boolean getState() {
			return state;
		}
		public void setState(boolean state) {
			this.state = state;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		
		

	}
}
