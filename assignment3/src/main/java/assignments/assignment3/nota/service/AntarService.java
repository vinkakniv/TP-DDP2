package assignments.assignment3.nota.service;

// This AntarService class implements the LaundryService interface. 
public class AntarService implements LaundryService{

    // A variable to keep track of whether the AntarService is done.
    private boolean done = false;

    // Overrides all of the methods in the LaundryService interface according to its own criteria.

    @Override
    public String doWork() {
        this.done = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public long getHarga(int berat) {
        long harga = 500 * berat;
        if (harga < 2000) {
            harga = 2000;
        }
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
