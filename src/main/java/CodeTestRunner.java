import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeTestRunner {
    /**
     * Compila la clase y la clase de test y deja los resultados de la ejecución en un archivo xml
     * @param args: arg[0] es el nombre de la clase
     *            : arg[1] es el directorio donde se encuentra la clase
     *
     */
    public static void main(String[] args) throws IOException {
        // 1º Compilar la clase
        String classCode = args[0];
        String path = args[1];

        // Crear el archivo xml para los resultados
        Results results = new Results(args[1]);

        //Lista para añadir las clases a compilar
        List<String> sources = new ArrayList<>();
        //Añadimos la clase
        sources.add( System.getProperty("user.dir") + "/" + path + "/" + classCode + ".java");

        CompileSource compileSource = new CompileSource(sources);
        compileSource.compile();

        if (!compileSource.isSucceed()){
            results.writeExecutionCode(1);
            results.writeError(compileSource.getCompilationError());
        }else{
            // 2º Compilar el test junto con el programa
            // Añadimos el test
            sources.add( System.getProperty("user.dir") + "/" + path + "/" + classCode + "Test.java");

            compileSource.compile();

            if (!compileSource.isSucceed()) {
                results.writeExecutionCode(2);
                results.writeError(compileSource.getCompilationError());
            }else{
                // Si todo ha ido bien, ejecutar el test
                TestExecutor tx = new TestExecutor( classCode + "Test", path);
                if (tx.test())
                    results.writeExecutionCode(0);
                else{
                    results.writeExecutionCode(3);
                    results.writeError(tx.getTestError().toString());
                }
            }
        }

        results.close();
    }
}
