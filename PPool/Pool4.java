// CSD feb 2013 Juansa Sendra

public class Pool4 extends Pool { //kids cannot enter if there are instructors waiting to exit
    int instructores = 0, ninyos = 0, capacidad, ninyosInstructor, esperando = 0;
    public void init(int ki, int cap) {
        this.capacidad = cap;
        this.ninyosInstructor = ki;
    }
    public synchronized void kidSwims() throws InterruptedException {
        while((ninyos + 1 > ninyosInstructor * instructores) || instructores < 1 || ninyos + instructores == capacidad || esperando != 0) {
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
        esperando ++;
        while((ninyos > (ninyosInstructor * (instructores - 1)))){
            log.waitingToRest();
            wait();
        }
        esperando --;
        instructores--;
        notifyAll();
        log.resting();
    }
}
