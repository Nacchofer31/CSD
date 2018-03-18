// CSD Mar 2013 Juansa Sendra

public class LimitedPhilo extends Philo {
    public LimitedPhilo(int id, int cycles, int delay, Table table) {super(id, cycles, delay, table);}
    
    public void run() {
    	table.begin(id);
    	for (int i = 0; i < cycles; i++) {
    		try {
				table.enter(id);
				table.takeR(id);
				randomDelay();
				table.takeL(id);
				randomDelay();
				table.dropR(id);
				table.dropL(id);
				table.exit(id);
				randomDelay();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	table.end(id);
    }
}