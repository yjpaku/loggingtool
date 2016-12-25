package be.ehb.pvdb.logtool;

public class Message {

	private String subject;
	private Long time;
	private static Long seqNr =0L;
	private String content;
	
	public Message(String subject, String content) {
		this.subject = subject;
		this.time=System.currentTimeMillis();
		seqNr += 1L;
		this.content = content;
	}
	public Message(String subject, Long time, Long seqNr, String content) {
		this.subject = subject;
		this.time = time;
		Message.seqNr = seqNr;
		this.content = content;
	}
	
	public String getSubject() {
		return subject;
	}

	public long getTime() {
		//System.out.println("Message>getTime()");
		return time;
	}

	public static Long getSeqNr() {
		//System.out.println("Message>Long getSeqNr()");
		return seqNr;
	}

	public String getContent() {
		//System.out.println("Message>getContent()");
		return content;
	}

	@Override
	public String toString() {
		return "Message [subject=" + subject + ", time=" + time + ", seqNr=" + Message.seqNr +", content=" + content + "]";
	}
	

}