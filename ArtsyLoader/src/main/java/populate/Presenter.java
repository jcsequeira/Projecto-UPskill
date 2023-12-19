package populate;

import com.opencsv.exceptions.CsvException;
import javafx.application.Platform;

import java.io.IOException;

public class Presenter {

    private ImportArtsyView view;
    private YourBackgroundModule backgroundModule;

    public Presenter(ImportArtsyView view, YourBackgroundModule backgroundModule) {
        this.view = view;
        this.backgroundModule = backgroundModule;
    }

    public void startBackgroundTask() throws IOException, InterruptedException, CsvException {
        Thread backgroundThread = new Thread(() -> {
            try {
                backgroundModule.runBackgroundTask();
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        });

        backgroundThread.start();

        Thread checkprogress = new Thread(() -> {
            // Periodically update the progress bar in the view
            while (backgroundThread.isAlive()) {
                double progress = backgroundModule.getProgress();

                // Use Platform.runLater to update UI components from the Application Thread
                Platform.runLater(() -> {
                    view.updateProgressBar(progress);
                });

                try {
                    // Add a delay if needed
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        checkprogress.start();



            view.updateProgressBar(backgroundModule.getProgress());

    }
}
