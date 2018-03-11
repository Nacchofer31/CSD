// CSD feb 2015 Juansa Sendra

public class Pool1 extends Pool {   //no kids alone
    int instructores = 0, ninyos = 0;
    public void init(int ki, int cap) {
        
    }
    public synchronized void kidSwims() throws InterruptedException {
        while(instructores<1) {
            log.waitingToSwim();
            wait();
        }
        ninyos++;
        log.swimming();
    }
    public synchronized void kidRests() {
        log.resting();
        this.ninyos--;
        notifyAll();
    }
    public synchronized void instructorSwims() {
        instructores++;
        log.swimming();
        notifyAll();
    }
    public synchronized void instructorRests() throws InterruptedException {
        while(instructores <= 1 && ninyos > 0){
            log.waitingToRest();
            wait();
        }
        log.resting();
        instructores--;
    }
}
