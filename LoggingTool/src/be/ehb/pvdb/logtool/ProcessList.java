package be.ehb.pvdb.logtool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;

public class ProcessList implements Runnable{

	int intervalSleep;
	boolean doRun;
	private HashMap<Integer ,ProcessDetails> processMap = new HashMap<Integer, ProcessDetails>();
	List<ProcessInfo> processesList;
	
	MessageData messageData;
	Websocket websocket;
	FileSave fileSave; 

	public ProcessList(int intervalSleep){
		this.intervalSleep = intervalSleep;
	}

	@Override
	public void run() {
		messageData = new MessageData();
		websocket = new Websocket(messageData);
		fileSave = new FileSave(messageData);
		//System.out.println("ProcessList>run>--start--");
		doRun = true;
		buildMap(true);
		while (doRun) {
			if (Thread.interrupted()){
				doRun = false;
			}
			try {
				buildMap(false);
				Thread.sleep(intervalSleep);
			} catch (InterruptedException ex) {
				doRun = false;
			}
		}
		//System.out.println("ProcessList>run>--stop--");
	}

	private void buildMap(boolean firstRun) {
		//System.out.println("ProcessList>buildMap>--start--");
		processesList = JProcesses.getProcessList();
		for (final ProcessInfo processInfo : processesList) {
			DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			try {
				if (firstRun) {
					sendProcess(processInfo,1);
				} else {
					ProcessDetails pid = processMap.get(Integer.parseInt(processInfo.getPid()));
					if (pid == null) {
						sendProcess(processInfo,2);
					} else {
//						System.out.println(pid.toString());
						Date processInfodUsedTime = dateFormat.parse(processInfo.getTime()); 
						if (!pid.getProcessUsedTime().equals(processInfodUsedTime)) {
							sendProcess(processInfo,3);
						}
					}
				}
				processMap.put(Integer.parseInt(processInfo.getPid()), new ProcessDetails(Integer.parseInt(processInfo.getPid()),
					processInfo.getName(),
					dateFormat.parse(processInfo.getTime()),
					processInfo.getUser(),
					dateFormat.parse(processInfo.getStartTime())));
			} catch (NumberFormatException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//System.out.println("ProcessList>buildMap>--stop--");
	}
	
	private void sendProcess(ProcessInfo processInfo,int crud) {
		String content = processInfo.getPid() + ";" + processInfo.getName() + ";" + processInfo.getTime() + ";" + processInfo.getUser() + ";" + processInfo.getStartTime();	
		messageData.createMsg(new Message("process", content));
	}
}
