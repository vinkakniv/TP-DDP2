package assignments.assignment3.nota.service;

public interface LaundryService {
    /**
     * Returns a String indicating that the service is being worked on.
     * If called at least once, the isDone method will return true.
     *
     * @return A String indicating that the service is being worked on.
     */
    String doWork();

    /**
     * Returns true if the doWork() method has been called at least once, false otherwise.
     *
     * @return A boolean indicating whether the service has been completed or not.
     */
    boolean isDone();

    /**
     * Calculates the price of the laundry based on the weight passed as an argument.
     *
     * @param berat -> the weight of the laundry.
     * @return The calculated price based on the weight in long to prevent overflow.
     */
    long getHarga(int berat);

    /**
     *  Returns the name of the service
     *
     * @return service name.
     */
    String getServiceName();
}