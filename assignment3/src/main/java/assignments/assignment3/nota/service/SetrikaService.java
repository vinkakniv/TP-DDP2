package assignments.assignment3.nota.service;

// This class implements the LaundryService interface
public class SetrikaService implements LaundryService{
    private boolean done = false;

    @Override
    public String doWork() {
        this.done = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public long getHarga(int berat) {
        return 1000 * berat;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
