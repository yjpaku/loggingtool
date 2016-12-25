package be.ehb.pvdb.logtool;

import java.util.Date;

public class ProcessDetails {
	private int pid;
	private String processName;
	private Date processUsedTime;
	private String user;
	private Date startTime;
	
	public ProcessDetails(int pid, String processName, Date processUsedTime, String user, Date startTime) {
		this.pid = pid;
		this.processName = processName;
		this.processUsedTime = processUsedTime;
		this.user = user;
		this.startTime = startTime;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public Date getProcessUsedTime() {
		return processUsedTime;
	}
	public void setProcessUsedTime(Date processUsedTime) {
		this.processUsedTime = processUsedTime;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Override
	public String toString() {
		return "Process [pid=" + pid + ", processName=" + processName + ", processUsedTime=" + processUsedTime
				+ ", user=" + user + ", startTime=" + startTime + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pid;
		result = prime * result + ((processName == null) ? 0 : processName.hashCode());
		result = prime * result + ((processUsedTime == null) ? 0 : processUsedTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessDetails other = (ProcessDetails) obj;
		if (pid != other.pid)
			return false;
		if (processName == null) {
			if (other.processName != null)
				return false;
		} else if (!processName.equals(other.processName))
			return false;
		if (processUsedTime == null) {
			if (other.processUsedTime != null)
				return false;
		} else if (!processUsedTime.equals(other.processUsedTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}
