// CSD Mar 2013 Juansa Sendra

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    private int count;
	public LimitedTable(StateManager state) {
		super(state);
		count = 0;
	}
	
	public synchronized void enter(int id) {
		while(count >= 4) {
			state.wenter(id);
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		count++;
		state.enter(id);
	}
	
	public synchronized void exit(int id) {
		count--;
		if (count == 4) notifyAll();
		state.exit(id);
	}
}
