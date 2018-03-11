// CSD feb 2015 Juansa Sendra

public class Pool3 extends Pool{ //max capacity
    int instructores = 0, ninyos = 0, capacidad, ninyosInstructor;
    public void init(int ki, int cap) {
        this.capacidad = cap;
        this.ninyosInstructor = ki;
    }
    public synchronized void kidSwims() throws InterruptedException {
        while((ninyos + 1 > ninyosInstructor * instructores) || instructores < 1 || ninyos + instructores == capacidad) {
            log.waitingToSwim();
            wait();
        }
        log.swimming();
        ninyos++;
    }
    public synchronized void kidRests() {
        log.resting();
        this.ninyos--;
        notifyAll();
    }
    public synchronized void instructorSwims() throws InterruptedException {
        while(ninyos + instructores == capacidad) {
            log.waitingToSwim();
            wait();
        }
        instructores++;
        log.swimming();
        notifyAll();
    }
    public synchronized void instructorRests() throws InterruptedException {
        while((ninyos > (ninyosInstructor * (instructores - 1)))){
            log.waitingToRest();
            wait();
        }
        log.resting();
        instructores--;
    }   
}
