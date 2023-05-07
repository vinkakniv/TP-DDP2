package assignments.assignment3.nota.service;

// This CuciService class implements the LaundryService interface. 
public class CuciService implements LaundryService{

    // A variable to keep track of whether the CuciService is done.
    private boolean done = false;

    // Overrides all of the methods in the LaundryService interface according to its own criteria.

    @Override
    public String doWork() {
        this.done = true;
        return "Sedang mencuci...";
    }
 
    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public long getHarga(int berat) {
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
