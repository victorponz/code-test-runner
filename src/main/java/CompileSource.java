import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.Diagnostic;

public class CompileSource {
    private List<String> sourceFiles; //The source code including path
    private String compilationError; //If error in compilation holds the error message
    private boolean succees;
    public CompileSource(List<String> sourceFiles){
        this.sourceFiles = sourceFiles;
        this.compilationError = "";
        this.succees = true;
    }

    public void setSourceFile(List<String> sourceFiles) {
        this.sourceFiles = sourceFiles;
    }

    public boolean isSucceed() {
        return succees;
    }

    public String getCompilationError() {
        return compilationError;
    }

    public void compile() {

        // Get the Java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // Set up a diagnostic collector to collect compilation diagnostics
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        // Get a standard file manager
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        // Get the compilation units (i.e., the source files to compile)
        //String a = Arrays.asList(new File(this.sourceFile);
        List<File> sourcesFiles = new ArrayList<>();
        for(String f: this.sourceFiles){
            sourcesFiles.add(new File(f));
        }
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourcesFiles);

        // Create a compilation task
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);

        // Perform the compilation
        this.succees = task.call();

        // Close the file manager
        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print compilation diagnostics
        StringBuilder sb = new StringBuilder();
        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
            sb.append(diagnostic.getKind()).append(": ").append(diagnostic.getMessage(null));
            sb.append("\n Line ").append(diagnostic.getLineNumber()).append( " in ").append(diagnostic.getSource().toUri());
            System.out.println(diagnostic.getKind() + ": " + diagnostic.getMessage(null));
            System.out.println("Line " + diagnostic.getLineNumber() + " in " + diagnostic.getSource().toUri());
        }
        this.compilationError = sb.toString();
    }
}
