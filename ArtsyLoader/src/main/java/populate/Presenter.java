package populate;

import com.opencsv.exceptions.CsvException;
import javafx.application.Platform;

import java.io.IOException;
/**
 * The Presenter class acts as a mediator between the {@link ImportArtsyView} and {@link PopulateModel},
 * managing the interaction and updating the user interface based on the background task's progress.
 */
public class Presenter {

    /** The associated ImportArtsyView instance. */
    private final ImportArtsyView view;

    /** The background module responsible for executing the import task. */
    private final PopulateModel backgroundModule;

    /**
     * Initializes the Presenter with the specified {@link ImportArtsyView} and {@link PopulateModel}.
     *
     * @param view            The associated ImportArtsyView instance.
     * @param backgroundModule The background module responsible for executing the import task.
     */
    public Presenter(ImportArtsyView view, PopulateModel backgroundModule) {
        this.view = view;
        this.backgroundModule = backgroundModule;
    }

    /**
     * Initiates the background task and periodically updates the progress bar in the view.
     *
     * @throws IOException   If an I/O error occurs during the background task.
     * @throws InterruptedException If the background task is interrupted.
     * @throws CsvException If an exception related to CSV processing occurs.
     */
    public void startBackgroundTask() throws IOException, InterruptedException, CsvException {
        Thread backgroundThread = new Thread(() -> {
            try {
                backgroundModule.runBackgroundTask();
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        });

        backgroundThread.start();

        Thread checkProgress = new Thread(() -> {
            // Periodically update the progress bar in the view
            while (backgroundThread.isAlive()) {
                double progress = backgroundModule.getProgress();

                // Use Platform.runLater to update UI components from the Application Thread
                Platform.runLater(() -> view.updateProgressBar(progress));

                try {
                    // Add a delay if needed
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        checkProgress.start();

        // Initial update of the progress bar
        view.updateProgressBar(backgroundModule.getProgress());
    }
}
