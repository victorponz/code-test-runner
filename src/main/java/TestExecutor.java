import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestExecutor {
    private String testClassName; //The test source code including path
    private String testPath;
    private StringBuilder testError; //If error in compilation holds the error message
    private boolean succees;
    public TestExecutor(String testClassName, String testPath){
        this.testClassName = testClassName;
        this.testPath =  "./" + testPath;
        this.testError = new StringBuilder();
    }

    public StringBuilder getTestError() {
        return testError;
    }
    public boolean test() {
        System.out.println("Classpath: " + System.getProperty("java.class.path"));

        try {
            // Get the current working directory
            Path currentDir = Paths.get("").toAbsolutePath();

            // Create a URL relative to the current working directory
            Path relativePath = currentDir.resolve(this.testPath);
            URL[] classLoaderUrls = new URL[] { relativePath.toUri().toURL() };

            // Create a new class loader with the directory
            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

            // Load the test class using the class loader
            Class<?> testClass = urlClassLoader.loadClass(this.testClassName);

            // Run the test class
            JUnitCore junit = new JUnitCore();
            Result result = junit.run(testClass);
            // Process the test results
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
                this.testError.append(failure.toString());
            }
            if (!result.getFailures().isEmpty())
                return false;
            // Print overall test result
            System.out.println("All tests successful: " + result.wasSuccessful());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
