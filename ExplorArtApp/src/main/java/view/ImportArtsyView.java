package view;

import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;

public class ImportArtsyView extends Parent {

        private ProgressBar progressBar;

        public ImportArtsyView() {
            // Initialize your view components, including the progress bar
            progressBar = new ProgressBar();
        }

        public void updateProgressBar(double progress) {
            // Update the progress bar based on the value obtained from the Model
            progressBar.setProgress(progress);
        }

        // Other methods and UI setup
    }


