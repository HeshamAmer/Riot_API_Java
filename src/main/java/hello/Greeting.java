package hello;

public class Greeting {

    private final long id;
    private final String content;
    private final int Level;

    public Greeting(long id, String content, int Level) {
        this.id = id;
        this.content = content;
        this.Level=Level;
    }

    public long getId() {
        return id;
    }
    public int getLevel() {
    	return Level;
    }
    public String getContent() {
        return content;
    }
}
