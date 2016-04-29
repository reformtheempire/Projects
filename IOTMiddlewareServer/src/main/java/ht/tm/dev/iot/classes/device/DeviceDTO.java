package ht.tm.dev.iot.classes.device;

import java.sql.Date;

import ht.tm.dev.iot.enums.IOTDeviceTypeEnum;

public class DeviceDTO {

	private final int deviceID;
	private final String deviceName;
	private final int deviceType;
	private final int owner;
	private final Date createdDate;

	public DeviceDTO(int deviceID, int deviceType, String deviceName, int owner, Date createdDate) {
		this.deviceID = deviceID;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.owner = owner;
		this.createdDate = createdDate;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public int getOwner() {
		return owner;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public String toString() {
		return "DeviceDTO [deviceID=" + deviceID + ", deviceName=" + deviceName + ", deviceType=" + deviceType + ", owner=" + owner + ", createdDate=" + createdDate + "]";
	}

}
