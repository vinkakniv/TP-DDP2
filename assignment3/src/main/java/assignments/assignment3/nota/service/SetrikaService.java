package assignments.assignment3.nota.service;

// This SetrikaService class implements the LaundryService interface. 

public class SetrikaService implements LaundryService{

    // A variable to keep track of whether the SetrikaService is done.
    private boolean done = false;

    // Overrides all of the methods in the LaundryService interface according to its own criteria.

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
